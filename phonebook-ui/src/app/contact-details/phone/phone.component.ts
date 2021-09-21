import { Component, OnInit } from '@angular/core';
import {Phone} from "../../model/phone";

@Component({
  selector: 'app-phone',
  templateUrl: './phone.component.html',
  styleUrls: ['../contact-details.component.css']
})
export class PhoneComponent implements OnInit {
  public phone: Phone| undefined;
  public phoneEditFlag = false;


  constructor() { }

  ngOnInit(): void {
    this.phone = {
      id : 1,
      countryCode: "+11",
      telephoneNumber: "222-3344",
      isFavorite: true,
      contactId: 1
    }
  }
  toggleEditPhone() {
    this.phoneEditFlag = !this.phoneEditFlag;
  }
  toggleStar() {
    console.log("clickStar", this.phone?.isFavorite)
  }

}
