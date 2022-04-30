package com.example.trello.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {

    String name;

    String email;

    String userName;

    String password;

    String phoneNumber;

    String avatarName;

    String gender;

    String newPassword;

    Date birthDay;

    String otp;
}
