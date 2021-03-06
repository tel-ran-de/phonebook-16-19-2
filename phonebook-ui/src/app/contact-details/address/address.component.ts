import {Component, OnInit} from '@angular/core';
import {Address} from "../../model/address";
import {Subscription} from "rxjs";
import {ActivatedRoute} from "@angular/router";
import {AddressService} from "../../service/address.service";
import {HttpErrorResponse} from "@angular/common/http";
import {convertHttpResponseToErrorMessage} from "../../shared/httpErrorHandler";

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.css']
})
export class AddressComponent implements OnInit {
  private subscriptions: Subscription[] = [];
  private contactId: number;
  public addingNewAddress = false;

  public addresses: Address[] = [];
  public errorMessage: string = '';

  constructor(
    private route: ActivatedRoute,
    private addressService: AddressService
  ) {
    this.contactId = Number(this.route.snapshot.paramMap.get('id'));
  }

  ngOnInit(): void {
    this.getAddresses();
  }

  getAddresses() {
    const getAddressesSubscription = this.addressService.getAddresses(this.contactId)
      .subscribe(addresses => this.addresses = addresses, error => this.handleHttpError(error));
    this.subscriptions.push(getAddressesSubscription);
  }

  onAddressChange(address: Address) {
    if (address.id != null) {
      this.subscriptions.push(this.addressService.updateAddress(address)
        .subscribe(_ => {
        }, error => this.handleHttpError(error)))
    } else {
      address.contactId = this.contactId;
      this.subscriptions.push(this.addressService.addAddress(address)
        .subscribe(_ => {
            this.addingNewAddress = false;
            this.getAddresses();
          },
          error => this.handleHttpError(error)
        ));
    }
  }

  addNewAddress() {
    this.addingNewAddress = true;
  }

  deleteAddress(address: Address) {
    if (address.id != null) {
      this.addressService.deleteAddress(address.id).subscribe(
        _ => this.getAddresses(),
        error => this.handleHttpError(error)
      );
    }
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(value => value.unsubscribe())
  }

  cancelAddingNewAddress() {
    this.addingNewAddress = false;
  }

  private handleHttpError(error: HttpErrorResponse): void {
    this.errorMessage = convertHttpResponseToErrorMessage(error);
  }
}
