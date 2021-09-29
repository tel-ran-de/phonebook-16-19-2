import {Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {Email} from "../../../model/email";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-email-element',
  templateUrl: './email-element.component.html',
  styleUrls: ['../../contact-details.component.css', './email-element.component.css']
})
export class EmailElementComponent implements OnInit, OnDestroy {

  @Input()
  public email: Email = {} as Email;
  @Input()
  public emailEditFlag = false;
  @Output()
  public EmailChanged = new EventEmitter<Email>()

  public imageSave = "assets/images/save.png";
  public imageEdit = "assets/images/edit.png";

  emailForm!: FormGroup;
  emailEditSubscription!: Subscription;

  constructor() {
  }

  ngOnInit() {
    this.emailForm = new FormGroup({
      id: new FormControl(this.email?.id),
      email: new FormControl(this.email?.email, [Validators.required,
        Validators.pattern("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,10}$")]),
      isFavorite: new FormControl(this.email?.isFavorite),
      contactId: new FormControl(this.email?.contactId)

    })
  }

  toggleEditEmail() {
    this.emailEditFlag = !this.emailEditFlag;
  }

  toggleStar() {
    this.email.isFavorite = !this.email.isFavorite;
    this.emailForm.get("isFavorite")?.setValue(this.email.isFavorite);
  }

  onSubmit() {
    this.EmailChanged.emit(this.emailForm.value);
    this.emailEditFlag = !this.emailEditFlag;
  }

  ngOnDestroy(): void {
    if (this.emailEditSubscription)
      this.emailEditSubscription.unsubscribe();
  }
}
