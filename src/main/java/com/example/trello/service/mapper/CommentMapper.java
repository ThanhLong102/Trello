package com.example.trello.service.mapper;

import com.example.trello.dto.CommentDTO;
import com.example.trello.model.Comment;
import com.example.trello.repositories.CardRepository;
import com.example.trello.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentMapper implements EntityMapper<CommentDTO, Comment>{

    private final UserRepository userRepository;

    private final CardRepository cardRepository;

    public CommentMapper(UserRepository userRepository, CardRepository cardRepository) {
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public Comment toEntity(CommentDTO dto) {
        if (dto == null) {
            return null;
        }

        Comment entity = new Comment();
        BeanUtils.copyProperties(dto, entity);
        entity.setUser(userRepository.findOneById(dto.getUserID()));
        entity.setCard(cardRepository.findOneById(dto.getCardID()));
        return entity;
    }

    @Override
    public CommentDTO toDto(Comment entity) {
        if (entity == null) {
            return null;
        }

        CommentDTO dto = new CommentDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setUserID(entity.getUser().getId());
        dto.setCardID(entity.getCard().getId());
        dto.setNameUser(entity.getUser().getName());
        return dto;
    }

    @Override
    public List<Comment> toEntity(List<CommentDTO> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> toDto(List<Comment> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
