package com.example.trello.web.rest;

import com.example.trello.dto.CommentDTO;
import com.example.trello.service.CommentService;
import io.swagger.annotations.Api;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "chat")
public class WebSocketController {
    private final CommentService commentService;

    public WebSocketController(CommentService commentService) {
        this.commentService = commentService;
    }

    @MessageMapping("/add-comment")
    @SendTo("/topic/add-comment")
    public CommentDTO saveComment(CommentDTO commentDTO){
        return commentService.save(commentDTO);
    }

    @MessageMapping("/delete-comment")
    @SendTo("/topic/delete-comment")
    public void delete(CommentDTO commentDTO){
        commentService.delete(commentDTO.getId());
    }

}
