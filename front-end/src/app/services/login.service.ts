import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Users } from '../models/users';

export interface LoginResponse {
  jwt: string;
}

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private apiUrl = 'http://localhost:8081/api/login';
  private apiUrll = 'http://localhost:8081/api/v1/rems/services';

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(this.apiUrl, { username, password })
      .pipe(
        tap(response => {
          localStorage.setItem('jwtToken', response.jwt);
        })
      );
  }

  getUserByEmail(email: string): Observable<Users> {
    return this.http.get<Users>(`http://localhost:8081/api/v1/rems/services/forall/userbyemail/${email}`);
  }



  getUserById(userId: string): Observable<Users> {
    const jwtToken = localStorage.getItem('jwtToken'); // Get the JWT from local storage
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${jwtToken}`
      })
    };

    return this.http.get<Users>(`${this.apiUrll}/forall/usersbyid/${userId}`, httpOptions);
  }

  isLoggedIn(): boolean {
    return localStorage.getItem('email') !== null;
  }

}
