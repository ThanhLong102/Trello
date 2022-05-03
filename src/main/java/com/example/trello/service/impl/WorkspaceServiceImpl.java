package com.example.trello.service.impl;

import com.example.trello.dto.WorkspaceDTO;
import com.example.trello.model.Workspace;
import com.example.trello.repositories.WorkspaceRepository;
import com.example.trello.service.WorkspaceService;
import com.example.trello.service.mapper.WorkspaceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WorkspaceServiceImpl implements WorkspaceService {
    private final Logger log = LoggerFactory.getLogger(WorkspaceServiceImpl.class);

    private final WorkspaceRepository workspaceRepository;

    private final WorkspaceMapper workspaceMapper;

    public WorkspaceServiceImpl(WorkspaceRepository workspaceRepository, WorkspaceMapper workspaceMapper) {
        this.workspaceRepository = workspaceRepository;
        this.workspaceMapper = workspaceMapper;
    }

    @Override
    public WorkspaceDTO save(WorkspaceDTO workspaceDTO){
        log.debug("Request to save workspace : {}", workspaceDTO);
        Workspace workspace = workspaceMapper.toEntity(workspaceDTO);
        workspace = workspaceRepository.save(workspace);
        return workspaceMapper.toDto(workspace);
    }

    @Transactional(readOnly = true)
    @Override
    public WorkspaceDTO findOne(Long id) {
        log.debug("Request to get workspace : {}", id);
        return workspaceMapper.toDto(workspaceRepository.findOneById(id));
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete workspace : {}", id);
        workspaceRepository.deleteById(id);
    }
}
