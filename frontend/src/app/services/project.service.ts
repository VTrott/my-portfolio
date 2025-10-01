import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Project } from '../models/project.model';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  private apiUrl = 'http://localhost:8080/api/projects';

  constructor(private http: HttpClient) { }

  getAllProjects(): Observable<Project[]> {
    return this.http.get<Project[]>(this.apiUrl);
  }

  getFeaturedProjects(): Observable<Project[]> {
    return this.http.get<Project[]>(`${this.apiUrl}/featured`);
  }

  getProjectById(id: number): Observable<Project> {
    return this.http.get<Project>(`${this.apiUrl}/${id}`);
  }

  createProject(project: Project): Observable<Project> {
    return this.http.post<Project>(this.apiUrl, project);
  }

  updateProject(id: number, project: Project): Observable<Project> {
    return this.http.put<Project>(`${this.apiUrl}/${id}`, project);
  }

  deleteProject(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  searchProjects(keyword: string): Observable<Project[]> {
    return this.http.get<Project[]>(`${this.apiUrl}/search?keyword=${encodeURIComponent(keyword)}`);
  }

  filterProjectsBySkill(skill: string): Observable<Project[]> {
    return this.http.get<Project[]>(`${this.apiUrl}/filter?skill=${encodeURIComponent(skill)}`);
  }

  getAllSkills(): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiUrl}/skills`);
  }
}
