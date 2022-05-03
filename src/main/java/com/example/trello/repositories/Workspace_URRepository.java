package com.example.trello.repositories;

import com.example.trello.model.Role;
import com.example.trello.model.User;
import com.example.trello.model.Workspace_UR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Workspace_URRepository extends JpaRepository<Workspace_UR,Long> {
    Workspace_UR findOneById(Long id);

    List<Workspace_UR> findByUserAndRole(User user, Role role);
}
