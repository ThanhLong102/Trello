package com.example.trello.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class OtpDTO {
    @Email(message = "Email không hợp lệ")
    @NotEmpty(message = "Thiếu email")
    String email;
}
