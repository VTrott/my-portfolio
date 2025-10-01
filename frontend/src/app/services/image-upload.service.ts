import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpEventType, HttpProgressEvent } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

export interface UploadProgress {
  loaded: number;
  total: number;
  percentage: number;
}

export interface UploadResult {
  success: boolean;
  url?: string;
  error?: string;
}

@Injectable({
  providedIn: 'root'
})
export class ImageUploadService {
  private apiUrl = 'http://localhost:8080/api/upload';
  private uploadProgress = new BehaviorSubject<UploadProgress>({ loaded: 0, total: 0, percentage: 0 });
  
  public uploadProgress$ = this.uploadProgress.asObservable();

  constructor(private http: HttpClient) {}

  uploadImage(file: File, type: 'profile' | 'project'): Observable<UploadResult> {
    const formData = new FormData();
    formData.append('file', file);
    formData.append('type', type);

    return this.http.post<UploadResult>(`${this.apiUrl}/image`, formData, {
      reportProgress: true,
      observe: 'events'
    }).pipe(
      map((event: HttpEvent<any>) => {
        if (event.type === HttpEventType.UploadProgress) {
          const progress = event as HttpProgressEvent;
          const percentage = Math.round(100 * progress.loaded / (progress.total || 1));
          this.uploadProgress.next({
            loaded: progress.loaded,
            total: progress.total || 0,
            percentage: percentage
          });
        } else if (event.type === HttpEventType.Response) {
          return event.body;
        }
        return { success: false, error: 'Upload in progress' };
      }),
      catchError(error => {
        console.error('Upload error:', error);
        return [{ success: false, error: 'Upload failed' }];
      })
    );
  }

 
  generatePreview(file: File): Observable<string> {
    return new Observable(observer => {
      const reader = new FileReader();
      reader.onload = () => {
        observer.next(reader.result as string);
        observer.complete();
      };
      reader.onerror = error => {
        observer.error(error);
      };
      reader.readAsDataURL(file);
    });
  }


  validateImage(file: File): { valid: boolean; error?: string } {
    const maxSize = 5 * 1024 * 1024; // 5MB
    const allowedTypes = ['image/jpeg', 'image/jpg', 'image/png', 'image/gif', 'image/webp'];
    
    if (!allowedTypes.includes(file.type)) {
      return { valid: false, error: 'Invalid file type. Please upload a JPEG, PNG, GIF, or WebP image.' };
    }
    
    if (file.size > maxSize) {
      return { valid: false, error: 'File too large. Please upload an image smaller than 5MB.' };
    }
    
    return { valid: true };
  }

  
  resetProgress() {
    this.uploadProgress.next({ loaded: 0, total: 0, percentage: 0 });
  }

  getOptimizedImageUrl(originalUrl: string, width?: number, height?: number): string {
    
    if (width && height) {
      return `${originalUrl}?w=${width}&h=${height}&fit=crop&auto=format`;
    }
    return originalUrl;
  }

  getThumbnailUrl(originalUrl: string): string {
    return this.getOptimizedImageUrl(originalUrl, 300, 200);
  }


  getProfileImageUrl(originalUrl: string): string {
    return this.getOptimizedImageUrl(originalUrl, 300, 300);
  }
}
