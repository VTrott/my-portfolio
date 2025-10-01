import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface GitHubProfile {
  id: number;
  login: string;
  name: string;
  bio: string;
  avatarUrl: string;
  htmlUrl: string;
  publicRepos: number;
  followers: number;
  following: number;
  createdAt: string;
  updatedAt: string;
  location: string;
  email: string;
  blog: string;
  company: string;
  twitterUsername: string;
}

export interface GitHubRepository {
  id: number;
  name: string;
  fullName: string;
  description: string;
  htmlUrl: string;
  cloneUrl: string;
  language: string;
  stargazersCount: number;
  forksCount: number;
  watchersCount: number;
  openIssuesCount: number;
  createdAt: string;
  updatedAt: string;
  pushedAt: string;
  size: number;
  defaultBranch: string;
  topics: string[];
  homepage: string;
  archived: boolean;
  disabled: boolean;
}

export interface GitHubLanguage {
  name: string;
  bytes: number;
}

@Injectable({
  providedIn: 'root'
})
export class GitHubService {
  private apiUrl = 'http://localhost:8080/api/github';

  constructor(private http: HttpClient) { }

  getUserProfile(username: string): Observable<GitHubProfile> {
    return this.http.get<GitHubProfile>(`${this.apiUrl}/profile/${username}`);
  }

  getUserRepositories(username: string): Observable<GitHubRepository[]> {
    return this.http.get<GitHubRepository[]>(`${this.apiUrl}/repos/${username}`);
  }

  getRepositoryDetails(username: string, repoName: string): Observable<GitHubRepository> {
    return this.http.get<GitHubRepository>(`${this.apiUrl}/repos/${username}/${repoName}`);
  }

  getRepositoryLanguages(username: string, repoName: string): Observable<GitHubLanguage[]> {
    return this.http.get<GitHubLanguage[]>(`${this.apiUrl}/repos/${username}/${repoName}/languages`);
  }
}
