import {Component, OnInit} from '@angular/core';
import {Address} from "../../model/address";
import {Subscription} from "rxjs";
import {ActivatedRoute} from "@angular/router";
import {AddressService} from "../../service/address.service";

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
      .subscribe(addresses => this.addresses = addresses);
    this.subscriptions.push(getAddressesSubscription);
  }

  onAddressChange(address: Address) {
    if(address.id != null) {
      this.subscriptions.push(this.addressService.updateAddress(address)
        .subscribe())
    } else {
      address.contactId = this.contactId;
      this.subscriptions.push(this.addressService.addAddress(address)
        .subscribe(_ => {
          this.addingNewAddress = false;
          this.getAddresses();
        }));
    }
  }
  addNewAddress() {
    this.addingNewAddress = true;
  }

  deleteAddress(address: Address) {
    if (address.id != null) {
      this.addressService.deleteAddress(address.id).subscribe(_ => this.getAddresses());
    }
  }
  ngOnDestroy(): void {
    this.subscriptions.forEach(value => value.unsubscribe())
  }
}
