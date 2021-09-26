import {Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {Phone} from "../../../model/phone";
import {Subscription} from "rxjs";
import {PhoneService} from "../../../service/phone.service";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-phone-element',
  templateUrl: './phone-element.component.html',
  styleUrls: ['./phone-element.component.css', '../../contact-details.component.css']
})
export class PhoneElementComponent implements OnInit, OnDestroy {
  private subscriptions: Subscription[] = [];
  getAllPhoneErrorMessage: string | undefined;
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

  constructor(private phoneService: PhoneService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit(): void {
    this.contactId = Number(this.route.snapshot.paramMap.get('id'));
    this._initFrom();
  }

  private _initFrom() {
    this.updatePhoneForm = new FormGroup({
      countryCode: new FormControl(this.phone?.countryCode, Validators.required),
      telephoneNumber: new FormControl(this.phone?.telephoneNumber, Validators.required),
      favorite: new FormControl(this.phone?.isFavorite),
      contactId: new FormControl(this.contactId),
      id: new FormControl(this.phone?.id)
    });
  }

  private callBackError(error: HttpErrorResponse): void {
    this.getAllPhoneErrorMessage = "Error";
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
      .subscribe(value => {
        this.updatePhone.emit(this.updatePhoneForm.value);
      }, error => this.logError = error);
    this.subscriptions.push(addSubscription);
    this.phoneEditFlag = false;
  }

  onClickDelete(phone: Phone) {
    const addSubscription = this.phoneService.deletePhone(phone.id).subscribe(value => {
      this.deletePhone.emit(this.phone);
    }, error => this.logError = error);
    this.subscriptions.push(addSubscription);
  }

  toggleEditPhone() {
    this.phoneEditFlag = !this.phoneEditFlag;
  }

  ngOnDestroy(): void {
    this.subscriptions
      .forEach(subscriptions => subscriptions.unsubscribe());
  }
}
