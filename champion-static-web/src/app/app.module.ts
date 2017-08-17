import { NgModule }             from '@angular/core';
import { BrowserModule }        from '@angular/platform-browser';
import { FormsModule }          from '@angular/forms';
import { RouterModule } from '@angular/router';
import { appRoutes } from './route/appRoutes.component';
import { AppComponent }          from './app.component';
import { LoginComponent }   from './login/login.component';
import { WelcomeComponent }   from './welcome/welcome.component';
import { PageNotFoundComponent } from './not-found.component';
import { HttpModule,JsonpModule } from '@angular/http';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    HttpModule,
    JsonpModule
  ],
  declarations: [
    AppComponent,
    LoginComponent,
    WelcomeComponent,
    PageNotFoundComponent
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
