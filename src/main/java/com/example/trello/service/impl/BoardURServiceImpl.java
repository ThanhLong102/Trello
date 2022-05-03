package com.example.trello.service.impl;

import com.example.trello.dto.Board_UserRoleDTO;
import com.example.trello.model.Board_UserRole;
import com.example.trello.repositories.Board_UserRoleRepository;
import com.example.trello.service.BoardURService;
import com.example.trello.service.mapper.Board_UserRoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BoardURServiceImpl implements BoardURService {
    private final Logger log = LoggerFactory.getLogger(BoardURServiceImpl.class);

    private final Board_UserRoleRepository board_userRoleRepository;

    private final Board_UserRoleMapper board_userRoleMapper;

    public BoardURServiceImpl(Board_UserRoleRepository board_userRoleRepository, Board_UserRoleMapper board_userRoleMapper) {
        this.board_userRoleRepository = board_userRoleRepository;
        this.board_userRoleMapper = board_userRoleMapper;
    }

    @Override
    public Board_UserRoleDTO save(Board_UserRoleDTO board_userRoleDTO){
        log.debug("Request to save board_ur : {}", board_userRoleDTO);
        Board_UserRole board_userRole = board_userRoleMapper.toEntity(board_userRoleDTO);
        board_userRole = board_userRoleRepository.save(board_userRole);
        return board_userRoleMapper.toDto(board_userRole);
    }

    @Transactional(readOnly = true)
    @Override
    public Board_UserRoleDTO findOne(Long id) {
        log.debug("Request to get board_ur : {}", id);
        return board_userRoleMapper.toDto(board_userRoleRepository.findOneById(id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Board_UserRoleDTO> findAllBoardByUserNameAndRole(Board_UserRoleDTO board_userRoleDTO) {
        log.debug("Request to get board_ur : {}", board_userRoleDTO);
        Board_UserRole board_userRole = board_userRoleMapper.toEntity(board_userRoleDTO);
        return board_userRoleMapper.toDto(board_userRoleRepository.findByUserAndRole(board_userRole.getUser()
                ,board_userRole.getRole()));
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete board : {}", id);
        board_userRoleRepository.deleteById(id);
    }
}
