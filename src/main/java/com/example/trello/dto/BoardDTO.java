package com.example.trello.dto;

import com.example.trello.model.enumeration.ViewPermission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

    private Long id;

    private String title;

    private String background;

    private ViewPermission viewPermission;

    private Long workspaceId;
}
