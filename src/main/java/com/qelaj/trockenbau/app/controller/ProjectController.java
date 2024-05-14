package com.qelaj.trockenbau.app.controller;

import com.qelaj.trockenbau.app.entity.Project;
import com.qelaj.trockenbau.app.service.ProjectService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    @GetMapping("/id/{id}")
    private ResponseEntity getProjectById(@PathVariable Long id){
        Optional<Project> project = projectService.getProject(id);
        if(project.isPresent()){
            return ResponseEntity.ok(project);
        }
        return (ResponseEntity) ResponseEntity.notFound();
    }

    @GetMapping("/all")
    private ResponseEntity getAllProjects(){
        return ResponseEntity.ok(projectService.getAllProject());
    }

    @PostMapping("/create")
    private ResponseEntity createProjects(@RequestBody Project project){
        projectService.createProject(project);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/update/id/{id}")
    private ResponseEntity updateProjects(@PathVariable Long id,@RequestBody Project project){
        projectService.updateProject(id,project);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    private ResponseEntity deleteProjectById(@PathVariable Long id){
        projectService.deleteProject(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/client/{clientId}")
    private ResponseEntity getProjectsByClientId(@PathVariable Long clientId){
        return ResponseEntity.ok(projectService.getAllProjectsByClientId(clientId));
    }

    @GetMapping("/search")
    private ResponseEntity getAllClients(@RequestParam String value,@RequestParam Long clientId,@RequestParam int pageNumber, @RequestParam int dataSize){
        Pageable pagination = PageRequest.of(pageNumber, dataSize);
        if(value.equals("")){
            return ResponseEntity.ok(projectService.getAllWithoutSearch(clientId,pagination));
        }
        return ResponseEntity.ok(projectService.searchProjects(value,clientId,pagination));
    }
}
