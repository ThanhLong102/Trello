package com.example.trello.web.vm;

import com.example.trello.dto.ListDTO;
import com.example.trello.model.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BoardVm {
    Board board;

    List<ListDTO> list;
}
