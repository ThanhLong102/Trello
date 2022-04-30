package com.example.trello.service.impl;

import com.example.trello.dto.MessageDto;
import com.example.trello.dto.UserDTO;
import com.example.trello.model.OTP;
import com.example.trello.model.User;
import com.example.trello.repositories.OtpRepository;
import com.example.trello.repositories.UserRepository;
import com.example.trello.service.OtpService;
import com.example.trello.service.email.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OtpServiceImpl implements OtpService {
    private final Logger log = LoggerFactory.getLogger(OtpServiceImpl.class);

    private final OtpRepository otpRepository;

    private final UserRepository userRepository;


    private final EmailService emailService;

    public OtpServiceImpl(OtpRepository otpRepository, UserRepository userRepository, EmailService emailService) {
        this.otpRepository = otpRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    public MessageDto sendOtp(UserDTO userDTO) {
        MessageDto messageDto = new MessageDto();
        try {
            User user = userRepository.findOneByEmail(userDTO.getEmail());
            System.out.println(user);
            if (user == null) {
                messageDto.setMessage("Không tìm thấy Email.");
                messageDto.setObj(false);
            } else {
                OTP otp = new OTP(user);
                OTP oldOtp = otpRepository.findOneByUser(user);
                if (oldOtp != null) {
                    oldOtp.setCode(otp.getCode());
                    oldOtp.setIssueAt(otp.getIssueAt());
                    otpRepository.save(oldOtp);
                } else {
                    otpRepository.save(otp);
                }
                String email = emailService.buildOtpEmail(user.getName(), otp.getCode());
                emailService.send(user.getEmail(), email);
                messageDto.setMessage("Đã gửi mã OTP đến mail: " + user.getEmail());
                messageDto.setObj(true);
            }
        } catch (Exception e) {
            log.error("Gửi mail không thành công", e);
        }
        return messageDto;
    }
}
