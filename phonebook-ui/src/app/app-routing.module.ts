import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {ContactDetailsComponent} from "./contact-details/contact-details.component";


const routes: Routes = [
  { path: 'contacts/:id', component: ContactDetailsComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
