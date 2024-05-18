import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PropertyTransactionsService {

  private apiUrl = 'http://localhost:8081/api/v1/rems/services';

  constructor(private http: HttpClient) { }

  addTransaction(transaction: any): Observable<any> {
    const jwtToken = localStorage.getItem('jwtToken');
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${jwtToken}`
      })
    };

    return this.http.post(`${this.apiUrl}/forboth/addpropertytransactions`, transaction, httpOptions);
  }


  getTransactions(userId: string): Observable<any> {
    const jwtToken = localStorage.getItem('jwtToken');
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${jwtToken}`
      })
    };
    return this.http.get(`${this.apiUrl}/forboth/propertytransactions/0/10/transactiondate`, httpOptions);
  }



  updateTransaction(id: number, updatedTransaction: any): Observable<any> {
    const jwtToken = localStorage.getItem('jwtToken');
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${jwtToken}`
      })
    };
    return this.http.put(`${this.apiUrl}/forboth/updatepropertytransactions/${id}`, updatedTransaction, httpOptions);
  }


  deleteTransaction(id: number): Observable<any> {
    const jwtToken = localStorage.getItem('jwtToken');
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${jwtToken}`
      })
    };
    return this.http.delete(`${this.apiUrl}/forboth/deletepropertytransactions/${id}`, httpOptions);
  }



  getTransactionsByPropertyId(propertyId: number): Observable<any> {
    const jwtToken = localStorage.getItem('jwtToken');
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${jwtToken}`
      })
    };
    return this.http.get(`${this.apiUrl}/forboth/propertytransactionsbypropertyid/${propertyId}`, httpOptions);
  }



}
