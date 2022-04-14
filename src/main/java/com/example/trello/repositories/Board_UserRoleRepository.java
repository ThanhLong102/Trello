package com.example.trello.repositories;

import com.example.trello.model.Board_UserRole;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface Board_UserRoleRepository extends PagingAndSortingRepository<Board_UserRole,Long> {
    Board_UserRole findOneById(Long id);
}
