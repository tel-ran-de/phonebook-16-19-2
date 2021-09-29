import {FormControl, ValidationErrors} from "@angular/forms";

export class Validator {
  public static noWhitespaceValidator(control: FormControl): ValidationErrors | null {
    if (control.pristine || !control.touched) {
      return null;
    }
    const isWhitespace = (control.value || '').trim().length === 0;
    const isValid = !isWhitespace;
    return isValid ? null : {'whitespace': true};
  }
}
