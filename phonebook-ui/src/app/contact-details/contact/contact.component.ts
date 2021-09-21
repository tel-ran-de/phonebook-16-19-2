import {Component, OnInit} from '@angular/core';
import {Contact} from "../../model/contact";
import {ActivatedRoute} from "@angular/router";
import {ContactService} from "../../service/contact.service";

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['../contact-details.component.css']
})
export class ContactComponent implements OnInit {
  public contactEditFlag = false;
  public contact: Contact | undefined;
  public imageSave = "assets/images/save.png";
  public imageEdit = "assets/images/edit.png";

  constructor(
    private route: ActivatedRoute,
    private contactService: ContactService) {
  }

  ngOnInit(): void {
    this.getContact();
  }

  getContact(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.contactService.getContact(id)
      .subscribe(contact => this.contact = contact);
  }

  toggleEditContact() {
    this.contactEditFlag = !this.contactEditFlag;
  }

  save(): void {
    this.contactEditFlag = !this.contactEditFlag;
  }
}
