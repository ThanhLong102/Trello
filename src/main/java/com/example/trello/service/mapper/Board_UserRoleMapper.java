package com.example.trello.service.mapper;

import com.example.trello.dto.Board_UserRoleDTO;
import com.example.trello.model.Board;
import com.example.trello.model.Board_UserRole;
import com.example.trello.repositories.BoardRepository;
import com.example.trello.repositories.RoleRepository;
import com.example.trello.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Board_UserRoleMapper implements EntityMapper<Board_UserRoleDTO, Board_UserRole>{
    private final BoardRepository boardRepository;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public Board_UserRoleMapper(BoardRepository boardRepository, UserRepository userRepository, RoleRepository repository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.roleRepository = repository;
    }


    @Override
    public Board_UserRole toEntity(Board_UserRoleDTO dto) {
        if (dto == null) {
            return null;
        }
        Board_UserRole entity = new Board_UserRole();
        BeanUtils.copyProperties(dto, entity);
        entity.setBoard(boardRepository.findOneById(dto.getBoardId()));
        entity.setRole(roleRepository.findOneByContent(dto.getRole()));
        entity.setUser(userRepository.findOneByUserName(dto.getUserName()));
        return entity;
    }

    @Override
    public Board_UserRoleDTO toDto(Board_UserRole entity) {
        if (entity == null) {
            return null;
        }

        Board_UserRoleDTO dto = new Board_UserRoleDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setUserName(entity.getUser().getUserName());
        dto.setRole(entity.getRole().getContent());
        dto.setBoardId(entity.getBoard().getId());
        return dto;
    }

    @Override
    public List<Board_UserRole> toEntity(List<Board_UserRoleDTO> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<Board_UserRoleDTO> toDto(List<Board_UserRole> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());

    }

}
