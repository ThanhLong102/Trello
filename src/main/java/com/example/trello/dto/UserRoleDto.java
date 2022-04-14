package com.example.trello.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDto {
    private Long id;

    @NonNull
    private String userName;

    @NonNull
    private String role;
}
