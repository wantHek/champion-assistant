import { Component } from '@angular/core';
import { Location }from '@angular/common'
import {ActivatedRoute, Params} from '@angular/router';

@Component({
  selector: 'welcome',
  templateUrl: './welcome.component.html',
  styleUrls: []
})

export class WelcomeComponent {
  userName:String;
  constructor(
    private route: ActivatedRoute
  ) {
    // this.route.params.subscribe((params: Params) => {
    //   console.log(params);
    //   this.userName = params['userName'];
    // });
    // 这种是直接获取queryParam
    this.route.queryParams.subscribe(
      params => {
        this.userName = params.userName;
      }
    );
  }


}

