import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {AddContactFormComponent} from "./add-contact-form/add-contact-form.component";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";


const routes: Routes = [
  {path: 'contacts/add-form', component: AddContactFormComponent},
  {path: 'contacts/:id', component: PageNotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
