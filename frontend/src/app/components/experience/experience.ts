import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { ExperienceService } from '../../services/experience.service';
import { Experience as ExperienceModel } from '../../models/experience.model';

@Component({
  selector: 'app-experience',
  imports: [
    CommonModule,
    MatCardModule,
    MatIconModule,
    MatChipsModule,
    MatProgressSpinnerModule
  ],
  templateUrl: './experience.html',
  styleUrl: './experience.scss'
})
export class Experience implements OnInit {
  experiences: ExperienceModel[] = [];
  loading = true;
  error: string | null = null;

  constructor(private experienceService: ExperienceService) {}

  ngOnInit() {
    this.loadExperiences();
  }

  loadExperiences() {
    this.experienceService.getAllExperiences().subscribe({
      next: (experiences) => {
        this.experiences = experiences;
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading experiences:', error);
        this.error = 'Failed to load experience information';
        this.loading = false;
      }
    });
  }

  getSkillsArray(skills: string | undefined): string[] {
    return skills ? skills.split(',').map(s => s.trim()) : [];
  }

  formatDate(dateString: string | undefined): string {
    if (!dateString) return '';
    const date = new Date(dateString);
    return date.toLocaleDateString('en-US', { 
      year: 'numeric', 
      month: 'short' 
    });
  }
}
