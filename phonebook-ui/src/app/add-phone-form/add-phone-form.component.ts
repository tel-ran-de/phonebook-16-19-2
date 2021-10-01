import {Component, EventEmitter, OnDestroy, OnInit, Output} from '@angular/core';
import {Subscription} from "rxjs";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {PhoneService} from "../service/phone.service";
import {ActivatedRoute} from "@angular/router";
import {Phone} from "../model/phone";
import {HttpErrorResponse} from "@angular/common/http";
import {convertHttpResponseToErrorMessage} from "../shared/httpErrorHandler";

@Component({
  selector: 'app-add-phone-form',
  templateUrl: './add-phone-form.component.html',
  styleUrls: ['./add-phone-form.component.css', '../contact-details/contact-details.component.css']
})
export class AddPhoneFormComponent implements OnInit, OnDestroy {
  subscription: Subscription[] = [];
  phoneForm!: FormGroup;
  errorMessage: String | undefined;
  contactId: number | undefined;
  @Output()
  addPhone: EventEmitter<Phone> = new EventEmitter<Phone>();

  constructor(private phoneService: PhoneService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.contactId = Number(this.route.snapshot.paramMap.get('id'));
    this._initFrom();
  }

  private _initFrom(): void {
    this.phoneForm = new FormGroup({
      countryCode: new FormControl('', [Validators.required,Validators.pattern("^[+\\d]+$")]),
      telephoneNumber: new FormControl('', [Validators.required,Validators.pattern("^[0-9]+$")]),
      favorite: new FormControl(false),
      contactId: new FormControl(this.contactId),
      id: new FormControl()
    });
  }

  onSubmit(): void {
    if (this.phoneForm.invalid) {
      this.errorMessage = "The phone form is invalid";
      return;
    }
    const addSubscription = this.phoneService.addPhone(this.phoneForm.value)
      .subscribe(value => this.addPhone.emit(value),
        error => this.handleHttpError = error);
    this.subscription.push(addSubscription);
  }

  clear(): void {
    this.phoneForm.reset();
    this.errorMessage = undefined;
  }

  private handleHttpError(error: HttpErrorResponse): void {
    this.errorMessage = convertHttpResponseToErrorMessage(error);
  }

  ngOnDestroy(): void {
    this.subscription.forEach(value => value.unsubscribe());
  }
}
