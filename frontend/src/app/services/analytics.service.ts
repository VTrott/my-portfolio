import { Injectable } from '@angular/core';

declare let gtag: Function;

@Injectable({
  providedIn: 'root'
})
export class AnalyticsService {
  private isGA4Loaded = false;

  constructor() {
    this.loadGA4();
  }

  private loadGA4() {
    const script = document.createElement('script');
    script.async = true;
    script.src = 'https://www.googletagmanager.com/gtag/js?id=GA_MEASUREMENT_ID';
    document.head.appendChild(script);

    window.dataLayer = window.dataLayer || [];
    function gtag(...args: any[]) {
      window.dataLayer.push(args);
    }
    window.gtag = gtag;

    gtag('js', new Date());
    gtag('config', 'GA_MEASUREMENT_ID', {
      page_title: document.title,
      page_location: window.location.href
    });

    this.isGA4Loaded = true;
  }

  trackPageView(pageName: string, pagePath: string) {
    if (this.isGA4Loaded && typeof gtag !== 'undefined') {
      gtag('config', 'GA_MEASUREMENT_ID', {
        page_title: pageName,
        page_location: window.location.origin + pagePath
      });
    }
  }

  trackEvent(eventName: string, parameters?: { [key: string]: any }) {
    if (this.isGA4Loaded && typeof gtag !== 'undefined') {
      gtag('event', eventName, parameters);
    }
  }

  trackProjectView(projectTitle: string) {
    this.trackEvent('project_view', {
      project_title: projectTitle,
      event_category: 'Portfolio',
      event_label: 'Project Interaction'
    });
  }

  trackGitHubClick(repoName: string) {
    this.trackEvent('github_click', {
      repository_name: repoName,
      event_category: 'Portfolio',
      event_label: 'GitHub Interaction'
    });
  }

  trackContactFormSubmit() {
    this.trackEvent('contact_form_submit', {
      event_category: 'Contact',
      event_label: 'Form Submission'
    });
  }

  trackResumeDownload() {
    this.trackEvent('resume_download', {
      event_category: 'Portfolio',
      event_label: 'Resume Download'
    });
  }

  trackEmailClick() {
    this.trackEvent('email_click', {
      event_category: 'Contact',
      event_label: 'Email Interaction'
    });
  }

  trackLinkedInClick() {
    this.trackEvent('linkedin_click', {
      event_category: 'Social',
      event_label: 'LinkedIn Interaction'
    });
  }

  trackGitHubProfileClick() {
    this.trackEvent('github_profile_click', {
      event_category: 'Social',
      event_label: 'GitHub Profile Interaction'
    });
  }

  trackScrollDepth(depth: number) {
    this.trackEvent('scroll_depth', {
      scroll_depth: depth,
      event_category: 'Engagement',
      event_label: 'Page Scroll'
    });
  }

  trackTimeOnPage(timeInSeconds: number) {
    this.trackEvent('time_on_page', {
      time_in_seconds: timeInSeconds,
      event_category: 'Engagement',
      event_label: 'Page Duration'
    });
  }
}

declare global {
  interface Window {
    dataLayer: any[];
    gtag: Function;
  }
}
