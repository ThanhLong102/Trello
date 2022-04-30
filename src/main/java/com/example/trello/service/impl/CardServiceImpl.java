package com.example.trello.service.impl;

import com.example.trello.dto.CardDTO;
import com.example.trello.model.Card;
import com.example.trello.repositories.BoardRepository;
import com.example.trello.repositories.CardRepository;
import com.example.trello.service.CardService;
import com.example.trello.service.mapper.CardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CardServiceImpl implements CardService {
    private final Logger log = LoggerFactory.getLogger(CardServiceImpl.class);

    private final CardRepository cardRepository;

    private final BoardRepository boardRepository;

    private final CardMapper cardMapper;

    public CardServiceImpl(CardRepository cardRepository, BoardRepository boardRepository, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.boardRepository = boardRepository;
        this.cardMapper = cardMapper;
    }


    @Override
    public CardDTO save(CardDTO cardDTO){
        log.debug("Request to save card : {}", cardDTO);
        Card card = cardMapper.toEntity(cardDTO);
        card = cardRepository.save(card);
        return cardMapper.toDto(card);
    }

    @Transactional(readOnly = true)
    @Override
    public CardDTO findOne(Long id) {
        log.debug("Request to get card : {}", id);
        return cardMapper.toDto(cardRepository.findOneById(id));
    }

    @Override
    public List<CardDTO> findAllByBoard(Long id) {
        log.debug("Request to get card : {}", id);
        return cardMapper.toDto(cardRepository.findByBoard(boardRepository.findOneById(id)));
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete card : {}", id);
        cardRepository.deleteById(id);
    }
}
