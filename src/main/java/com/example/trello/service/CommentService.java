package com.example.trello.service;

import com.example.trello.dto.CommentDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentService {
    CommentDTO save(CommentDTO commentDTO);

    @Transactional(readOnly = true)
    CommentDTO findOne(Long id);

    List<CommentDTO> findAllByCard(Long id);

    int countCommentByCard(Long id);

    void delete(Long id);
}
