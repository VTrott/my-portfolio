import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, retry, delay } from 'rxjs';
import { About } from '../models/about.model';

@Injectable({
  providedIn: 'root'
})
export class AboutService {
  private apiUrl = 'http://localhost:8080/api/about';

  constructor(private http: HttpClient) { }

  getAbout(): Observable<About> {
    return this.http.get<About>(this.apiUrl).pipe(
      retry(3),
      delay(1000),
      catchError(error => {
        console.error('Error fetching about data:', error);
        throw error;
      })
    );
  }

  createAbout(about: About): Observable<About> {
    return this.http.post<About>(this.apiUrl, about);
  }

  updateAbout(id: number, about: About): Observable<About> {
    return this.http.put<About>(`${this.apiUrl}/${id}`, about);
  }
}
