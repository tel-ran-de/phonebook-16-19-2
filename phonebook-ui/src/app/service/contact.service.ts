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

  updateContact(contact: Contact | undefined): Observable<Contact> {
    return this.httpClient.put<Contact>(this.contactUrl, contact, this.httpOptions);
  }

}
