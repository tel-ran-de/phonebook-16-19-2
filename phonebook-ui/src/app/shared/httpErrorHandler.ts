import {HttpErrorResponse} from "@angular/common/http";

export function convertHttpResponseToErrorMessage(error: HttpErrorResponse): string {
  if (error.status === 500)
    return 'Server error 500, please try again later';
  else if (error.error.errorMsg)
    return error.error.errorMsg;
  else
    return 'Server error, please try again later';
}
