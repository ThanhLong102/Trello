package com.example.trello.web.rest;

import com.example.trello.core.Constants;
import com.example.trello.dto.Board_UserRoleDTO;
import com.example.trello.model.Board;
import com.example.trello.service.BoardURService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.USER+"/boardURs")
@Api(tags = "BoardUR")
@CrossOrigin(origins = "*", maxAge = 3600)
public class Board_URController {
    private final BoardURService boardService;

    public Board_URController(BoardURService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<Board_UserRoleDTO> add(@Valid @RequestBody Board_UserRoleDTO board_userRoleDTO) {
        return ResponseEntity.ok().body(boardService.save(board_userRoleDTO));
    }

    @PutMapping
    public ResponseEntity<Board_UserRoleDTO> update(@Valid @RequestBody Board_UserRoleDTO board_userRoleDTO) {
        return ResponseEntity.ok().body(boardService.save(board_userRoleDTO));
    }

    @GetMapping("/id={id}")
    public ResponseEntity<Board_UserRoleDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(boardService.findOne(id));
    }

    @PostMapping("/boards")
    public ResponseEntity<List<Board_UserRoleDTO>> findAllBoardByUserNameAndRole(@Valid @RequestBody Board_UserRoleDTO board_userRoleDTO) {
        return ResponseEntity.ok().body(boardService.findAllBoardByUserNameAndRole(board_userRoleDTO));
    }

    @GetMapping("/boards")
    public ResponseEntity<List<Board>> getAllByToken(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(boardService.findAllBoardByToken(token));
    }

    @DeleteMapping("/id={id}")
    public void delete(@PathVariable("id") Long id) {
        boardService.delete(id);
    }
}
