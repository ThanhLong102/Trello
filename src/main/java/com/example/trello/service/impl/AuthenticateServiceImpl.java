package com.example.trello.service.impl;

import com.example.trello.core.Constants;
import com.example.trello.dto.MessageDto;
import com.example.trello.dto.UserDTO;
import com.example.trello.model.OTP;
import com.example.trello.model.Role;
import com.example.trello.model.User;
import com.example.trello.repositories.OtpRepository;
import com.example.trello.repositories.RoleRepository;
import com.example.trello.repositories.UserRepository;
import com.example.trello.service.AuthenticateService;
import com.example.trello.service.email.EmailService;
import com.example.trello.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AuthenticateServiceImpl implements AuthenticateService {

    private final Logger log = LoggerFactory.getLogger(AuthenticateServiceImpl.class);

    public final UserMapper userMapper;

    public final RoleRepository roleRepository;

    public final UserRepository userRepository;

    public final EmailService emailService;

    private final OtpRepository otpRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthenticateServiceImpl(UserMapper userMapper, RoleRepository roleRepository, UserRepository userRepository, EmailService emailService, OtpRepository otpRepository, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.otpRepository = otpRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public MessageDto signup(UserDTO dto) {
        MessageDto messageDto = new MessageDto();
        String message;
        if (userRepository.findOneByEmail(dto.getEmail()) == null && userRepository.findOneByUserName(dto.getUserName()) == null
                ) {
            try {
                Set<Role> roles = roleRepository.findByContent(Constants.Role.USER);
                User user = userMapper.toEntity(dto);
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setActive(false);
                user.setRoles(roles);
                userRepository.save(user);
                OTP otp = new OTP(user);
                otpRepository.save(otp);
                String link = emailService.buildActiveEmail(user.getName(), otp.getCode(), user.getId());
                emailService.send(user.getEmail(), link);
                message = "Đăng kí thành công, vui lòng active tài khoản";
                messageDto.setObj(true);
                messageDto.setMessage(message);
                return messageDto;
            } catch (Exception e) {
                log.error("cannot save to database");
                message = "Có lỗi trong quá trình";
                messageDto.setObj(false);
                messageDto.setMessage(message);
                return messageDto;
            }
        } else {
            message = "Tài khoản đã tồn tại email hoặc số điện thoại, tên đăng nhập";
            messageDto.setObj(false);
            messageDto.setMessage(message);
            return messageDto;
        }
    }

    @Override
    public MessageDto changePassword(UserDTO dto) {
        String message = "";
        boolean check = false;
        try {
            User user = userRepository.findOneByEmail(dto.getEmail());
            if (user != null) {
                OTP dbOtp = otpRepository.findOneByUser(user);
                if (dbOtp.isExpired()) {
                    message = "Mã otp đã hết hạn";
                } else {
                    if (dbOtp.getCode().equals(dto.getOtp())) {
                        user.setPassword(passwordEncoder.encode(dto.getPassword()));
                        userRepository.save(user);
                        System.out.println("tempuser:" + user);
                        message = "Đổi mật khẩu thành công";
                        check = true;
                    } else {
                        message = "Mã otp không đúng vui lòng thử lại";
                    }
                }
            }
        } catch (Exception e) {
            message = "Đã sảy ra lỗi trong quá trình đổi mật khẩu vui lòng thử lại sau.";
        }
        MessageDto messageDto = new MessageDto();
        messageDto.setObj(check);
        messageDto.setMessage(message);
        return messageDto;
    }

    @Override
    public String activeAccount(String otp, Long userId) {
        try {
            Optional<User> dbUser = userRepository.findById(userId);
            if (dbUser.isPresent()) {
                User user = dbUser.get();
                OTP dbOtp = otpRepository.findOneByUser(user);
                if (dbOtp.getCode().equals(otp)) {
                    user.setActive(true);
                    userRepository.save(user);
                    return "Kích hoạt tài khoản thành công ";
                }
            } else {
                return "Kích hoạt tài khoản thất bại";
            }
        } catch (Exception e) {
            log.error("Lỗi kích hoạt", e);
        }
        return "Kích hoạt tài khoản thất bại";
    }

}
