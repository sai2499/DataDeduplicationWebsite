import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  constructor(
    private http: HttpClient
  ) { }

  insertUser(username,passWord,emailid)
  {
    return this.http.get(`http://localhost:9090/data/${username}/${passWord}/${emailid}/filepage`);
  }
}
