import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {HttpClientInMemoryWebApiModule} from 'angular-in-memory-web-api';
import {InMemoryDataService} from './in-memory-data.service';
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
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

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
    ContactComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    HttpClientInMemoryWebApiModule.forRoot(
      InMemoryDataService, {dataEncapsulation: false}
    ),
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent],

})
export class AppModule {
}
