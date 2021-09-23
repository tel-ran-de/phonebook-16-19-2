import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {Phone} from "../model/phone";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class PhoneService {
  private readonly phoneUrl = '/api/phone';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  constructor(private httpClient: HttpClient) {
  }

  getPhones(contactId: number): Observable<Phone[]> {
    const url = `${this.phoneUrl}?contactId=${contactId}`;
    return this.httpClient.get<Phone[]>(url);
  }

  addPhone(phone: Phone): Observable<Phone> {
    return this.httpClient.post<Phone>(this.phoneUrl, phone, this.httpOptions);
  }

  deletePhone(id: number): Observable<Phone> {
    const url = `${this.phoneUrl}/${id}`;
    return this.httpClient.delete<Phone>(url, this.httpOptions);
  }
  updatePhone( phone:Phone):Observable<any>{
    const url=`${this.phoneUrl}/${phone.id}`;
    return this.httpClient.put(url,phone,this.httpOptions);
  }

}
