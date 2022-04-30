package com.example.trello.web.rest;

import com.example.trello.core.Constants;
import com.example.trello.dto.BoardDTO;
import com.example.trello.service.BoardService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = Constants.Api.Path.USER+"/boards")
@Api(tags = "Board")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<BoardDTO> add(@Valid @RequestBody BoardDTO boardDTO) {
        return ResponseEntity.ok().body(boardService.save(boardDTO));
    }

    @PutMapping
    public ResponseEntity<BoardDTO> update(@Valid @RequestBody BoardDTO boardDTO) {
        return ResponseEntity.ok().body(boardService.save(boardDTO));
    }

    @GetMapping("/id={id}")
    public ResponseEntity<BoardDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(boardService.findOne(id));
    }

    @DeleteMapping("/id={id}")
    public void delete(@PathVariable("id") Long id) {
        boardService.delete(id);
    }
}
