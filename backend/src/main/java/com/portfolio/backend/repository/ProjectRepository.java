package com.portfolio.backend.repository;

import com.portfolio.backend.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    List<Project> findByIsFeaturedTrueOrderByCreatedDateDesc();
    
    List<Project> findAllByOrderByCreatedDateDesc();
    
    @Query("SELECT p FROM Project p WHERE p.title LIKE %:keyword% OR p.description LIKE %:keyword% OR p.technologies LIKE %:keyword%")
    List<Project> findByKeyword(String keyword);
    
    List<Project> findByTechnologiesContainingIgnoreCase(String skill);
}
