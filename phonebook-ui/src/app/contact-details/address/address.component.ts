import {Component, OnInit} from '@angular/core';
import {Phone} from "../../model/phone";
import {Address} from "../../model/address";

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['../contact-details.component.css']
})
export class AddressComponent implements OnInit {
  public addressEditFlag = false;
  public phone: Phone | undefined;
  public address: Address | undefined;

  constructor() {
  }

  ngOnInit(): void {
    this.address = {
      id: 1,
      country: "France",
      city: "Paris",
      index: "123456",
      street: "Some Street",
      homeNr: 12,
      isFavorite: true,
      contactId: 1
    }

  }

  toggleEditAddress() {
    this.addressEditFlag = !this.addressEditFlag;
  }

  toggleStar() {
    console.log("clickStar", this.phone?.isFavorite)
  }

}
