package com.example.trello.repositories;

import com.example.trello.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Set;

public interface RoleRepository extends PagingAndSortingRepository<Role,Long> {
    Set<Role> findByContent(String role);
}
