import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient
    ) {
      
     }
    checkLogin(username,passWord)
    {
      return this.http.get(`http://localhost:9090/data/${username}/${passWord}/filepage`);
    }
}
