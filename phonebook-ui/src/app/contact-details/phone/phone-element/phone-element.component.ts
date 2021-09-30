import {Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {Phone} from "../../../model/phone";
import {Subscription} from "rxjs";
import {PhoneService} from "../../../service/phone.service";
import {ActivatedRoute} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-phone-element',
  templateUrl: './phone-element.component.html',
  styleUrls: ['./phone-element.component.css', '../../contact-details.component.css']
})
export class PhoneElementComponent implements OnInit, OnDestroy {
  private subscriptions: Subscription[] = [];
  phoneEditFlag = false;
  contactId: number | undefined;
  updatePhoneForm!: FormGroup;
  logError: String | undefined;
  @Input()
  phone: Phone | undefined;
  @Output()
  updatePhone: EventEmitter<Phone> = new EventEmitter<Phone>();
  @Output()
  deletePhone: EventEmitter<Phone> = new EventEmitter<Phone>();

  constructor(private phoneService: PhoneService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.contactId = Number(this.route.snapshot.paramMap.get('id'));
    this._initFrom();
  }

  private _initFrom() {
    this.updatePhoneForm = new FormGroup({
      countryCode: new FormControl(this.phone?.countryCode, [Validators.required,Validators.pattern("^[+\\d]+$")]),
      telephoneNumber: new FormControl(this.phone?.telephoneNumber, [Validators.required,Validators.pattern("^[0-9]+$")]),
      favorite: new FormControl(this.phone?.favorite),
      contactId: new FormControl(this.contactId),
      id: new FormControl(this.phone?.id)
    });
  }

  onClickSave() {
    if (this.updatePhoneForm.invalid) {
      this.logError = "The phone form is invalid";
      return;
    }
    if (this.phone === this.updatePhoneForm.value) {
      this.logError = undefined;
      return;
    }
    const addSubscription = this.phoneService.updatePhone(this.updatePhoneForm.value)
      .subscribe(_ => this.updatePhone.emit(this.updatePhoneForm.value), error => this.logError = error);
    this.subscriptions.push(addSubscription);
    this.phoneEditFlag = false;
  }

  onClickDelete(phone: Phone): void {
    const addSubscription = this.phoneService.deletePhone(phone.id).subscribe(_ =>
      this.deletePhone.emit(this.phone), error => this.logError = error);
    this.subscriptions.push(addSubscription);
  }

  ngOnDestroy(): void {
    this.subscriptions
      .forEach(subscriptions => subscriptions.unsubscribe());
  }
}
