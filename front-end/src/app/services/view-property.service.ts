import { map, Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Properties } from '../models/properties';
import { HttpHeaders } from '@angular/common/http';
import { PropImages } from '../models/prop-images';
import { of } from 'rxjs';
import { catchError } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class ViewPropertyService {

  constructor(private http: HttpClient) { }

  getPropertiesOwner(city: string, minPrice: number, maxPrice: number, propstatus: string, propertytype: string, pageNo: number, pageSize: number, sortBy: string): Observable<Properties[]> {
    const url = `http://localhost:8081/api/v1/rems/services/owner/propertiesbyfilters/${city}/${minPrice}/${maxPrice}/${propstatus}/${propertytype}/${pageNo}/${pageSize}/${sortBy}`;
    const jwtToken = localStorage.getItem('jwtToken'); // Get the JWT from local storage
    const headers = new HttpHeaders().set('Authorization', `Bearer ${jwtToken}`); // Create headers with the JWT
    return this.http.get<any>(url, { headers }).pipe(
      catchError(error => {
        console.error('An error occurred:', error);
        return of([]); // Return an empty array when an error occurs
      })
    );
  }



getPropertiesBroker(city: string, minPrice: number, maxPrice: number, propstatus: string, propertytype: string, pageNo: number, pageSize: number, sortBy: string): Observable<Properties[]> {
  const url = `http://localhost:8081/api/v1/rems/services/broker/propertiesbyfilters/${city}/${minPrice}/${maxPrice}/${propstatus}/${propertytype}/${pageNo}/${pageSize}/${sortBy}`;
  const jwtToken = localStorage.getItem('jwtToken'); // Get the JWT from local storage
  const headers = new HttpHeaders().set('Authorization', `Bearer ${jwtToken}`); // Create headers with the JWT
  return this.http.get<any>(url, { headers }).pipe(
    catchError(error => {
      console.error('An error occurred:', error);
      return of([]); // Return an empty array when an error occurs
    })
  );
}




getPropertiesTenant(city: string, minPrice: number, maxPrice: number, propstatus: string, propertytype: string, pageNo: number, pageSize: number, sortBy: string): Observable<Properties[]> {
  const url = `http://localhost:8081/api/v1/rems/services/tenant/propertiesbyfilters/${city}/${minPrice}/${maxPrice}/${propstatus}/${propertytype}/${pageNo}/${pageSize}/${sortBy}`;
  const jwtToken = localStorage.getItem('jwtToken'); // Get the JWT from local storage
  const headers = new HttpHeaders().set('Authorization', `Bearer ${jwtToken}`); // Create headers with the JWT
  return this.http.get<any>(url, { headers }).pipe(
    catchError(error => {
      console.error('An error occurred:', error);
      return of([]); // Return an empty array when an error occurs
    })
  );
}

//.pipe(map((response: { content: any; }) => response.content));

getPropertyImages(id: number): Observable<PropImages[]> {
const url = `http://localhost:8081/api/v1/rems/services/forall/showpropertyimages/${id}`;
const jwtToken = localStorage.getItem('jwtToken'); // Get the JWT from local storage
const headers = new HttpHeaders().set('Authorization', `Bearer ${jwtToken}`); // Create headers with the JWT
return this.http.get<PropImages[]>(url, { headers });
}


}
