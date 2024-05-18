import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ratings } from '../models/ratings';
import { Page } from '../models/page';


@Injectable({
  providedIn: 'root'
})
export class RatingsService {

  private apiUrl = 'http://localhost:8081/api/v1/rems/services/tenant';

  constructor(private http: HttpClient) { }

  addRating(rating: any): Observable<any> {
    const jwtToken = localStorage.getItem('jwtToken');
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${jwtToken}`
      })
    };

    return this.http.post(`${this.apiUrl}/addratings`, rating, httpOptions);
  }

}
