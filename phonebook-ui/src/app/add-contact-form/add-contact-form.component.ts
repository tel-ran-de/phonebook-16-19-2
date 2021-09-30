import {Component, OnDestroy, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {ContactService} from "../service/contact.service";
import {Subscription} from "rxjs";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-add-contact-form',
  templateUrl: './add-contact-form.component.html',
  styleUrls: ['./add-contact-form.component.css']
})
export class AddContactFormComponent implements OnInit, OnDestroy {
  subscription: Subscription[] = [];
  contactForm!: FormGroup;
  logError: String | undefined;

  constructor(private contactService: ContactService, private router: Router) {

  }

  ngOnInit(): void {
    this._initFrom();
  }

  private _initFrom() {
    this.contactForm = new FormGroup({
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      age: new FormControl(1, [Validators.required, Validators.min(1), Validators.max(120)]),
      favorite: new FormControl(false),
      group: new FormControl('NONE', Validators.required)
    });
  }

  onSubmit(): void {
    const addSubscription = this.contactService.addContact(this.contactForm.value)
      .subscribe(value => this.router.navigate(['contacts', value.id]), error => this.logError = error);
    this.subscription.push(addSubscription);
  }

  clear(): void {
    this.contactForm.reset();
    this.logError = undefined;
  }

  ngOnDestroy(): void {
    this.subscription.forEach(value => value.unsubscribe());
  }
}
