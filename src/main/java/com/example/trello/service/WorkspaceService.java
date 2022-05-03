package com.example.trello.service;

import com.example.trello.dto.WorkspaceDTO;
import org.springframework.transaction.annotation.Transactional;

public interface WorkspaceService {
    WorkspaceDTO save(WorkspaceDTO workspaceDTO);

    @Transactional(readOnly = true)
    WorkspaceDTO findOne(Long id);

    void delete(Long id);
}
