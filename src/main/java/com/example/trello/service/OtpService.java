package com.example.trello.service;


import com.example.trello.dto.MessageDto;
import com.example.trello.dto.UserDTO;

public interface OtpService {
     MessageDto sendOtp(UserDTO userDTO);

}
