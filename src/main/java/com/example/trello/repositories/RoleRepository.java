package com.example.trello.repositories;

import com.example.trello.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role,Long> {
    Set<Role> findByContent(String role);

    Role findOneByContent(String role);
}
