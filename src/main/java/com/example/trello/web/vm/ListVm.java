package com.example.trello.web.vm;

import com.example.trello.dto.ListDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListVm {

    List<ListDTO> list;
}
