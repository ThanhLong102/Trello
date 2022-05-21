package com.example.trello.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

    private Long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String background;

    @NotEmpty
    private Boolean start;

}
