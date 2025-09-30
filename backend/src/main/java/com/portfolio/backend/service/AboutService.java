package com.portfolio.backend.service;

import com.portfolio.backend.model.About;
import com.portfolio.backend.repository.AboutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AboutService {
    
    @Autowired
    private AboutRepository aboutRepository;
    
    public Optional<About> getAbout() {
        return aboutRepository.findFirstByOrderByIdAsc();
    }
    
    public About saveAbout(About about) {
        return aboutRepository.save(about);
    }
    
    public About updateAbout(Long id, About aboutDetails) {
        Optional<About> optionalAbout = aboutRepository.findById(id);
        if (optionalAbout.isPresent()) {
            About about = optionalAbout.get();
            about.setName(aboutDetails.getName());
            about.setTitle(aboutDetails.getTitle());
            about.setBio(aboutDetails.getBio());
            about.setEmail(aboutDetails.getEmail());
            about.setPhone(aboutDetails.getPhone());
            about.setLocation(aboutDetails.getLocation());
            about.setLinkedinUrl(aboutDetails.getLinkedinUrl());
            about.setGithubUrl(aboutDetails.getGithubUrl());
            about.setResumeUrl(aboutDetails.getResumeUrl());
            about.setProfileImageUrl(aboutDetails.getProfileImageUrl());
            return aboutRepository.save(about);
        }
        return null;
    }
}
