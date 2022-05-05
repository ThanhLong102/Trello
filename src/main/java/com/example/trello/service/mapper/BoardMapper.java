package com.example.trello.service.mapper;

import com.example.trello.dto.BoardDTO;
import com.example.trello.model.Board;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardMapper implements EntityMapper<BoardDTO, Board>{

    @Override
    public Board toEntity(BoardDTO dto) {
        if (dto == null) {
            return null;
        }
        Board entity = new Board();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    public BoardDTO toDto(Board entity) {
        if (entity == null) {
            return null;
        }

        BoardDTO dto = new BoardDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    @Override
    public List<Board> toEntity(List<BoardDTO> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<BoardDTO> toDto(List<Board> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
