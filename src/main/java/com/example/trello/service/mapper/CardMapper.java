package com.example.trello.service.mapper;

import com.example.trello.dto.CardDTO;
import com.example.trello.model.Card;
import com.example.trello.repositories.ListRepository;
import com.example.trello.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardMapper implements EntityMapper<CardDTO, Card>{

    private final ListRepository listRepository;

    private final CommentService commentService;

    public CardMapper(ListRepository listRepository, CommentService commentService) {
        this.listRepository = listRepository;
        this.commentService = commentService;
    }

    @Override
    public Card toEntity(CardDTO dto) {
        if (dto == null) {
            return null;
        }

        Card entity = new Card();
        BeanUtils.copyProperties(dto, entity);
        entity.setList(listRepository.findOneById(dto.getListID()));

        return entity;
    }

    @Override
    public CardDTO toDto(Card entity) {
        if (entity == null) {
            return null;
        }

        CardDTO dto = new CardDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setListID(entity.getList().getId());
        dto.setCountComment(commentService.countCommentByCard(entity.getId()));

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
