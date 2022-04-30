package com.example.trello.repositories;

import com.example.trello.model.Card;
import com.example.trello.model.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment,Long> {
    List<Comment> findByCard(Card card);

    Comment findOneById(Long id);
}
