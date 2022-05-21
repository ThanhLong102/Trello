package com.example.trello.web.vm;

import com.example.trello.dto.CardDTO;
import com.example.trello.dto.CommentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CardVm {
    CardDTO cardDTO;

    List<CommentDTO> commentDTOList;
}
