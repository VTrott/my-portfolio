import { Routes } from '@angular/router';
import { AboutComponent } from './components/about/about';
import { Projects } from './components/projects/projects';
import { Experience } from './components/experience/experience';
import { Contact } from './components/contact/contact';

export const routes: Routes = [
  { path: '', redirectTo: '/about', pathMatch: 'full' },
  { path: 'about', component: AboutComponent },
  { path: 'projects', component: Projects },
  { path: 'experience', component: Experience },
  { path: 'contact', component: Contact },
  { path: '**', redirectTo: '/about' }
];
