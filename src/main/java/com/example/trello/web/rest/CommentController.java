package com.example.trello.web.rest;

import com.example.trello.core.Constants;
import com.example.trello.dto.CommentDTO;
import com.example.trello.service.CommentService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.USER+"/comments")
@Api(tags = "Comment")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping
    public ResponseEntity<CommentDTO> add(@Valid @RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok().body(commentService.save(commentDTO));
    }

    @PutMapping
    public ResponseEntity<CommentDTO> update(@Valid @RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok().body(commentService.save(commentDTO));
    }

    @GetMapping("/id={id}")
    public ResponseEntity<CommentDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(commentService.findOne(id));
    }

    @GetMapping("/board={id}")
    public ResponseEntity<List<CommentDTO>> getByBoardId(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(commentService.findAllByCard(id));
    }

    @DeleteMapping("/id={id}")
    public void delete(@PathVariable("id") Long id) {
        commentService.delete(id);
    }
}
