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
      .sort((a, b) => Number(b.favorite) - Number(a.favorite)), error => this.callBackError(error));
    this.subscriptions.push(getPhonesSubscription);
  }

  private callBackError(error: HttpErrorResponse): void {
    this.getAllPhoneErrorMessage = "Error";
  }


  toggleAddForm() {
    this.addPhoneForm = !this.addPhoneForm;
    this.phoneAddFlag = !this.phoneAddFlag;
  }

  addPhone($event: Phone): void {
    this.phones.push($event);
    this.toggleAddForm();
    this.sortPhones();
  }

  updatePhone($event: Phone): void {
    this.phones.forEach(value => {
      if (value.id === $event.id) {
        value = $event;
      }
    });
    this.sortPhones();
  }

  deletePhone($event: Phone): void {
    let deleteIndex = this.phones.indexOf($event);
    this.phones.splice(deleteIndex, 1);
    this.sortPhones();
  }

  ngOnDestroy(): void {
    this.subscriptions
      .forEach(subscriptions => subscriptions.unsubscribe());
  }

  sortPhones(): void {
    this.phones.sort((a, b) => Number(b.favorite) - Number(a.favorite));
  }
}
