package com.example.trello.service.mapper;

import com.example.trello.dto.CardDTO;
import com.example.trello.model.Card;
import com.example.trello.repositories.BoardRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardMapper implements EntityMapper<CardDTO, Card>{

    private final BoardRepository boardRepository;

    public CardMapper(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public Card toEntity(CardDTO dto) {
        if (dto == null) {
            return null;
        }

        Card entity = new Card();
        BeanUtils.copyProperties(dto, entity);
        entity.setBoard(boardRepository.findOneById(dto.getBoardId()));

        return entity;
    }

    @Override
    public CardDTO toDto(Card entity) {
        if (entity == null) {
            return null;
        }

        CardDTO dto = new CardDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setBoardId(entity.getBoard().getId());

        return dto;
    }

    @Override
    public List<Card> toEntity(List<CardDTO> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<CardDTO> toDto(List<Card> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
