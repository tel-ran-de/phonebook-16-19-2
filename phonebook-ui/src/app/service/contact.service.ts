import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {Observable} from 'rxjs';
import {Contact} from "../model/contact";

@Injectable({
  providedIn: 'root'
})
export class ContactService {
  private contactsUrl = 'api/contacts';

  constructor(private http: HttpClient) {
  }

  getContact(id: number): Observable<Contact> {
    const url = `${this.contactsUrl}/${id}`;
    return this.http.get<Contact>(url);
  }

}
