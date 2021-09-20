import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {AddContactFormComponent} from "./add-contact-form/add-contact-form.component";


const routes: Routes = [
  {path: 'add-form', component: AddContactFormComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
