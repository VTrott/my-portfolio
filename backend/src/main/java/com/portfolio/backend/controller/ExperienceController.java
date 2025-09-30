package com.portfolio.backend.controller;

import com.portfolio.backend.model.Experience;
import com.portfolio.backend.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/experiences")
@CrossOrigin(origins = "http://localhost:4200")
public class ExperienceController {
    
    @Autowired
    private ExperienceService experienceService;
    
    @GetMapping
    public ResponseEntity<List<Experience>> getAllExperiences() {
        List<Experience> experiences = experienceService.getAllExperiences();
        return ResponseEntity.ok(experiences);
    }
    
    @GetMapping("/current")
    public ResponseEntity<List<Experience>> getCurrentExperiences() {
        List<Experience> experiences = experienceService.getCurrentExperiences();
        return ResponseEntity.ok(experiences);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Experience> getExperienceById(@PathVariable Long id) {
        Optional<Experience> experience = experienceService.getExperienceById(id);
        return experience.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Experience> createExperience(@RequestBody Experience experience) {
        Experience savedExperience = experienceService.saveExperience(experience);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedExperience);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Experience> updateExperience(@PathVariable Long id, @RequestBody Experience experienceDetails) {
        Experience updatedExperience = experienceService.updateExperience(id, experienceDetails);
        if (updatedExperience != null) {
            return ResponseEntity.ok(updatedExperience);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long id) {
        boolean deleted = experienceService.deleteExperience(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
