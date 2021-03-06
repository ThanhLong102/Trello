package com.example.trello.web.auth;

import com.example.trello.core.Constants;
import com.example.trello.dto.MessageDto;
import com.example.trello.dto.OtpDTO;
import com.example.trello.dto.UserDTO;
import com.example.trello.security.jwt.JWTFilter;
import com.example.trello.security.jwt.TokenProvider;
import com.example.trello.security.oauth.OAuth2LoginSuccessHandler;
import com.example.trello.service.AuthenticateService;
import com.example.trello.service.OtpService;
import com.example.trello.web.vm.LoginVM;
import io.swagger.annotations.Api;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping(value = Constants.Api.Path.AUTH)
@Api(tags = "Auth")
@CrossOrigin(origins = "*")
public class AuthenticateController {

    private final AuthenticateService authenticateService;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final TokenProvider tokenProvider;

    private final OtpService otpService;

    public AuthenticateController(
            AuthenticateService authenticateService, AuthenticationManagerBuilder authenticationManagerBuilder,
            TokenProvider tokenProvider, OtpService otpService) {
        this.authenticateService = authenticateService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.tokenProvider = tokenProvider;
        this.otpService = otpService;
    }

    @PostMapping(Constants.Api.Path.Account.REGISTER)
    public ResponseEntity<MessageDto> register(@Valid @RequestBody UserDTO dto) {
        return ResponseEntity.ok().body(authenticateService.signup(dto));
    }

    @PostMapping(Constants.Api.Path.Account.CHANGE_PASSWORD)
    public ResponseEntity<MessageDto> changePassword(@RequestBody UserDTO dto) {
        return ResponseEntity.ok().body(authenticateService.changePassword(dto));
    }

    @GetMapping(Constants.Api.Path.Account.ACTIVE_ACCOUNT)
    public ResponseEntity<String> activeAccount(@RequestParam("otp") String otp, @RequestParam("id") Long userId) {
        return ResponseEntity.ok().body(authenticateService.activeAccount(otp, userId));
    }

    @PostMapping(Constants.Api.Path.Account.RESET_PASSWORD_FINISH)
    public ResponseEntity<MessageDto> resetPassword(@Valid @RequestBody UserDTO dto, @RequestParam String email) {
        dto.setEmail(email);
        System.out.println(dto.getEmail());
        return ResponseEntity.ok().body(authenticateService.changePassword(dto));
    }

    @PostMapping(Constants.Api.Path.Account.RESET_PASSWORD_INIT)
    public ResponseEntity<MessageDto> resetPasswordInit(@Valid @RequestBody OtpDTO dto) {
        return ResponseEntity.ok().body(otpService.sendOtp(dto));
    }

    @PostMapping(Constants.Api.Path.Auth.LOGIN)
    public ResponseEntity<?> authenticateLocal(@Valid @RequestBody LoginVM loginVM) {
//		T???o chu???i authentication t??? username v?? password (object LoginRequest
//		- file n??y ch??? l?? 1 class b??nh th?????ng, ch???a 2 tr?????ng username v?? password)
        UsernamePasswordAuthenticationToken authenticationString = new UsernamePasswordAuthenticationToken(
                loginVM.getUserName(),
                loginVM.getPassword()
        );
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationString);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, loginVM.getRememberMe());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, String.format("Bearer %s", jwt));
        return new ResponseEntity<>(Collections.singletonMap("token", jwt), httpHeaders, HttpStatus.OK); //Tr??? v??? chu???i jwt(authentication string)
    }

    @PostMapping(Constants.Api.Path.Auth.LOGIN_GOOGLE)
    public ResponseEntity<?> authenticateGoogle(Boolean rememberMe) {
        if(OAuth2LoginSuccessHandler.authentication == null){
            return ResponseEntity.ok().body(new MessageDto("Vui l??ng ????ng nh???p",false));
        }
        SecurityContextHolder.getContext().setAuthentication(OAuth2LoginSuccessHandler.authentication);
        String jwt = tokenProvider.createTokenLoginGG(OAuth2LoginSuccessHandler.authentication, rememberMe);
        OAuth2LoginSuccessHandler.authentication = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, String.format("Bearer %s", jwt));
        return new ResponseEntity<>(Collections.singletonMap("token", jwt), httpHeaders, HttpStatus.OK); //Tr??? v??? chu???i jwt(authentication string)
    }

}
