import {Component, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {PhoneService} from "../service/phone.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-add-phone-form',
  templateUrl: './add-phone-form.component.html',
  styleUrls: ['./add-phone-form.component.css', '../contact-details/contact-details.component.css']
})
export class AddPhoneFormComponent implements OnInit {
  subscription: Subscription[] = [];
  phoneForm!: FormGroup;
  logError: String | undefined;
  contactId: number | undefined;
  flag!: boolean;

  constructor(private phoneService: PhoneService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit(): void {
    this.contactId = Number(this.route.snapshot.paramMap.get('id'));
    this._initFrom();
    this.flag = false;
  }

  private _initFrom() {
    this.phoneForm = new FormGroup({
      countryCode: new FormControl('', Validators.required),
      telephoneNumber: new FormControl('', Validators.required),
      favorite: new FormControl(this.flag),
      contactId: new FormControl(this.contactId)
    });
  }

  onSubmit(): void {
    if (this.phoneForm.invalid) {
      this.logError = "The phone form is invalid";
      return;
    }
    const addSubscription = this.phoneService.addPhone(this.phoneForm.value)
      .subscribe(value => this.router.navigate(['contacts', this.contactId]), error => this.logError = error);
    this.subscription.push(addSubscription);
  }

  ngOnDestroy(): void {
    this.subscription.forEach(value => value.unsubscribe());
  }

  toggleStar() {
    this.flag = !this.flag;
  }

  clear() {
    this.phoneForm.reset();
    this.logError = undefined;
  }
}
