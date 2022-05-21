package com.example.trello.service;

import com.example.trello.dto.Board_UserRoleDTO;
import com.example.trello.model.Board;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardURService {
    Board_UserRoleDTO save(Board_UserRoleDTO board_userRoleDTO);

    @Transactional(readOnly = true)
    Board_UserRoleDTO findOne(Long id);

    List<Board_UserRoleDTO> findAllBoardByUserNameAndRole(Board_UserRoleDTO board_userRoleDTO);

    void delete(Long id);

    List<Board> findAllBoardByToken(String token);
}
