package com.example.trello.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Long id;

    private Long userID;

    private Long cardID;

    private Date date;

    private String content;
}
