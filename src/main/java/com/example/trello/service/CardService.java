package com.example.trello.service;

import com.example.trello.dto.CardDTO;
import com.example.trello.web.vm.CardVm;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CardService {
    CardDTO save(CardDTO cardDTO);

    @Transactional(readOnly = true)
    CardVm findOne(Long id);

    List<CardDTO> findAllByList(Long id);

    void delete(Long id);
}
