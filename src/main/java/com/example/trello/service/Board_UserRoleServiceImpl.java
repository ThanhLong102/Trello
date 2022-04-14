package com.example.trello.service;

import com.example.trello.converter.Board_UserRoleConverter;
import com.example.trello.dto.Board_UserRoleDto;
import com.example.trello.model.Board_UserRole;
import com.example.trello.repositories.Board_UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Board_UserRoleServiceImpl implements Board_UserRoleService {

    @Autowired
    private Board_UserRoleRepository board_userRoleRepository;

    @Autowired
    private Board_UserRoleConverter board_userRoleConverter;

    @Override
    public Board_UserRoleDto update(Board_UserRoleDto board_userRoleDto) {
        Board_UserRole board_userRole;
        if (board_userRoleDto.getId() != null) {
            Board_UserRole oldBoard_UserRole = board_userRoleRepository.findOneById(board_userRoleDto.getId());
            board_userRole = board_userRoleConverter.toEntity(board_userRoleDto, oldBoard_UserRole);
        } else {
            board_userRole = board_userRoleConverter.toEntity(board_userRoleDto);
        }
        board_userRole = board_userRoleRepository.save(board_userRole);
        return board_userRoleConverter.toDto(board_userRole);
    }
}
