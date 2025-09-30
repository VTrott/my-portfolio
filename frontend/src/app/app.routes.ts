import { Routes } from '@angular/router';
import { AboutComponent } from './components/about/about';
import { ProjectsComponent } from './components/projects/projects';
import { ExperienceComponent } from './components/experience/experience';
import { ContactComponent } from './components/contact/contact';

export const routes: Routes = [
  { path: '', redirectTo: '/about', pathMatch: 'full' },
  { path: 'about', component: AboutComponent },
  { path: 'projects', component: ProjectsComponent },
  { path: 'experience', component: ExperienceComponent },
  { path: 'contact', component: ContactComponent },
  { path: '**', redirectTo: '/about' }
];
