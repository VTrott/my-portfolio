import { Component, OnInit, OnDestroy } from '@angular/core';
import { RouterOutlet, NavigationEnd, Router } from '@angular/router';
import { HeaderComponent } from './components/header/header';
import { FooterComponent } from './components/footer/footer';
import { AnalyticsService } from './services/analytics.service';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderComponent, FooterComponent],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App implements OnInit, OnDestroy {
  private startTime: number = Date.now();

  constructor(
    private router: Router,
    private analyticsService: AnalyticsService
  ) {}

  ngOnInit() {
    // Track page views on route changes
    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe((event: NavigationEnd) => {
        this.analyticsService.trackPageView(
          this.getPageTitle(event.url),
          event.url
        );
      });

    // Track initial page load
    this.analyticsService.trackPageView('Home', '/');

    // Track scroll depth
    this.trackScrollDepth();

    // Track time on page before unload
    window.addEventListener('beforeunload', () => {
      const timeOnPage = Math.round((Date.now() - this.startTime) / 1000);
      this.analyticsService.trackTimeOnPage(timeOnPage);
    });
  }

  ngOnDestroy() {
    const timeOnPage = Math.round((Date.now() - this.startTime) / 1000);
    this.analyticsService.trackTimeOnPage(timeOnPage);
  }

  private getPageTitle(url: string): string {
    const routeMap: { [key: string]: string } = {
      '/': 'Home',
      '/about': 'About',
      '/projects': 'Projects',
      '/experience': 'Experience',
      '/contact': 'Contact'
    };
    return routeMap[url] || 'Portfolio';
  }

  private trackScrollDepth() {
    let maxScroll = 0;
    const scrollThresholds = [25, 50, 75, 90, 100];
    const trackedThresholds = new Set<number>();

    window.addEventListener('scroll', () => {
      const scrollTop = window.pageYOffset || document.documentElement.scrollTop;
      const documentHeight = document.documentElement.scrollHeight - window.innerHeight;
      const scrollPercent = Math.round((scrollTop / documentHeight) * 100);

      if (scrollPercent > maxScroll) {
        maxScroll = scrollPercent;
      }

      scrollThresholds.forEach(threshold => {
        if (scrollPercent >= threshold && !trackedThresholds.has(threshold)) {
          trackedThresholds.add(threshold);
          this.analyticsService.trackScrollDepth(threshold);
        }
      });
    });
  }
}
