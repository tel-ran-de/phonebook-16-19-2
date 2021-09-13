import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {HttpClientInMemoryWebApiModule} from 'angular-in-memory-web-api';
import {InMemoryDataService} from './in-memory-data.service';
import { AppRoutingModule } from './app-routing.module';
import { ContactDetailsComponent } from './contact-details/contact-details.component';
import { FormsModule } from "@angular/forms";
import { LoginComponent } from './login/login.component';
import { ContactsComponent } from './contacts/contacts.component';
import { PersonalAccountComponent } from './personal-account/personal-account.component';

@NgModule({
  declarations: [
    AppComponent,
    ContactDetailsComponent,
    LoginComponent,
    ContactsComponent,
    PersonalAccountComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    HttpClientInMemoryWebApiModule.forRoot(
      InMemoryDataService, {dataEncapsulation: false}
    ),
    AppRoutingModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],

})
export class AppModule {
}
