import { Component } from '@angular/core';
import { Http,Headers,RequestOptions } from '@angular/http';
import { Router } from '@angular/router';

import { tomcatLoginUrl } from "../route/appRoutesUrl";

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['css/reset.css','css/style.css']
})

export class LoginComponent {

  loginUser:LoginUser = new LoginUser;
  constructor(private http:Http,private router:Router){}

  submitLogin(isVailed:boolean){
    if(isVailed){
      let headers = new Headers({ 'Content-Type': 'application/json' });

      let options = new RequestOptions({ headers: headers });
      this.http.post( tomcatLoginUrl, JSON.stringify(this.loginUser), options).subscribe(data => {
        var body = data.json();
        if(body != 0){
          this.router.navigate(['welcome'],{ queryParams: { userName: body.userName } });
        }
      })
    }
  }
}
export class LoginUser{
  userName:String;
  passWord:String
}
