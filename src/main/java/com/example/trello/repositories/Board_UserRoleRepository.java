package com.example.trello.repositories;

import com.example.trello.model.Board_UserRole;
import com.example.trello.model.Role;
import com.example.trello.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface Board_UserRoleRepository extends PagingAndSortingRepository<Board_UserRole,Long> {
    Board_UserRole findOneById(Long id);

    List<Board_UserRole> findByUserAndRole(User user, Role role);
}
