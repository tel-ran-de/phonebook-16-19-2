import {Component, OnDestroy, OnInit} from '@angular/core';
import {Contact} from "../model/contact";
import {ContactService} from "../service/contact.service";
import {Subscription} from "rxjs";


@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css']

})
export class ContactsComponent implements OnInit, OnDestroy {
  public contacts: Contact[] | undefined;
  private subscriptions: Subscription[] = [];

  public searchItem = '';


  public imageDelete = "assets/images/delete.png";

  constructor(private service: ContactService) {
  }

  ngOnInit(): void {
    const getContactsSubscription = this.service.getContacts()
      .subscribe(contacts => this.contacts = contacts);
    this.subscriptions.push(getContactsSubscription);

  }

  delete(id: number): void {
    const deleteContactSubscription = this.service.removeContact(id)
      .subscribe(() => this.contacts = this.contacts?.filter(value => value.id !== id));
    this.subscriptions.push(deleteContactSubscription);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(value => value.unsubscribe())
  }
}
