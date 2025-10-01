package com.portfolio.backend.service;

import com.portfolio.backend.model.Project;
import com.portfolio.backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    
    @Autowired
    private ProjectRepository projectRepository;
    
    public List<Project> getAllProjects() {
        return projectRepository.findAllByOrderByCreatedDateDesc();
    }
    
    public List<Project> getFeaturedProjects() {
        return projectRepository.findByIsFeaturedTrueOrderByCreatedDateDesc();
    }
    
    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }
    
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }
    
    public Project updateProject(Long id, Project projectDetails) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            project.setTitle(projectDetails.getTitle());
            project.setDescription(projectDetails.getDescription());
            project.setTechnologies(projectDetails.getTechnologies());
            project.setGithubUrl(projectDetails.getGithubUrl());
            project.setLiveUrl(projectDetails.getLiveUrl());
            project.setImageUrl(projectDetails.getImageUrl());
            project.setIsFeatured(projectDetails.getIsFeatured());
            return projectRepository.save(project);
        }
        return null;
    }
    
    public boolean deleteProject(Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public List<Project> searchProjects(String keyword) {
        return projectRepository.findByKeyword(keyword);
    }
    
    public List<Project> filterProjectsBySkill(String skill) {
        return projectRepository.findByTechnologiesContainingIgnoreCase(skill);
    }
    
    public List<String> getAllSkills() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .map(Project::getTechnologies)
                .filter(technologies -> technologies != null && !technologies.trim().isEmpty())
                .flatMap(technologies -> Arrays.stream(technologies.split(",")))
                .map(String::trim)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}
