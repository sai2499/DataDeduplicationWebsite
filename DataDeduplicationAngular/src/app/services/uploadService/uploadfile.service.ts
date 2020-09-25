import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UploadfileService 
{
  constructor(private http:HttpClient)
  {}

  uploadFile(formData: FormData): Observable<any> 
  {
    return this.http.post(`http://localhost:9090/upload`,formData,{responseType: 'text'});
  }

  updateFile(formData: FormData): Observable<any>
  {
    return this.http.post(`http://localhost:9090/update`,formData,{responseType: 'text'});
  }
}
