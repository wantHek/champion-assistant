import { Routes } from '@angular/router';
import { LoginComponent }   from '../login/login.component';
import { PageNotFoundComponent } from '../not-found.component';

export const appRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '',   redirectTo: '/login', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];

