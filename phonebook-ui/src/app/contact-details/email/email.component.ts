import {Component, OnDestroy, OnInit} from '@angular/core';
import {Email} from "../../model/email";
import {EmailService} from "../../service/email.service";
import {HttpErrorResponse} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
  styleUrls: ['../contact-details.component.css', './email.component.css']
})
export class EmailComponent implements OnInit, OnDestroy {

  emails: Email[] | undefined;
  private subscriptions: Subscription[] = [];

  getAllEmailErrorMessage: string | undefined;

  constructor(private emailService: EmailService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getEmails();
  }

  private getEmails(): void {
    this.getAllEmailErrorMessage = undefined;
    const contactId: number = Number(this.route.snapshot.paramMap.get('id'));

    const getEmailsSubscription = this.emailService.getEmails(contactId)
      .subscribe(value => this.emails = value, error => this.callBackError(error));
    this.subscriptions.push(getEmailsSubscription);
  }

  private callBackError(error: HttpErrorResponse): void {
    this.getAllEmailErrorMessage = "Error";
  }

  ngOnDestroy(): void {
    this.subscriptions
      .forEach(subscriptions => subscriptions.unsubscribe());
  }
}
