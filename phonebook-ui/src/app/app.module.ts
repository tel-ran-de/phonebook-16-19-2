import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {AppRoutingModule} from './app-routing.module';
import {NavigationComponent} from './navigation/navigation.component';
import {AddContactFormComponent} from './add-contact-form/add-contact-form.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {ContactDetailsComponent} from './contact-details/contact-details.component';
import {PhoneComponent} from './contact-details/phone/phone.component';
import {EmailComponent} from './contact-details/email/email.component';
import {AddressComponent} from './contact-details/address/address.component';
import {ContactComponent} from './contact-details/contact/contact.component';
import {ContactsComponent} from './contacts/contacts.component';
import {AddPhoneFormComponent} from './add-phone-form/add-phone-form.component';
import {PhoneElementComponent} from './contact-details/phone/phone-element/phone-element.component';
import {SortPhonesByFavoritePipe} from './contact-details/sort-by-favorite-phone.pipe';
import {EmailElementComponent} from './contact-details/email/emeail-element/email-element.component';
import {ContactSearch} from "./contacts/contact.search";
import {EditAddressComponent} from './contact-details/address/edit-address/edit-address.component';

@NgModule({
    declarations: [
        AppComponent,
        NavigationComponent,
        AddContactFormComponent,
        PageNotFoundComponent,
        ContactDetailsComponent,
        PhoneComponent,
        EmailComponent,
        AddressComponent,
        ContactComponent,
        ContactsComponent,
        EmailElementComponent,
        ContactSearch,
        EditAddressComponent,
        AddPhoneFormComponent,
        PhoneElementComponent,
        SortPhonesByFavoritePipe,
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        AppRoutingModule,
        FormsModule,
        ReactiveFormsModule
    ],
    providers: [],
    bootstrap: [AppComponent],

})
export class AppModule {
}
