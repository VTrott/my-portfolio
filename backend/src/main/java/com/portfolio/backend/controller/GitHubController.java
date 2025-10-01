package com.portfolio.backend.controller;

import com.portfolio.backend.service.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/github")
@CrossOrigin(origins = "http://localhost:4200")
public class GitHubController {

    @Autowired
    private GitHubService gitHubService;

    @GetMapping("/repos/{username}")
    public List<Map<String, Object>> getUserRepositories(@PathVariable String username) {
        return gitHubService.getUserRepositories(username);
    }

    @GetMapping("/repos/{username}/{repoName}")
    public Map<String, Object> getRepositoryDetails(@PathVariable String username, @PathVariable String repoName) {
        return gitHubService.getRepositoryDetails(username, repoName);
    }

    @GetMapping("/repos/{username}/{repoName}/languages")
    public List<Map<String, Object>> getRepositoryLanguages(@PathVariable String username, @PathVariable String repoName) {
        return gitHubService.getRepositoryLanguages(username, repoName);
    }
}
