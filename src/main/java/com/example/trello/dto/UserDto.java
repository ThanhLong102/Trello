package com.example.trello.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NonNull
    private String email;

    @NonNull
    private String userName;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    private Boolean isEnabled = false;

    private String password;
}
