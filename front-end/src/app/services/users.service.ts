import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Users } from '../models/users';


@Injectable({
  providedIn: 'root'
})
export class UsersService {


  private baseUrl = 'http://localhost:8081/api/register';

  constructor(private http: HttpClient) { }

  registerUser(user: Users): Observable<any> {
    return this.http.post(`${this.baseUrl}`, user);
  }
}
