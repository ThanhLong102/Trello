package com.example.trello.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class UserDTO {

    String name;

    String email;

    String userName;

    String password;

    String phoneNumber;

    String avatarName;

    String gender;

    Instant birthDay;

    String otp;

}
