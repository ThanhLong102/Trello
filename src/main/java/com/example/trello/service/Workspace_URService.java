package com.example.trello.service;

import com.example.trello.dto.Workspace_URDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface Workspace_URService {
    Workspace_URDTO save(Workspace_URDTO workspace_urdto);

    @Transactional(readOnly = true)
    Workspace_URDTO findOne(Long id);

    @Transactional(readOnly = true)
    List<Workspace_URDTO> findAllWorkspaceByUserNameAndRole(Workspace_URDTO workspace_urdto);

    void delete(Long id);
}
