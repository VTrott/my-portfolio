package com.portfolio.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class GitHubService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String GITHUB_API_BASE = "https://api.github.com";
    private static final String GITHUB_TOKEN = System.getenv("GITHUB_TOKEN"); // Set this in your environment

    public List<Map<String, Object>> getUserRepositories(String username) {
        try {
            String url = GITHUB_API_BASE + "/users/" + username + "/repos?sort=updated&per_page=12&type=all";
            
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/vnd.github.v3+json");
            headers.set("User-Agent", "Portfolio-App");
            if (GITHUB_TOKEN != null && !GITHUB_TOKEN.isEmpty()) {
                headers.set("Authorization", "Bearer " + GITHUB_TOKEN);
            }
            
            HttpEntity<String> entity = new HttpEntity<>(headers);
            
            ResponseEntity<List> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, List.class);
            
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                List<Map<String, Object>> repos = response.getBody();
                return processRepositories(repos);
            }
            
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Error fetching GitHub repositories: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private List<Map<String, Object>> processRepositories(List<Map<String, Object>> repos) {
        return repos.stream()
            .filter(repo -> !(Boolean) repo.getOrDefault("fork", false)) // Filter out forks
            .filter(repo -> !(Boolean) repo.getOrDefault("private", false)) // Filter out private repos
            .map(this::processRepository)
            .collect(Collectors.toList());
    }

    private Map<String, Object> processRepository(Map<String, Object> repo) {
        Map<String, Object> processedRepo = new HashMap<>();
        
        processedRepo.put("id", repo.get("id"));
        processedRepo.put("name", repo.get("name"));
        processedRepo.put("fullName", repo.get("full_name"));
        processedRepo.put("description", repo.get("description"));
        processedRepo.put("htmlUrl", repo.get("html_url"));
        processedRepo.put("cloneUrl", repo.get("clone_url"));
        processedRepo.put("language", repo.get("language"));
        processedRepo.put("stargazersCount", repo.get("stargazers_count"));
        processedRepo.put("forksCount", repo.get("forks_count"));
        processedRepo.put("watchersCount", repo.get("watchers_count"));
        processedRepo.put("openIssuesCount", repo.get("open_issues_count"));
        processedRepo.put("createdAt", repo.get("created_at"));
        processedRepo.put("updatedAt", repo.get("updated_at"));
        processedRepo.put("pushedAt", repo.get("pushed_at"));
        processedRepo.put("size", repo.get("size"));
        processedRepo.put("defaultBranch", repo.get("default_branch"));
        processedRepo.put("topics", repo.get("topics"));
        processedRepo.put("homepage", repo.get("homepage"));
        processedRepo.put("archived", repo.get("archived"));
        processedRepo.put("disabled", repo.get("disabled"));
        
        return processedRepo;
    }

    public Map<String, Object> getRepositoryDetails(String username, String repoName) {
        try {
            String url = GITHUB_API_BASE + "/repos/" + username + "/" + repoName;
            
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/vnd.github.v3+json");
            if (GITHUB_TOKEN != null && !GITHUB_TOKEN.isEmpty()) {
                headers.set("Authorization", "token " + GITHUB_TOKEN);
            }
            
            HttpEntity<String> entity = new HttpEntity<>(headers);
            
            ResponseEntity<Map> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, Map.class);
            
            return response.getBody();
        } catch (Exception e) {
            System.err.println("Error fetching repository details: " + e.getMessage());
            return null;
        }
    }

    public List<Map<String, Object>> getRepositoryLanguages(String username, String repoName) {
        try {
            String url = GITHUB_API_BASE + "/repos/" + username + "/" + repoName + "/languages";
            
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/vnd.github.v3+json");
            if (GITHUB_TOKEN != null && !GITHUB_TOKEN.isEmpty()) {
                headers.set("Authorization", "token " + GITHUB_TOKEN);
            }
            
            HttpEntity<String> entity = new HttpEntity<>(headers);
            
            ResponseEntity<Map> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, Map.class);
            
            Map<String, Object> languages = response.getBody();
            List<Map<String, Object>> result = new ArrayList<>();
            
            if (languages != null) {
                for (Map.Entry<String, Object> entry : languages.entrySet()) {
                    Map<String, Object> lang = Map.of(
                        "name", entry.getKey(),
                        "bytes", entry.getValue()
                    );
                    result.add(lang);
                }
            }
            
            return result;
        } catch (Exception e) {
            System.err.println("Error fetching repository languages: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
