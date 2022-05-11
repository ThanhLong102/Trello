package com.example.trello.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board_UserRoleDTO {

    private Long id;

    @NotEmpty
    private String userName;

    @NotEmpty
    private String role;

    @NotEmpty
    private Long boardId;
}