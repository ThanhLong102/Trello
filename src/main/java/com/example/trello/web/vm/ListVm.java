package com.example.trello.web.vm;

import com.example.trello.dto.CardDTO;
import com.example.trello.dto.ListDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ListVm {
    ListDTO listDTO;

    List<CardDTO> cardDTOList;
}
