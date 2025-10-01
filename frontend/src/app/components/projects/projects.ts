import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatTabsModule } from '@angular/material/tabs';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { ProjectService } from '../../services/project.service';
import { GitHubService, GitHubRepository, GitHubProfile } from '../../services/github.service';
import { AnalyticsService } from '../../services/analytics.service';
import { Project } from '../../models/project.model';

@Component({
  selector: 'app-projects',
  imports: [
    CommonModule, 
    MatCardModule, 
    MatButtonModule, 
    MatIconModule, 
    MatChipsModule,
    MatProgressSpinnerModule,
    MatTabsModule,
    MatSelectModule,
    MatFormFieldModule,
    FormsModule
  ],
  templateUrl: './projects.html',
  styleUrl: './projects.scss'
})
export class Projects implements OnInit {
  projects: Project[] = [];
  featuredProjects: Project[] = [];
  allProjects: Project[] = [];
  filteredProjects: Project[] = [];
  availableSkills: string[] = [];
  selectedSkill = '';
  loading = true;
  error: string | null = null;
  selectedTab = 0;
  githubRepos: GitHubRepository[] = [];
  githubProfile: GitHubProfile | null = null;
  showGitHubRepos = false;
  githubLoading = false;
  githubError: string | null = null;

  constructor(
    private projectService: ProjectService,
    private githubService: GitHubService,
    private analyticsService: AnalyticsService
  ) {}

  ngOnInit() {
    this.loadProjects();
    this.loadSkills();
  }

  loadProjects() {
    this.projectService.getAllProjects().subscribe({
      next: (projects) => {
        this.allProjects = projects;
        this.projects = projects;
        this.filteredProjects = projects;
        this.featuredProjects = projects.filter(p => p.isFeatured);
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading projects:', error);
        this.error = 'Failed to load projects';
        this.loading = false;
      }
    });
  }

  loadSkills() {
    this.projectService.getAllSkills().subscribe({
      next: (skills) => {
        this.availableSkills = skills;
      },
      error: (error) => {
        console.error('Error loading skills:', error);
      }
    });
  }

  onSkillFilterChange() {
    if (this.selectedSkill) {
      this.projectService.filterProjectsBySkill(this.selectedSkill).subscribe({
        next: (projects) => {
          this.filteredProjects = projects;
          this.projects = projects;
        },
        error: (error) => {
          console.error('Error filtering projects:', error);
        }
      });
    } else {
      this.projects = this.allProjects;
      this.filteredProjects = this.allProjects;
    }
  }

  getTechnologiesArray(technologies: string | undefined): string[] {
    return technologies ? technologies.split(',').map(t => t.trim()) : [];
  }

  openLink(url: string | undefined) {
    if (url) {
      window.open(url, '_blank', 'noopener,noreferrer');
    }
  }

  openProjectLink(project: Project, linkType: 'github' | 'live') {
    const url = linkType === 'github' ? project.githubUrl : project.liveUrl;
    if (url) {
      this.analyticsService.trackProjectView(project.title);
      if (linkType === 'github') {
        this.analyticsService.trackGitHubClick(project.title);
      }
      window.open(url, '_blank', 'noopener,noreferrer');
    }
  }

  openGitHubRepo(repo: GitHubRepository) {
    this.analyticsService.trackGitHubClick(repo.name);
    window.open(repo.htmlUrl, '_blank', 'noopener,noreferrer');
  }

  loadGitHubRepos() {
    this.githubLoading = true;
    this.githubError = null;
    
    // Load both profile and repositories
    this.githubService.getUserProfile('VTrott').subscribe({
      next: (profile) => {
        this.githubProfile = profile;
      },
      error: (error) => {
        console.error('Error loading GitHub profile:', error);
      }
    });

    this.githubService.getUserRepositories('VTrott').subscribe({
      next: (repos) => {
        this.githubRepos = repos;
        this.showGitHubRepos = true;
        this.githubLoading = false;
      },
      error: (error) => {
        console.error('Error loading GitHub repositories:', error);
        this.githubError = 'Failed to load GitHub repositories';
        this.githubLoading = false;
      }
    });
  }

  getLanguageColor(language: string): string {
    const colors: { [key: string]: string } = {
      'JavaScript': '#f7df1e',
      'TypeScript': '#3178c6',
      'Java': '#007396',
      'Python': '#3776ab',
      'HTML': '#e34f26',
      'CSS': '#1572b6',
      'SCSS': '#cf649a',
      'Vue': '#4fc08d',
      'React': '#61dafb',
      'Angular': '#dd0031'
    };
    return colors[language] || '#6c757d';
  }

  formatDate(dateString: string): string {
    return new Date(dateString).toLocaleDateString();
  }

  formatGitHubDate(dateString: string): string {
    const date = new Date(dateString);
    const now = new Date();
    const diffTime = Math.abs(now.getTime() - date.getTime());
    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
    
    if (diffDays === 1) return 'yesterday';
    if (diffDays < 7) return `${diffDays} days ago`;
    if (diffDays < 30) return `${Math.ceil(diffDays / 7)} weeks ago`;
    if (diffDays < 365) return `${Math.ceil(diffDays / 30)} months ago`;
    return `${Math.ceil(diffDays / 365)} years ago`;
  }

  formatFileSize(bytes: number): string {
    if (bytes === 0) return '0 Bytes';
    const k = 1024;
    const sizes = ['Bytes', 'KB', 'MB', 'GB'];
    const i = Math.floor(Math.log(bytes) / Math.log(k));
    return parseFloat((bytes / Math.pow(k, i)).toFixed(2)) + ' ' + sizes[i];
  }

  getRepositoryTopics(topics: string[] | undefined): string[] {
    return topics || [];
  }

  isRepositoryActive(repo: GitHubRepository): boolean {
    return !repo.archived && !repo.disabled;
  }
}
