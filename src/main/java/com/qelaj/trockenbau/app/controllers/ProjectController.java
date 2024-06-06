package com.qelaj.trockenbau.app.controllers;

import com.qelaj.trockenbau.app.entities.Project;
import com.qelaj.trockenbau.app.services.ProjectService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private ResponseEntity getAllProjectsBySearch(@RequestParam String value,@RequestParam Long clientId,@RequestParam int pageNumber, @RequestParam int dataSize,@RequestParam(required = false) String direction,@RequestParam(required = false) String sortBy){
        Pageable pagination=null;
        if(direction != null && sortBy != null){
            Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
            pagination = PageRequest.of(pageNumber, dataSize,sort);
        }else{
            pagination = PageRequest.of(pageNumber, dataSize);
        }
        if(value.equals("")){
            return ResponseEntity.ok(projectService.getAllWithoutSearch(clientId,pagination));
        }
        return ResponseEntity.ok(projectService.searchProjects(value,clientId,pagination));
    }
}
