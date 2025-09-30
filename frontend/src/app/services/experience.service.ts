import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Experience } from '../models/experience.model';

@Injectable({
  providedIn: 'root'
})
export class ExperienceService {
  private apiUrl = 'http://localhost:8080/api/experiences';

  constructor(private http: HttpClient) { }

  getAllExperiences(): Observable<Experience[]> {
    return this.http.get<Experience[]>(this.apiUrl);
  }

  getCurrentExperiences(): Observable<Experience[]> {
    return this.http.get<Experience[]>(`${this.apiUrl}/current`);
  }

  getExperienceById(id: number): Observable<Experience> {
    return this.http.get<Experience>(`${this.apiUrl}/${id}`);
  }

  createExperience(experience: Experience): Observable<Experience> {
    return this.http.post<Experience>(this.apiUrl, experience);
  }

  updateExperience(id: number, experience: Experience): Observable<Experience> {
    return this.http.put<Experience>(`${this.apiUrl}/${id}`, experience);
  }

  deleteExperience(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
