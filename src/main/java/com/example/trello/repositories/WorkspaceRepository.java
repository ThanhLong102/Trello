package com.example.trello.repositories;

import com.example.trello.model.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace,Long> {
    Workspace findOneById(Long workspaceId);
}
