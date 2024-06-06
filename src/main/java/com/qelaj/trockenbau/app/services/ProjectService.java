package com.qelaj.trockenbau.app.services;

import com.qelaj.trockenbau.app.entities.Client;
import com.qelaj.trockenbau.app.entities.Project;
import com.qelaj.trockenbau.app.repositories.ClientRepository;
import com.qelaj.trockenbau.app.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ClientRepository clientRepository;


    @Autowired
    public ProjectService(ProjectRepository projectRepository, ClientRepository clientRepository) {
        this.projectRepository = projectRepository;
        this.clientRepository = clientRepository;
    }


    public void createProject(Project project) {
        Optional<Client> client = clientRepository.findById(project.getClientId());
        if(client.isPresent()) {
            project.setClient(client.get());
            projectRepository.save(project);
        }
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public Optional<Project> getProject(Long id) {
        return projectRepository.findById(id);
    }

    public int updateProject(Long id,Project project) {
        Optional<Project> projectFromDb = projectRepository.findById(id);
        if(projectFromDb.isEmpty()) {
            return 404;
        }
        project.setId(id);
        project.setClient(projectFromDb.get().getClient());
        projectRepository.save(project);
        return 200;
    }

    public List<Project> getAllProject() {
        return projectRepository.findAll();
    }

    public List<Project> getAllProjectsByClientId(Long clientId) {
        return projectRepository.findByClientId(clientId);
    }

    public Page<Project> searchProjects(String value,Long clientId, Pageable pageable){
        value = value.toLowerCase();
        return projectRepository.findProjectsByValueAndClientId(value,clientId,pageable);
    }

    public Page<Project> getAllWithoutSearch(Long clientId,Pageable pageable){
        return projectRepository.findByClientId(clientId,pageable);
    }
}
