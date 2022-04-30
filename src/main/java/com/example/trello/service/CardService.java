package com.example.trello.service;

import com.example.trello.dto.CardDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CardService {
    CardDTO save(CardDTO cardDTO);

    @Transactional(readOnly = true)
    CardDTO findOne(Long id);

    List<CardDTO> findAllByBoard(Long id);

    void delete(Long id);
}
