import { Component, OnInit } from '@angular/core';
import {Contact} from "../model/contact";
import {Phone} from "../model/phone";
import {Email} from "../model/email";
import {Address} from "../model/address";


@Component({
  selector: 'app-contact-details',
  templateUrl: './contact-details.component.html',
  styleUrls: ['./contact-details.component.css']
})
export class ContactDetailsComponent implements OnInit {
  public contactEditFlag = false;
  public phoneEditFlag = false;
  public contact: Contact | undefined;
  public phone: Phone| undefined;
  public phone2: Phone| undefined;
  public email: Email| undefined;
  public address: Address| undefined;

  constructor() { }

  ngOnInit(): void {
    this.contact = {
      id: 1,
      firstName: "Vasya",
      lastName: "Ivanov",
      age: 22,
      isFavorite: true,
      group: "Family"
    }
    this.phone = {
      id : 1,
      countryCode: "+11",
      telephoneNumber: "222-3344",
      isFavorite: true,
      contactId: 1

    }
    this.phone2 = {
      id : 2,
      countryCode: "+22",
      telephoneNumber: "444-1122",
      isFavorite: false,
      contactId: 1
    }
    this.email = {
      id : 1,
      email: "vasya@email.com",
      isFavorite: false,
      contactId: 1
    }
    this.address = {
      id : 1,
      country: "France",
      city: "Paris",
      index: "123456",
      street: "Some Street",
      homeNr: 12,
      isFavorite: true,
      contactId: 1
    }
  }

  toggleEditContact() {
    this.contactEditFlag = !this.contactEditFlag;
  }
  toggleEditPhone() {
    this.phoneEditFlag = !this.phoneEditFlag;
  }
  toggleStar() {
    console.log("clickStar", this.phone?.isFavorite)
  }

}
