package com.example.trello.web.rest;

import com.example.trello.dto.CommentDTO;
import com.example.trello.service.CommentService;
import io.swagger.annotations.Api;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "chat")
@CrossOrigin(origins = "*", maxAge = 3600)
public class WebSocketController {
    private final CommentService commentService;

    public WebSocketController(CommentService commentService) {
        this.commentService = commentService;
    }

    @MessageMapping("/chat.sendComment")
    @SendTo("/topic/send-comment")
    public CommentDTO saveComment(CommentDTO commentDTO){
        return commentService.save(commentDTO);
    }

    @MessageMapping("/chat.deleteComment")
    @SendTo("/topic/delete-comment")
    public void delete(CommentDTO commentDTO){
        commentService.delete(commentDTO.getId());
    }

}
