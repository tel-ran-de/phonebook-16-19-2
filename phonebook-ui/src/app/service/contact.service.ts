import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {Contact} from "../model/contact";

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  constructor(private httpClient: HttpClient) {
  }

  private contactUrl = '/api/contact'
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  getContacts(): Observable<Contact[]> {
    return this.httpClient.get<Contact[]>(this.contactUrl);
  }

  getContact(id: number): Observable<Contact> {
    const url = `${this.contactUrl}/${id}`;
    return this.httpClient.get<Contact>(url);
  }

  updateContact(contact: Contact): Observable<any> {
    const url = `${this.contactUrl}/${contact.id}`;
    return this.httpClient.put(url, contact, this.httpOptions);
  }

  addContact(contact: Contact): Observable<Contact | any> {
    return this.httpClient.post<Contact>(this.contactUrl, contact, this.httpOptions);
  }

  deleteContact(id: number): Observable<Contact> {
    const url = `${this.contactUrl}/${id}`;
    return this.httpClient.delete<Contact>(url, this.httpOptions);
  }

  searchContact(term: string): Observable<Contact[]> {
    if (!term.trim()) {
      return of([]);
    }
    return this.httpClient.get<Contact[]>(`${this.contactUrl}/?name=${term}`);
  }
}
