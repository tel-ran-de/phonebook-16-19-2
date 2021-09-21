import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Contact} from "../model/contact";

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  constructor(private httpClient: HttpClient) {
  }

  private contactUrl = '/api/contacts'
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  addContact(contact: Contact): Observable<Contact> {
    console.log(contact);
    return this.httpClient.post<Contact>(this.contactUrl, contact, this.httpOptions);
  }
}
