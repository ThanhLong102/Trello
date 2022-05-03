package com.example.trello.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Workspace_URDTO {

    private Long id;

    private String userName;

    private String role;

    private Long workspaceId;
}
