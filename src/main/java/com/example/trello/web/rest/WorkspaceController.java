package com.example.trello.web.rest;

import com.example.trello.core.Constants;
import com.example.trello.dto.WorkspaceDTO;
import com.example.trello.service.WorkspaceService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = Constants.Api.Path.USER+"/workspaces")
@Api(tags = "Workspace")
@CrossOrigin(origins = "*", maxAge = 3600)
public class WorkspaceController {
    private final WorkspaceService workspaceService;

    public WorkspaceController(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @PostMapping
    public ResponseEntity<WorkspaceDTO> add(@Valid @RequestBody WorkspaceDTO workspaceDTO) {
        return ResponseEntity.ok().body(workspaceService.save(workspaceDTO));
    }

    @PutMapping
    public ResponseEntity<WorkspaceDTO> update(@Valid @RequestBody WorkspaceDTO workspaceDTO) {
        return ResponseEntity.ok().body(workspaceService.save(workspaceDTO));
    }

    @GetMapping("/id={id}")
    public ResponseEntity<WorkspaceDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(workspaceService.findOne(id));
    }

    @DeleteMapping("/id={id}")
    public void delete(@PathVariable("id") Long id) {
        workspaceService.delete(id);
    }
}
