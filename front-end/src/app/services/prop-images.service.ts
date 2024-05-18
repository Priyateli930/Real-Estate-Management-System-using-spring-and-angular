import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class PropImagesService {

  private apiUrl = 'http://localhost:8081/api/v1/rems/services';

  constructor(private http: HttpClient) { }

  uploadImageOwner(propertyId: string, imageData: string) {
    const jwtToken = localStorage.getItem('jwtToken'); // Get the JWT from local storage
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${jwtToken}`,
        'Content-Type': 'application/json'
      })
    };

    const body = {
      propImages: imageData,
      propertyid: propertyId
    };

    return this.http.post(`${this.apiUrl}/owner/propimages`, body, httpOptions);
  }




  uploadImageBroker(propertyId: string, imageData: string) {
    const jwtToken = localStorage.getItem('jwtToken'); // Get the JWT from local storage
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${jwtToken}`,
        'Content-Type': 'application/json'
      })
    };

    const body = {
      propImages: imageData,
      propertyid: propertyId
    };

    return this.http.post(`${this.apiUrl}/broker/propimages`, body, httpOptions);
  }

}
