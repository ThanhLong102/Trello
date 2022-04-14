package com.example.trello.converter;

import com.example.trello.dto.Board_UserRoleDto;
import com.example.trello.model.Board_UserRole;
import com.example.trello.repositories.BoardRepository;
import com.example.trello.repositories.RoleRepository;
import com.example.trello.repositories.UserRepository;
import com.example.trello.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Board_UserRoleConverter {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BoardRepository boardRepository;

    public Board_UserRole toEntity(Board_UserRoleDto board_userRoleDto) {
        Board_UserRole board_userRole = new Board_UserRole();
        board_userRole.setUserRole(userRoleRepository.findOneByUserAndRole(userRepository.findOneByUserName(board_userRoleDto.getUserName())
                ,roleRepository.findOneByContent(board_userRoleDto.getRole())));
        board_userRole.setBoard(boardRepository.findOneById(board_userRoleDto.getBoardId()));
        return board_userRole;
    }

    public Board_UserRole toEntity(Board_UserRoleDto dto, Board_UserRole entity) {
        entity.setUserRole(userRoleRepository.findOneByUserAndRole(userRepository.findOneByUserName(dto.getUserName())
                ,roleRepository.findOneByContent(dto.getRole())));
        entity.setBoard(boardRepository.findOneById(dto.getBoardId()));
        return entity;
    }

    public Board_UserRoleDto toDto(Board_UserRole entity) {
        Board_UserRoleDto board_userRoleDto = new Board_UserRoleDto();
        board_userRoleDto.setId(entity.getId());
        board_userRoleDto.setUserName(entity.getUserRole().getUser().getUserName());
        board_userRoleDto.setRole(entity.getUserRole().getRole().getContent());
        board_userRoleDto.setBoardId(entity.getBoard().getId());
        return board_userRoleDto;
    }
}
