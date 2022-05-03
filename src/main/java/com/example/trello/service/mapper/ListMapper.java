package com.example.trello.service.mapper;

import com.example.trello.dto.ListDTO;
import com.example.trello.model.List;
import com.example.trello.repositories.BoardRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ListMapper implements EntityMapper<ListDTO , List>{

    private final BoardRepository boardRepository;

    public ListMapper(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public List toEntity(ListDTO dto) {
        if (dto == null) {
            return null;
        }
        List entity = new List();
        BeanUtils.copyProperties(dto, entity);
        entity.setBoard(boardRepository.findOneById(dto.getBoardID()));
        return entity;
    }

    @Override
    public ListDTO toDto(List entity) {
        if (entity == null) {
            return null;
        }

        ListDTO dto = new ListDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setBoardID(entity.getBoard().getId());
        return dto;
    }

    @Override
    public java.util.List<List> toEntity(java.util.List<ListDTO> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public java.util.List<ListDTO> toDto(java.util.List<List> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
