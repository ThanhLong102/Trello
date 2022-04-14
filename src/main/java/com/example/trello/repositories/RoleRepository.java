package com.example.trello.repositories;

import com.example.trello.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleRepository extends PagingAndSortingRepository<Role,Long> {
    Role findOneByContent(String role);
}
