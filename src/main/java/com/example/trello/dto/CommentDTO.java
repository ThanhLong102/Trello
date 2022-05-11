package com.example.trello.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Long id;

    @NotEmpty
    private Long userID;

    @NotEmpty
    private Long cardID;

    private Instant createdDate;

    private Instant updatedDate;

    @NotEmpty
    private String content;
}
