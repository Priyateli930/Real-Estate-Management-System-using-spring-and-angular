import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Properties } from '../models/properties';
import { PropImages } from '../models/prop-images';
import { Users } from '../models/users';


@Injectable({
  providedIn: 'root'
})
export class UploadedPropertyService {

  private apiUrl = 'http://localhost:8081/api/v1/rems/services';

  constructor(private http: HttpClient) { }

  getAllProperties(pageNo: number, pageSize: number, sortBy: string): Observable<Properties[]> {
    const jwtToken = localStorage.getItem('jwtToken');
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${jwtToken}`
      })
    };
//changes just now from owner to forall
    return this.http.get<Properties[]>(`${this.apiUrl}/forboth/properties/${pageNo}/${pageSize}/${sortBy}`, httpOptions);
  }

  getPropertyImages(propertyId: number): Observable<PropImages[]> {
    const jwtToken = localStorage.getItem('jwtToken');
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${jwtToken}`
      })
    };

    return this.http.get<PropImages[]>(`${this.apiUrl}/forall/showpropertyimages/${propertyId}`, httpOptions);
  }


  updateProperty(propertyId: number, propertyDetails: Properties): Observable<Properties> {
    const jwtToken = localStorage.getItem('jwtToken');
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${jwtToken}`
      })
    };

    return this.http.put<Properties>(`${this.apiUrl}/forboth/updateproperties/${propertyId}`, propertyDetails, httpOptions);
  }

  deleteProperty(propertyId: number): Observable<{}> {
    const jwtToken = localStorage.getItem('jwtToken');
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${jwtToken}`
      })
    };

    return this.http.delete(`${this.apiUrl}/forboth/deleteproperties/${propertyId}`, httpOptions);
  }

  updatePropertyImages(imageId: number, imageDetails: PropImages): Observable<PropImages> {
    const jwtToken = localStorage.getItem('jwtToken');
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${jwtToken}`
      })
    };

    return this.http.put<PropImages>(`${this.apiUrl}/owner/updatepropertyimages/${imageId}`, imageDetails, httpOptions);
  }

  deletePropertyImages(imageId: number): Observable<{}> {
    const jwtToken = localStorage.getItem('jwtToken');
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${jwtToken}`
      })
    };

    return this.http.delete(`${this.apiUrl}/owner/deletepropertyimages/${imageId}`, httpOptions);
  }



  // getAllPropertiesByOwnerOrAgent(pageNo: number, pageSize: number, sortBy: string, userId: Users): Observable<Properties[]> {
  //   const jwtToken = localStorage.getItem('jwtToken');
  //   const httpOptions = {
  //     headers: new HttpHeaders({
  //       'Authorization': `Bearer ${jwtToken}`
  //     })
  //   };
  //   return this.http.get<Properties[]>(`${this.apiUrl}/owner/propertiesbyownerid?userId=${userId}&pageNo=${pageNo}&pageSize=${pageSize}&sortBy=${sortBy}`, httpOptions);
  // }



}
