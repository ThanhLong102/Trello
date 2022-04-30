package com.example.trello.service;

import com.example.trello.dto.MessageDto;
import com.example.trello.dto.UserDTO;

public interface AuthenticateService {
    MessageDto signup(UserDTO dto);

    MessageDto changePassword(UserDTO dto);

    String activeAccount(String otp, Long userId);
}
