package com.portfolio.backend.service;

import com.portfolio.backend.model.Experience;
import com.portfolio.backend.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceService {
    
    @Autowired
    private ExperienceRepository experienceRepository;
    
    public List<Experience> getAllExperiences() {
        return experienceRepository.findAllByOrderByStartDateDesc();
    }
    
    public List<Experience> getCurrentExperiences() {
        return experienceRepository.findByIsCurrentTrueOrderByStartDateDesc();
    }
    
    public Optional<Experience> getExperienceById(Long id) {
        return experienceRepository.findById(id);
    }
    
    public Experience saveExperience(Experience experience) {
        return experienceRepository.save(experience);
    }
    
    public Experience updateExperience(Long id, Experience experienceDetails) {
        Optional<Experience> optionalExperience = experienceRepository.findById(id);
        if (optionalExperience.isPresent()) {
            Experience experience = optionalExperience.get();
            experience.setCompany(experienceDetails.getCompany());
            experience.setPosition(experienceDetails.getPosition());
            experience.setDescription(experienceDetails.getDescription());
            experience.setLocation(experienceDetails.getLocation());
            experience.setStartDate(experienceDetails.getStartDate());
            experience.setEndDate(experienceDetails.getEndDate());
            experience.setIsCurrent(experienceDetails.getIsCurrent());
            experience.setCompanyUrl(experienceDetails.getCompanyUrl());
            experience.setCompanyLogoUrl(experienceDetails.getCompanyLogoUrl());
            return experienceRepository.save(experience);
        }
        return null;
    }
    
    public boolean deleteExperience(Long id) {
        if (experienceRepository.existsById(id)) {
            experienceRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
