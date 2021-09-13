import { Component, OnInit } from '@angular/core';
import {Contact} from "../model/contact";
import {Phone} from "../model/phone";
import {Email} from "../model/email";
import { Router } from '@angular/router';

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css']
})
export class ContactsComponent implements OnInit {
  public contact: Contact | undefined;
  public phone: Phone |undefined
  public email: Email |undefined

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.contact = {
      id: 1,
      firstName: "Ivan",
      lastName: "Petrov",
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
    this.email = {
      id : 1,
      email: "vasya@email.com",
      isFavorite: false,
      contactId: 1
    }
  }

   goToPage(pageName:string){
    this.router.navigate([`${pageName}`]);
  }
}
