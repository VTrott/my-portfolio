import { Component, Input, Output, EventEmitter, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { ImageUploadService, UploadProgress, UploadResult } from '../../services/image-upload.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-image-upload',
  imports: [
    CommonModule,
    MatButtonModule,
    MatIconModule,
    MatProgressBarModule,
    MatSnackBarModule
  ],
  templateUrl: './image-upload.html',
  styleUrl: './image-upload.scss'
})
export class ImageUploadComponent implements OnInit, OnDestroy {
  @Input() currentImageUrl: string = '';
  @Input() uploadType: 'profile' | 'project' = 'profile';
  @Input() disabled: boolean = false;
  @Output() imageUploaded = new EventEmitter<string>();
  @Output() imageRemoved = new EventEmitter<void>();

  selectedFile: File | null = null;
  previewUrl: string = '';
  isUploading: boolean = false;
  uploadProgress: number = 0;
  private uploadProgressSubscription: Subscription = new Subscription();

  constructor(
    private imageUploadService: ImageUploadService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit() {
    this.uploadProgressSubscription = this.imageUploadService.uploadProgress$.subscribe(
      (progress: UploadProgress) => {
        this.uploadProgress = progress.percentage;
      }
    );
  }

  ngOnDestroy() {
    this.uploadProgressSubscription.unsubscribe();
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      const validation = this.imageUploadService.validateImage(file);
      if (!validation.valid) {
        this.snackBar.open(validation.error || 'Invalid file', 'Close', {
          duration: 3000,
          horizontalPosition: 'center',
          verticalPosition: 'top'
        });
        return;
      }

      this.selectedFile = file;
      this.generatePreview();
    }
  }

  private generatePreview() {
    if (this.selectedFile) {
      this.imageUploadService.generatePreview(this.selectedFile).subscribe({
        next: (previewUrl) => {
          this.previewUrl = previewUrl;
        },
        error: (error) => {
          console.error('Error generating preview:', error);
        }
      });
    }
  }

  uploadImage() {
    if (this.selectedFile && !this.isUploading) {
      this.isUploading = true;
      this.uploadProgress = 0;

      this.imageUploadService.uploadImage(this.selectedFile, this.uploadType).subscribe({
        next: (result: UploadResult) => {
          if (result.success && result.url) {
            this.imageUploaded.emit(result.url);
            this.snackBar.open('Image uploaded successfully!', 'Close', {
              duration: 3000,
              horizontalPosition: 'center',
              verticalPosition: 'top'
            });
            this.resetUpload();
          } else {
            this.snackBar.open(result.error || 'Upload failed', 'Close', {
              duration: 3000,
              horizontalPosition: 'center',
              verticalPosition: 'top'
            });
          }
          this.isUploading = false;
        },
        error: (error) => {
          console.error('Upload error:', error);
          this.snackBar.open('Upload failed. Please try again.', 'Close', {
            duration: 3000,
            horizontalPosition: 'center',
            verticalPosition: 'top'
          });
          this.isUploading = false;
        }
      });
    }
  }

  removeImage() {
    this.imageRemoved.emit();
    this.resetUpload();
  }

  cancelUpload() {
    this.resetUpload();
  }

  private resetUpload() {
    this.selectedFile = null;
    this.previewUrl = '';
    this.uploadProgress = 0;
    this.imageUploadService.resetProgress();
  }

  getDisplayImage(): string {
    if (this.previewUrl) {
      return this.previewUrl;
    }
    return this.currentImageUrl || 'assets/default-avatar.svg';
  }

  hasImage(): boolean {
    return !!(this.currentImageUrl || this.previewUrl);
  }

  isImageSelected(): boolean {
    return !!this.selectedFile;
  }
}
