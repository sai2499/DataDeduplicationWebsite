import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/loginService/login.service';
import {NgModel} from '@angular/forms';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public username: String;
  public passWord: String;
  public loginCredCheck: boolean=false;
  constructor(
    private router: Router,
    private loginservice: LoginService
  ) { }

  ngOnInit() {
  }

  loginCred(data)
  {
    this.username=data.username
    console.log(this.username)
    this.passWord=data.passWord
    console.log(this.passWord)
   // this.router.navigate(["filepage",this.username,this.passWord])
    this.loginservice.checkLogin(data.username,data.passWord).subscribe(
      response =>{
        if(response==0)
        {
          this.loginCredCheck=true;
          this.username="";
          this.passWord="";
        }
        else
        {
          console.log(response)        
          this.router.navigate(["optionpage",response,data.username])
        }        
      }
    )
  }
}
