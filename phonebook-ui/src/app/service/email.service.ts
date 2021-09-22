import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Email} from "../model/email";

@Injectable({
  providedIn: 'root'
})
export class EmailService {

  private readonly emailUrl = '/api/email';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  constructor(private httpClient: HttpClient) {
  }

  getEmails(contactId: number): Observable<Email[]> {
    //url for facked backend
    const url = `${this.emailUrl}?contactId=${contactId}`;
    //url for our backend
    // const url = `${this.emailUrl}/${contactId}`;
    return this.httpClient.get<Email[]>(url)
  }
}
