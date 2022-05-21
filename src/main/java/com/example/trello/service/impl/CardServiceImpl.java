package com.example.trello.service.impl;

import com.example.trello.dto.CardDTO;
import com.example.trello.model.Card;
import com.example.trello.repositories.CardRepository;
import com.example.trello.repositories.ListRepository;
import com.example.trello.service.CardService;
import com.example.trello.service.CommentService;
import com.example.trello.service.mapper.CardMapper;
import com.example.trello.web.vm.CardVm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CardServiceImpl implements CardService {
    private final Logger log = LoggerFactory.getLogger(CardServiceImpl.class);

    private final CardRepository cardRepository;

    private final ListRepository listRepository;

    private final CommentService commentService;

    private final CardMapper cardMapper;

    public CardServiceImpl(CardRepository cardRepository, ListRepository listRepository, CommentService commentService, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.listRepository = listRepository;
        this.commentService = commentService;
        this.cardMapper = cardMapper;
    }


    @Override
    public CardDTO save(CardDTO cardDTO) {
        log.debug("Request to save card : {}", cardDTO);
        Card card = cardMapper.toEntity(cardDTO);
        card = cardRepository.save(card);
        return cardMapper.toDto(card);
    }

    @Transactional(readOnly = true)
    @Override
    public CardVm findOne(Long id) {
        log.debug("Request to get card : {}", id);
        return new CardVm(cardMapper.toDto(cardRepository.findOneById(id)),commentService.findAllByCard(id));
    }

    @Override
    public List<CardDTO> findAllByList(Long id) {
        log.debug("Request to get card : {}", id);
        return cardMapper.toDto(cardRepository.findByList(listRepository.findOneById(id)));
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete card : {}", id);
        cardRepository.deleteById(id);
    }
}
