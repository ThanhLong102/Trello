package com.example.trello.security.oauth;

import com.example.trello.model.User;
import com.example.trello.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserService userService;

    public static Authentication authentication;

    public OAuth2LoginSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        OAuth2LoginSuccessHandler.authentication = authentication;
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getEmail();
        User user = userService.findByEmail(email);
        String name = oAuth2User.getName();
        if(user == null){
            userService.createNewUserAfterOAuthLoginSuccess(email,name);
        }
        else {
            userService.updateNewUserAfterOAuthLoginSuccess(user,name);
        }
        response.setContentType("text/html");
        response.sendRedirect("http://localhost:3000");
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
