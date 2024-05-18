import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ContactAssistanceService {


  private apiUrl = 'http://localhost:8081/api/v1/rems/services';

  constructor(private http: HttpClient) { }

  addContactAssistance(contactAssistance: any): Observable<any> {
    const jwtToken = localStorage.getItem('jwtToken'); // Get the JWT from local storage
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${jwtToken}`
      })
    };

    return this.http.post(`${this.apiUrl}/tenant/addcontactassistance`, contactAssistance, httpOptions);
  }
}
