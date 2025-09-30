package com.portfolio.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "experiences")
public class Experience {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Company is required")
    @Size(max = 100, message = "Company must not exceed 100 characters")
    @Column(nullable = false)
    private String company;
    
    @NotBlank(message = "Position is required")
    @Size(max = 100, message = "Position must not exceed 100 characters")
    @Column(nullable = false)
    private String position;
    
    @NotBlank(message = "Description is required")
    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    @Column(nullable = false, length = 1000)
    private String description;
    
    @Size(max = 200, message = "Location must not exceed 200 characters")
    private String location;
    
    @Column(name = "start_date")
    private LocalDate startDate;
    
    @Column(name = "end_date")
    private LocalDate endDate;
    
    @Column(name = "is_current")
    private Boolean isCurrent = false;
    
    @Size(max = 200, message = "Company URL must not exceed 200 characters")
    @Column(name = "company_url")
    private String companyUrl;
    
    @Size(max = 200, message = "Company logo URL must not exceed 200 characters")
    @Column(name = "company_logo_url")
    private String companyLogoUrl;
    
    // Constructors
    public Experience() {}
    
    public Experience(String company, String position, String description, LocalDate startDate) {
        this.company = company;
        this.position = position;
        this.description = description;
        this.startDate = startDate;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCompany() {
        return company;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }
    
    public String getPosition() {
        return position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    
    public LocalDate getEndDate() {
        return endDate;
    }
    
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    public Boolean getIsCurrent() {
        return isCurrent;
    }
    
    public void setIsCurrent(Boolean isCurrent) {
        this.isCurrent = isCurrent;
    }
    
    public String getCompanyUrl() {
        return companyUrl;
    }
    
    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }
    
    public String getCompanyLogoUrl() {
        return companyLogoUrl;
    }
    
    public void setCompanyLogoUrl(String companyLogoUrl) {
        this.companyLogoUrl = companyLogoUrl;
    }
}
