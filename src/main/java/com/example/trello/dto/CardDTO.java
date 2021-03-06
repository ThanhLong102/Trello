package com.example.trello.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {
    private Long id;

    @NotEmpty
    private String title;

    private String description;

    private Long listID;

    private Integer countComment;

}
