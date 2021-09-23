import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Contact} from "../model/contact";

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  private readonly contactUrl = '/api/contact';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  constructor(private httpClient: HttpClient) {
  }

  addContact(contact: Contact): Observable<Contact> {
    return this.httpClient.post<Contact>(this.contactUrl, contact, this.httpOptions);
  }

  getContact(contactId: number): Observable<Contact> {
    const url = `${this.contactUrl}/${contactId}`;
    return this.httpClient.get<Contact>(url);
  }

  getContacts():Observable<Contact[]>{
    return this.httpClient.get<Contact[]>(this.contactUrl);
  }

  removeContact(contactId:number):Observable<any>{
    const url = `${this.contactUrl}/${contactId}`;
    console.log(url);
    return this.httpClient.delete<any>(url, this.httpOptions);
  }
}
