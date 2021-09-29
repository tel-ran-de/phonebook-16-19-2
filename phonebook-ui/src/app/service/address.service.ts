import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Address} from "../model/address";

@Injectable({
  providedIn: 'root'
})
export class AddressService {
  private readonly addressUrl = 'api/address';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  constructor(private httpClient: HttpClient) {
  }

  updateAddress(address: Address): Observable<Address> {
    return this.httpClient.put<Address>(`${this.addressUrl}/${address.id}`, address, this.httpOptions);
  }

  addAddress(address: Address): Observable<Address> {
    return this.httpClient.post<Address>(this.addressUrl, address, this.httpOptions);
  }

  getAddresses(contactId: number): Observable<Address[]> {
    return this.httpClient.get<Address[]>(this.addressUrl + '/' + contactId + '/all')
  }

  deleteAddress(addressId: number): Observable<void> {
    const url = `${this.addressUrl}/${addressId}`;
    return this.httpClient.delete<void>(url, this.httpOptions);
  }
}
