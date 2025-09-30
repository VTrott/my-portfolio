package com.portfolio.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "projects")
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must not exceed 100 characters")
    @Column(nullable = false)
    private String title;
    
    @NotBlank(message = "Description is required")
    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Column(nullable = false, length = 1000)
    private String description;
    
    @Size(max = 500, message = "Technologies must not exceed 500 characters")
    @Column(length = 500)
    private String technologies;
    
    @Size(max = 200, message = "GitHub URL must not exceed 200 characters")
    @Column(name = "github_url")
    private String githubUrl;
    
    @Size(max = 200, message = "Live URL must not exceed 200 characters")
    @Column(name = "live_url")
    private String liveUrl;
    
    @Size(max = 200, message = "Image URL must not exceed 200 characters")
    @Column(name = "image_url")
    private String imageUrl;
    
    @Column(name = "created_date")
    private LocalDate createdDate;
    
    @Column(name = "is_featured")
    private Boolean isFeatured = false;
    
    // Constructors
    public Project() {
        this.createdDate = LocalDate.now();
    }
    
    public Project(String title, String description, String technologies) {
        this();
        this.title = title;
        this.description = description;
        this.technologies = technologies;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getTechnologies() {
        return technologies;
    }
    
    public void setTechnologies(String technologies) {
        this.technologies = technologies;
    }
    
    public String getGithubUrl() {
        return githubUrl;
    }
    
    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }
    
    public String getLiveUrl() {
        return liveUrl;
    }
    
    public void setLiveUrl(String liveUrl) {
        this.liveUrl = liveUrl;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public LocalDate getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
    
    public Boolean getIsFeatured() {
        return isFeatured;
    }
    
    public void setIsFeatured(Boolean isFeatured) {
        this.isFeatured = isFeatured;
    }
}
