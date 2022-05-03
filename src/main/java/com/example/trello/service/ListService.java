package com.example.trello.service;

import com.example.trello.dto.ListDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ListService {
    ListDTO save(ListDTO listDTO);

    @Transactional(readOnly = true)
    ListDTO findOne(Long id);

    List<ListDTO> findAllByBoard(Long id);

    void delete(Long id);
}
