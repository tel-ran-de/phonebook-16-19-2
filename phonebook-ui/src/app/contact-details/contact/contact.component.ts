import {Component, OnDestroy, OnInit} from '@angular/core';
import {Contact} from "../../model/contact";
import {ActivatedRoute} from "@angular/router";
import {ContactService} from "../../service/contact.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['../contact-details.component.css']
})
export class ContactComponent implements OnInit, OnDestroy {
  public contactEditFlag = false;
  public contact: Contact | undefined;
  public imageSave = "assets/images/save.png";
  public imageEdit = "assets/images/edit.png";
  private subscriptions: Subscription[] = [];

  constructor(
    private route: ActivatedRoute,
    private contactService: ContactService) {
  }

  ngOnInit(): void {
    this.subscriptions.push(this.getContact());
  }

  getContact(): Subscription {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    return this.contactService.getContact(id)
      .subscribe(contact => this.contact = contact);
  }

  toggleEditContact() {
    this.contactEditFlag = !this.contactEditFlag;
  }

  updateContact() {
    if (this.contact) {
      this.subscriptions.push(this.contactService.updateContact(this.contact)
        .subscribe(_ => this.contactEditFlag = !this.contactEditFlag))
    }
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((subscription) => subscription.unsubscribe())
  }
}
