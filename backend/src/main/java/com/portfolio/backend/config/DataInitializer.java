package com.portfolio.backend.config;

import com.portfolio.backend.model.About;
import com.portfolio.backend.model.Experience;
import com.portfolio.backend.model.Project;
import com.portfolio.backend.repository.AboutRepository;
import com.portfolio.backend.repository.ExperienceRepository;
import com.portfolio.backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AboutRepository aboutRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ExperienceRepository experienceRepository;

    @Override
    public void run(String... args) throws Exception {
        if (aboutRepository.count() == 0) {
            About about = new About();
            about.setName("Victoria Trotter");
            about.setTitle("Full-Stack Software Engineer");
            about.setBio("Passionate software engineer with 3+ years of experience building modern web applications. I love creating innovative solutions and turning complex problems into simple, elegant designs. I specialize in Angular, Spring Boot, and cloud technologies.");
            about.setEmail("victoriatrotter95@gmail.com");
            about.setPhone("+1 (386) 316-3160");
            about.setLocation("Palm Coast, FL");
            about.setLinkedinUrl("https://www.linkedin.com/in/vtrotter95/");
            about.setGithubUrl("https://github.com/VTrott");
            about.setResumeUrl("https://victoria-trotter-resume.pdf");
            about.setProfileImageUrl("https://images.unsplash.com/photo-1494790108755-2616b612b786?w=300&h=300&fit=crop&crop=face");
            aboutRepository.save(about);
        }

        // Initialize Project data
        if (projectRepository.count() == 0) {
         
            Project portfolioProject = new Project();
            portfolioProject.setTitle("Portfolio Website");
            portfolioProject.setDescription("A modern, responsive portfolio website built with Angular and Spring Boot. Features include dynamic content management, contact forms, and GitHub integration.");
            portfolioProject.setTechnologies("Angular, TypeScript, Spring Boot, Java, HTML, CSS, SCSS, Angular Material");
            portfolioProject.setGithubUrl("https://github.com/VTrott/portfolio");
            portfolioProject.setLiveUrl("https://victoria-trotter-portfolio.com");
            portfolioProject.setImageUrl("https://images.unsplash.com/photo-1460925895917-afdab827c52f?w=400&h=300&fit=crop");
            portfolioProject.setCreatedDate(LocalDate.now().minusDays(30));
            portfolioProject.setIsFeatured(true);
            projectRepository.save(portfolioProject);

            Project ecommerceProject = new Project();
            ecommerceProject.setTitle("E-Commerce Platform");
            ecommerceProject.setDescription("Full-stack e-commerce application with user authentication, payment integration, and admin dashboard. Built with React, Node.js, and MongoDB.");
            ecommerceProject.setTechnologies("React, Node.js, MongoDB, Express, Stripe API, JWT, Material-UI");
            ecommerceProject.setGithubUrl("https://github.com/VTrott/ecommerce-platform");
            ecommerceProject.setLiveUrl("https://ecommerce-demo.victoria-trotter.com");
            ecommerceProject.setImageUrl("https://images.unsplash.com/photo-1556742049-0cfed4f6a45d?w=400&h=300&fit=crop");
            ecommerceProject.setCreatedDate(LocalDate.now().minusDays(60));
            ecommerceProject.setIsFeatured(true);
            projectRepository.save(ecommerceProject);

            Project taskProject = new Project();
            taskProject.setTitle("Task Management App");
            taskProject.setDescription("Collaborative task management tool with real-time updates, drag-and-drop functionality, and team collaboration features.");
            taskProject.setTechnologies("Vue.js, Firebase, JavaScript, CSS, Vuex, Socket.io");
            taskProject.setGithubUrl("https://github.com/VTrott/task-manager");
            taskProject.setLiveUrl("https://taskmanager.victoria-trotter.com");
            taskProject.setImageUrl("https://images.unsplash.com/photo-1611224923853-80b023f02d71?w=400&h=300&fit=crop");
            taskProject.setCreatedDate(LocalDate.now().minusDays(90));
            taskProject.setIsFeatured(false);
            projectRepository.save(taskProject);

            Project weatherProject = new Project();
            weatherProject.setTitle("Weather Dashboard");
            weatherProject.setDescription("Real-time weather dashboard with location-based forecasts, interactive maps, and weather alerts. Integrates with OpenWeather API.");
            weatherProject.setTechnologies("Angular, OpenWeather API, TypeScript, Material Design, Chart.js");
            weatherProject.setGithubUrl("https://github.com/VTrott/weather-dashboard");
            weatherProject.setLiveUrl("https://weather.victoria-trotter.com");
            weatherProject.setImageUrl("https://images.unsplash.com/photo-1504608524841-42fe6f032b4b?w=400&h=300&fit=crop");
            weatherProject.setCreatedDate(LocalDate.now().minusDays(120));
            weatherProject.setIsFeatured(false);
            projectRepository.save(weatherProject);
        }

        if (experienceRepository.count() == 0) {
            Experience currentExperience = new Experience();
            currentExperience.setPosition("Full-Stack Software Engineer");
            currentExperience.setCompany("Graici");
            currentExperience.setLocation("Remote");
            currentExperience.setStartDate(LocalDate.of(2024, 8, 19));
            currentExperience.setEndDate(LocalDate.of(2025,9,15)); 
            currentExperience.setDescription("Start up company building an AI-powered data platform to help underserved communities");
            currentExperience.setCompanyUrl("https://techcorp.com");
            currentExperience.setCompanyLogoUrl("https://images.unsplash.com/photo-1560472354-b33ff0c44a43?w=100&h=100&fit=crop");
            experienceRepository.save(currentExperience);

            // Previous Position
            Experience prevExperience = new Experience();
            prevExperience.setPosition("Full-Stack Junior Software Engineer");
            prevExperience.setCompany("Fidelity Investments ");
            prevExperience.setLocation("Durham, NC");
            prevExperience.setStartDate(LocalDate.of(2022, 8, 22));
            prevExperience.setEndDate(LocalDate.of(2024, 8, 19));
            prevExperience.setDescription("Built a scalable and efficient full-stack Stock Plan Application for Fidelity Investments.");
            prevExperience.setCompanyUrl("https://www.fidelity.com/stock-plan-services/overview");
            prevExperience.setCompanyLogoUrl("https://images.unsplash.com/photo-1559136555-9303baea8ebd?w=100&h=100&fit=crop");
            experienceRepository.save(prevExperience);

            // First Position
            Experience firstExperience = new Experience();
            firstExperience.setPosition("Junior Software Developer");
            firstExperience.setCompany("Revature");
            firstExperience.setLocation("Remote");
            firstExperience.setStartDate(LocalDate.of(2022, 5, 22));
            firstExperience.setEndDate(LocalDate.of(2023, 10, 31));
            firstExperience.setDescription("Contributed to various web development projects and learned modern frameworks. Worked on client projects and maintained existing applications.");
            firstExperience.setCompanyUrl("https://revature.com");
            firstExperience.setCompanyLogoUrl("https://images.unsplash.com/photo-1486312338219-ce68d2c6f44d?w=100&h=100&fit=crop");
            experienceRepository.save(firstExperience);
        }
    }
}
