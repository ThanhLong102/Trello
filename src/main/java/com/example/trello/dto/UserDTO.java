package com.example.trello.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Data
public class UserDTO {

    String name;

    @Email(message = "Email không hợp lệ")
    @NotEmpty(message = "Thiếu email")
    String email;

    @NotEmpty(message = "Thiếu username")
    String userName;

    @NotEmpty(message = "Thiếu password")
    @Min(value = 8, message = "Password phải từ 8 kí tự trở lên")
    String password;

    String phoneNumber;

    String avatarName;

    String gender;

    Instant birthDay;

    String otp;

}
