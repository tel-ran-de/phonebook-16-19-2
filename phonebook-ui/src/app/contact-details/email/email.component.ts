import {Component, OnInit} from '@angular/core';
import {Email} from "../../model/email";

@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
  styleUrls: ['../contact-details.component.css']
})
export class EmailComponent implements OnInit {
  public email: Email | undefined;
  public emailEditFlag = false;

  constructor() {
  }

  ngOnInit(): void {
    this.email = {
      id: 1,
      email: "vasya@email.com",
      isFavorite: false,
      contactId: 1
    }
  }

  toggleEditEmail() {
    this.emailEditFlag = !this.emailEditFlag;
  }

  toggleStar() {
    console.log("clickStar", this.email?.isFavorite)
  }
}
