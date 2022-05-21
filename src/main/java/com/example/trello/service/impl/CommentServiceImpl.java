package com.example.trello.service.impl;

import com.example.trello.dto.CommentDTO;
import com.example.trello.model.Comment;
import com.example.trello.repositories.CardRepository;
import com.example.trello.repositories.CommentRepository;
import com.example.trello.service.CommentService;
import com.example.trello.service.mapper.CommentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    private final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);

    private final CommentRepository commentRepository;

    private final CardRepository cardRepository;

    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepository, CardRepository cardRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.cardRepository = cardRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public CommentDTO save(CommentDTO commentDTO){
        log.debug("Request to save comment : {}", commentDTO);
        Comment comment = commentMapper.toEntity(commentDTO);
        comment = commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }

    @Transactional(readOnly = true)
    @Override
    public CommentDTO findOne(Long id) {
        log.debug("Request to get comment : {}", id);
        return commentMapper.toDto(commentRepository.findOneById(id));
    }

    @Override
    public List<CommentDTO> findAllByCard(Long id) {
        log.debug("Request to get comment : {}", id);
        return commentMapper.toDto(commentRepository.findByCard(cardRepository.findOneById(id)));
    }

    @Override
    public int countCommentByCard(Long id) {
        log.debug("Request to get comment : {}", id);
        return commentRepository.findByCard(cardRepository.findOneById(id)).size();
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete comment : {}", id);
        commentRepository.deleteById(id);
    }
}
