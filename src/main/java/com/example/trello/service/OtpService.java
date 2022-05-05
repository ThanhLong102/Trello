package com.example.trello.service;


import com.example.trello.dto.MessageDto;
import com.example.trello.dto.OtpDTO;

public interface OtpService {

     MessageDto sendOtp(OtpDTO otpDTO);
}
