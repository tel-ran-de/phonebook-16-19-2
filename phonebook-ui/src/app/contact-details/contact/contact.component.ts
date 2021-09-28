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
  public contactToDisplay: Contact | undefined;
  public imageSave = "assets/images/save.png";
  public imageEdit = "assets/images/edit.png";
  private subscriptions: Subscription[] = [];
  public errorMessage: undefined | string;

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
      .subscribe(contact => {
        this.contact = Object.assign({}, contact);
        this.contactToDisplay = Object.assign({}, contact);
      });
  }

  toggleEditContact() {
    this.contactEditFlag = !this.contactEditFlag;
  }

  updateContact() {
    this.errorMessage = '';
    if (this.contactToDisplay) {
      this.subscriptions.push(this.contactService.updateContact(this.contactToDisplay)
        .subscribe(_ => {
          this.contactEditFlag = !this.contactEditFlag;
          this.contact = Object.assign({}, this.contactToDisplay);
        },
         _ => this.errorMessage = "Error saving contact into Database. Please, try again later."
      ));
    }
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((subscription) => subscription.unsubscribe())
  }
}
