package com.portfolio.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "about")
public class About {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    @Column(nullable = false)
    private String name;
    
    @NotBlank(message = "Title is required")
    @Size(max = 200, message = "Title must not exceed 200 characters")
    @Column(nullable = false)
    private String title;
    
    @NotBlank(message = "Bio is required")
    @Size(max = 2000, message = "Bio must not exceed 2000 characters")
    @Column(nullable = false, length = 2000)
    private String bio;
    
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    @Column(nullable = false)
    private String email;
    
    @Size(max = 20, message = "Phone must not exceed 20 characters")
    private String phone;
    
    @Size(max = 200, message = "Location must not exceed 200 characters")
    private String location;
    
    @Size(max = 200, message = "LinkedIn URL must not exceed 200 characters")
    @Column(name = "linkedin_url")
    private String linkedinUrl;
    
    @Size(max = 200, message = "GitHub URL must not exceed 200 characters")
    @Column(name = "github_url")
    private String githubUrl;
    
    @Size(max = 200, message = "Resume URL must not exceed 200 characters")
    @Column(name = "resume_url")
    private String resumeUrl;
    
    @Size(max = 200, message = "Profile image URL must not exceed 200 characters")
    @Column(name = "profile_image_url")
    private String profileImageUrl;
    
    // Constructors
    public About() {}
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getBio() {
        return bio;
    }
    
    public void setBio(String bio) {
        this.bio = bio;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getLinkedinUrl() {
        return linkedinUrl;
    }
    
    public void setLinkedinUrl(String linkedinUrl) {
        this.linkedinUrl = linkedinUrl;
    }
    
    public String getGithubUrl() {
        return githubUrl;
    }
    
    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }
    
    public String getResumeUrl() {
        return resumeUrl;
    }
    
    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }
    
    public String getProfileImageUrl() {
        return profileImageUrl;
    }
    
    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
