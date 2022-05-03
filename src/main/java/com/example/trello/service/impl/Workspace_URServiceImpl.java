package com.example.trello.service.impl;

import com.example.trello.dto.Board_UserRoleDTO;
import com.example.trello.dto.Workspace_URDTO;
import com.example.trello.model.Board_UserRole;
import com.example.trello.model.Workspace_UR;
import com.example.trello.repositories.Workspace_URRepository;
import com.example.trello.service.Workspace_URService;
import com.example.trello.service.mapper.Workspace_URMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class Workspace_URServiceImpl implements Workspace_URService {
    private final Logger log = LoggerFactory.getLogger(Workspace_URServiceImpl.class);

    private final Workspace_URRepository workspace_urRepository;

    private final Workspace_URMapper workspace_urMapper;

    public Workspace_URServiceImpl(Workspace_URRepository workspace_urRepository, Workspace_URMapper workspace_urMapper) {
        this.workspace_urRepository = workspace_urRepository;
        this.workspace_urMapper = workspace_urMapper;
    }


    @Override
    public Workspace_URDTO save(Workspace_URDTO workspace_urdto){
        log.debug("Request to save workspace_ur : {}", workspace_urdto);
        Workspace_UR workspace_ur = workspace_urMapper.toEntity(workspace_urdto);
        workspace_ur = workspace_urRepository.save(workspace_ur);
        return workspace_urMapper.toDto(workspace_ur);
    }

    @Transactional(readOnly = true)
    @Override
    public Workspace_URDTO findOne(Long id) {
        log.debug("Request to get workspace_ur : {}", id);
        return workspace_urMapper.toDto(workspace_urRepository.findOneById(id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Workspace_URDTO> findAllWorkspaceByUserNameAndRole(Workspace_URDTO workspace_urdto) {
        log.debug("Request to get workspace_ur : {}", workspace_urdto);
        Workspace_UR workspace_ur = workspace_urMapper.toEntity(workspace_urdto);
        return workspace_urMapper.toDto(workspace_urRepository.findByUserAndRole(workspace_ur.getUser()
                ,workspace_ur.getRole()));
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete workspace_ur : {}", id);
        workspace_urRepository.deleteById(id);
    }
}
