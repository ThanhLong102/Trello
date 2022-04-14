package com.example.trello.repositories;

import com.example.trello.model.Role;
import com.example.trello.model.User;
import com.example.trello.model.UserRole;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRoleRepository extends PagingAndSortingRepository<UserRole,Long> {
    UserRole findOneById(Long id);

    UserRole findOneByUserAndRole(User user, Role role);
}
