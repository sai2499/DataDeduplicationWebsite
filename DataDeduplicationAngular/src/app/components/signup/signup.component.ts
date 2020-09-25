import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SignupService } from 'src/app/services/signupService/signup.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

    public username: string;
    public passWord: string;
    public emailid: string;
    
    constructor(
    private router:Router,
    private signupservice :SignupService
  ) { }

  ngOnInit() {
  }
  signUpCred(data)
  {
    console.log(data.username)
    console.log(data.passWord)
    console.log(data.emailid)
    this.signupservice.insertUser(data.username,data.passWord,data.emailid)
    .subscribe(
      response =>{
        console.log(response)
        this.router.navigate(["mainpage"]);
      }
    )
  }
}
