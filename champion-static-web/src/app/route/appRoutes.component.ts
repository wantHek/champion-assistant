import { Routes } from '@angular/router';
import { LoginComponent }   from '../login/login.component';
import { WelcomeComponent }   from '../welcome/welcome.component';
import { PageNotFoundComponent } from '../not-found.component';



export const appRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'welcome', component: WelcomeComponent, },
  { path: '',   redirectTo: '/login', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];

