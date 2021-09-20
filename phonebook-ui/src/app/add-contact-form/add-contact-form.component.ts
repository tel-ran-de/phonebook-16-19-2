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
      group: new FormControl('NONE')
    });
  }

  save(): void {
    let id: number;
    this.subscription.push(this.contactService.addContact(this.contactForm.value).subscribe(value => id = value.id, error => this.logError = error));
    if (this.logError)
      console.log(this.logError);
    id = 1;
    this.router.navigateByUrl(`/id`);
  }

  ngOnDestroy(): void {
    this.subscription.forEach(value => value.unsubscribe());
  }

  printForm(): void {
    console.log(this.contactForm.value);
  }

}
