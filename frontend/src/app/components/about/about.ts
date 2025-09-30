import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';
import { AboutService } from '../../services/about.service';
import { About } from '../../models/about.model';

@Component({
  selector: 'app-about',
  standalone: true,
  imports: [CommonModule, MatButtonModule, MatIconModule, MatChipsModule],
  templateUrl: './about.html',
  styleUrl: './about.scss'
})
export class AboutComponent implements OnInit {
  about: About | null = null;
  loading = true;
  error: string | null = null;

  constructor(private aboutService: AboutService) {}

  ngOnInit() {
    this.loadAbout();
  }

  loadAbout() {
    this.aboutService.getAbout().subscribe({
      next: (about) => {
        this.about = about;
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading about information:', error);
        this.error = 'Failed to load about information';
        this.loading = false;
      }
    });
  }
}