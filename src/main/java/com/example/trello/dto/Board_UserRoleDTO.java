package com.example.trello.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board_UserRoleDTO {

    private Long id;

    private String userName;

    private String role;

    private Long boardId;
}