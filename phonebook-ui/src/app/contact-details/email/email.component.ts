import {Component, OnDestroy, OnInit} from '@angular/core';
import {Email} from "../../model/email";
import {EmailService} from "../../service/email.service";
import {HttpErrorResponse} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs";
import {convertHttpResponseToErrorMessage} from "../../shared/httpErrorHandler";

@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
  styleUrls: ['../contact-details.component.css', './email.component.css']
})
export class EmailComponent implements OnInit, OnDestroy {

  emails: Email[] = [];
  private subscriptions: Subscription[] = [];

  errorMessage: string | undefined;
  public contactId!: number;
  public addNewEmail = false;


  constructor(
    private emailService: EmailService,
    private route: ActivatedRoute) {

    this.contactId = Number(this.route.snapshot.paramMap.get('id'));
  }

  ngOnInit(): void {
    this.getEmails();
  }

  private getEmails(): void {
    this.errorMessage = undefined;
    const contactId: number = Number(this.route.snapshot.paramMap.get('id'));

    const getEmailsSubscription = this.emailService.getEmails(contactId)
      .subscribe(value => this.emails = value, error => this.handleHttpError(error));
    this.subscriptions.push(getEmailsSubscription);
  }

  ngOnDestroy(): void {
    this.subscriptions
      .forEach(subscriptions => subscriptions.unsubscribe());
  }

  addEmail() {
    this.addNewEmail = true;
  }

  updateEmail(email: Email) {
    if (email.id != null) {
      this.subscriptions.push(this.emailService.updateEmail(email).subscribe())
    } else {
      email.contactId = this.contactId;
      this.subscriptions.push(this.emailService.addEmail(email).subscribe(
        _ => {
          this.addNewEmail = false;
          this.getEmails();
        },
        error => this.handleHttpError(error)))
    }
  }

  deleteEmail(email: Email) {
    if (email.id != null) {
      this.emailService.deleteEmail(email.id).subscribe(
        _ => this.getEmails(),
        error => this.handleHttpError(error));
    }
  }

  cancelEmail() {
    this.addNewEmail = false;
  }

  private handleHttpError(error: HttpErrorResponse): void {
    this.errorMessage = convertHttpResponseToErrorMessage(error);
  }
}
