import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {AddContactFormComponent} from "./add-contact-form/add-contact-form.component";
import {ContactDetailsComponent} from "./contact-details/contact-details.component";
import {ContactsComponent} from "./contacts/contacts.component";

const routes: Routes = [
  {path: '', redirectTo: '/contacts', pathMatch: 'full'},

  {path: 'contacts/add-form', component: AddContactFormComponent},
  {path: 'contacts/:id', component: ContactDetailsComponent},
  {path: 'contacts', component: ContactsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
