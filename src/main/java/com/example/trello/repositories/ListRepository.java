package com.example.trello.repositories;

import com.example.trello.model.Board;
import com.example.trello.model.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends JpaRepository<List,Long> {
    List findOneById(Long listId);

    java.util.List<List> findByBoard(Board oneById);
}
