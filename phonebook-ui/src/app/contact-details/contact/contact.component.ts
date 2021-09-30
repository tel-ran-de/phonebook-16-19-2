import {Component, OnDestroy, OnInit} from '@angular/core';
import {Contact} from "../../model/contact";
import {ActivatedRoute} from "@angular/router";
import {ContactService} from "../../service/contact.service";
import {Subscription} from "rxjs";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Validator} from "../../shared/validator";

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit, OnDestroy {
  public contactEditFlag = false;
  public contact: Contact | undefined;
  public imageSave = "assets/images/save.png";
  public imageEdit = "assets/images/edit.png";
  private subscriptions: Subscription[] = [];
  public errorMessage: undefined | string;

  contactForm: FormGroup = new FormGroup({});

  constructor(
    private route: ActivatedRoute,
    private contactService: ContactService) {
  }

  ngOnInit(): void {
    this.subscriptions.push(this.getContact());
  }

  createForm() {
    this.contactForm = new FormGroup({
      id: new FormControl(this.contact?.id),
      firstName: new FormControl(this.contact?.firstName, [Validators.required, Validator.noWhitespaceValidator]),
      lastName: new FormControl(this.contact?.lastName, [Validators.required, Validator.noWhitespaceValidator]),
      age: new FormControl(this.contact?.age, [Validators.required, Validator.noWhitespaceValidator, Validators.max(120), Validators.min(1)]),
      favorite: new FormControl(this.contact?.favorite),
      group: new FormControl(this.contact?.group)
    })
  }

  getContact(): Subscription {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    return this.contactService.getContact(id)
      .subscribe(contact => {
        this.contact = contact;
        this.createForm();
      });
  }

  toggleEditContact() {
    this.contactEditFlag = !this.contactEditFlag;
  }

  updateContact() {
    this.errorMessage = '';
    this.subscriptions.push(this.contactService.updateContact(this.contactForm.value)
      .subscribe(_ => {
          this.contactEditFlag = !this.contactEditFlag;
        },
        _ => this.errorMessage = "Error saving contact into Database. Please, try again later."
      ));
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((subscription) => subscription.unsubscribe())
  }
}
