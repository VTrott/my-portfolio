import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { AboutService } from '../../services/about.service';
import { AnalyticsService } from '../../services/analytics.service';
import { About } from '../../models/about.model';

@Component({
  selector: 'app-about',
  standalone: true,
  imports: [CommonModule, MatButtonModule, MatIconModule, MatChipsModule, MatProgressSpinnerModule],
  templateUrl: './about.html',
  styleUrl: './about.scss'
})
export class AboutComponent implements OnInit {
  about: About | null = null;
  loading = true;
  error: string | null = null;
  showContactInfo = false;

  constructor(
    private aboutService: AboutService,
    private analyticsService: AnalyticsService
  ) {}

  ngOnInit() {
    this.loadAbout();
  }

  loadAbout() {
    this.aboutService.getAbout().subscribe({
      next: (about) => {
        if (about && typeof about === 'object') {
          this.about = about;
        } else {
          this.about = null;
        }
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading about information:', error);
        this.error = 'Failed to load about information';
        this.loading = false;
        this.about = null;
      }
    });
  }

  openLink(url: string | undefined, linkType: 'email' | 'linkedin' | 'github' | 'resume') {
    if (url) {
      switch (linkType) {
        case 'email':
          this.analyticsService.trackEmailClick();
          break;
        case 'linkedin':
          this.analyticsService.trackLinkedInClick();
          break;
        case 'github':
          this.analyticsService.trackGitHubProfileClick();
          break;
        case 'resume':
          this.analyticsService.trackResumeDownload();
          break;
      }
      window.open(url, '_blank', 'noopener,noreferrer');
    }
  }

  toggleContactInfo() {
    this.showContactInfo = !this.showContactInfo;
    if (this.showContactInfo) {
      this.analyticsService.trackEvent('phone_number_revealed', {
        event_category: 'Contact',
        event_label: 'Phone Number Access'
      });
    }
  }
}