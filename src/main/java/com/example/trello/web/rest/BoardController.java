package com.example.trello.web.rest;

import com.example.trello.core.Constants;
import com.example.trello.dto.BoardDTO;
import com.example.trello.service.BoardService;
import com.example.trello.web.vm.BoardVm;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<BoardDTO> add(@Valid @RequestBody BoardDTO boardDTO, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(boardService.save(boardDTO,token));
    }

    @PutMapping
    public ResponseEntity<BoardDTO> update(@Valid @RequestBody BoardDTO boardDTO,  @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(boardService.save(boardDTO,token));
    }

    @GetMapping("/id={id}")
    public ResponseEntity<BoardVm> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(boardService.findOne(id));
    }

    @PostMapping("/search")
    public ResponseEntity<List<BoardDTO>> search(@RequestBody BoardDTO boardDTO, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(boardService.search(boardDTO, token));
    }

    @DeleteMapping("/id={id}")
    public void delete(@PathVariable("id") Long id) {
        boardService.delete(id);
    }
}
