package com.example.trello.service.impl;

import com.example.trello.dto.BoardDTO;
import com.example.trello.dto.CardDTO;
import com.example.trello.dto.ListDTO;
import com.example.trello.model.Board;
import com.example.trello.model.Board_UserRole;
import com.example.trello.model.User;
import com.example.trello.repositories.BoardRepository;
import com.example.trello.repositories.Board_UserRoleRepository;
import com.example.trello.service.BoardService;
import com.example.trello.service.CardService;
import com.example.trello.service.ListService;
import com.example.trello.service.UserService;
import com.example.trello.service.mapper.BoardMapper;
import com.example.trello.web.vm.BoardVm;
import com.example.trello.web.vm.ListVm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class BoardServiceImpl implements BoardService {
    private final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);

    private final BoardRepository boardRepository;

    private final BoardMapper boardMapper;

    private final ListService listService;

    private final CardService cardService;

    private final UserService userService;

    private final Board_UserRoleRepository board_userRoleRepository;


    public BoardServiceImpl(BoardRepository boardRepository, BoardMapper boardMapper, ListService listService, CardService cardService, UserService userService, Board_UserRoleRepository board_userRoleRepository) {
        this.boardRepository = boardRepository;
        this.boardMapper = boardMapper;
        this.listService = listService;
        this.cardService = cardService;
        this.userService = userService;
        this.board_userRoleRepository = board_userRoleRepository;
    }

    @Override
    public BoardDTO save(BoardDTO boardDTO) {
        log.debug("Request to save board : {}", boardDTO);
        Board board = boardMapper.toEntity(boardDTO);
        board = boardRepository.save(board);
        return boardMapper.toDto(board);
    }

    @Override
    public List<BoardDTO> search(BoardDTO boardDTO, String token) {
        log.debug("Request to search board : {}", boardDTO);
        User user = userService.findByToken(token);
        List<Board_UserRole> board_userRoles = board_userRoleRepository.findByUser(user);
        List<Board> boards = new ArrayList<>();
        for (Board_UserRole board_userRole: board_userRoles){
            if(board_userRole.getBoard().getTitle().toLowerCase().contains(boardDTO.getTitle().toLowerCase())){
                boards.add(board_userRole.getBoard());
            }
        }
        return boardMapper.toDto(boards);
    }

    @Transactional(readOnly = true)
    @Override
    public BoardVm findOne(Long id) {
        log.debug("Request to get board : {}", id);
        Board board = boardRepository.findOneById(id);
        List<ListVm> listVmList = new ArrayList<>();
        List<ListDTO> lists = listService.findAllByBoard(id);
        for (ListDTO listDTO : lists) {
            List<CardDTO> cardDTOS = cardService.findAllByList(listDTO.getId());
            listVmList.add(new ListVm(listDTO, cardDTOS));
        }
        return new BoardVm(board, listVmList);
    }


    @Override
    public void delete(Long id) {
        log.debug("Request to delete board : {}", id);
        boardRepository.deleteById(id);
    }
}
