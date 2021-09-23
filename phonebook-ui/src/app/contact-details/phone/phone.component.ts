import {Component, OnDestroy, OnInit} from '@angular/core';
import {Phone} from "../../model/phone";
import {Subscription} from "rxjs";
import {PhoneService} from "../../service/phone.service";
import {ActivatedRoute} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-phone',
  templateUrl: './phone.component.html',
  styleUrls: ['../contact-details.component.css', 'phone.component.css']
})
export class PhoneComponent implements OnInit, OnDestroy {
  public phones: Phone[] = [];
  private subscriptions: Subscription[] = [];
  getAllPhoneErrorMessage: string | undefined;
  phoneEditFlag = false;
  phoneAddFlag = false;
  addPhoneForm: boolean = false;

  constructor(private phoneService: PhoneService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getPhones();
  }

  getPhones(): void {
    this.getAllPhoneErrorMessage = undefined;
    const contactId: number = Number(this.route.snapshot.paramMap.get('id'));
    const getPhonesSubscription = this.phoneService.getPhones(contactId).subscribe(value => this.phones = value
      .sort((a, b) => Number(b.isFavorite) - Number(a.isFavorite)), error => this.callBackError(error));
    this.subscriptions.push(getPhonesSubscription);
  }

  private callBackError(error: HttpErrorResponse): void {
    this.getAllPhoneErrorMessage = "Error";
  }

  ngOnDestroy(): void {
    this.subscriptions
      .forEach(subscriptions => subscriptions.unsubscribe());
  }

  onClickSave() {
    this.phoneEditFlag = false;
  }

  toggleEditPhone() {
    this.phoneEditFlag = !this.phoneEditFlag;
  }

  toggleAddForm() {
    this.addPhoneForm = !this.addPhoneForm;
    this.phoneAddFlag = !this.phoneAddFlag;
  }

}
