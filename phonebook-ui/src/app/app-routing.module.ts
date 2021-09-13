import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {ContactDetailsComponent} from "./contact-details/contact-details.component";
import {LoginComponent} from "./login/login.component";
import {ContactsComponent} from "./contacts/contacts.component";
import {PersonalAccountComponent} from "./personal-account/personal-account.component";

const routes: Routes = [
  { path: 'contact-details', component: ContactDetailsComponent},
  { path: 'login', component: LoginComponent},
  { path: 'contacts', component: ContactsComponent},
  { path: 'personal-account', component: PersonalAccountComponent},
  { path: '', redirectTo: '/login', pathMatch: 'full' },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
