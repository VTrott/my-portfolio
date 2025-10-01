import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GitHubService {
  private apiUrl = 'http://localhost:8080/api/github';

  constructor(private http: HttpClient) { }

  getUserRepositories(username: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/repos/${username}`);
  }

  getRepositoryDetails(username: string, repoName: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/repos/${username}/${repoName}`);
  }

  getRepositoryLanguages(username: string, repoName: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/repos/${username}/${repoName}/languages`);
  }
}
