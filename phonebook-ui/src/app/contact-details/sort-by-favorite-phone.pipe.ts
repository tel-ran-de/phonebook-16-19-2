import {Pipe, PipeTransform} from '@angular/core';
import {Phone} from "../model/phone";

@Pipe({
  name: 'sortPhonesByFavorite',
  pure: false
})
export class SortPhonesByFavoritePipe implements PipeTransform {

  transform(value: Phone[]): any {
    if (!value)
      return [];
    return value.sort((a) => a.favorite ? -1 : 1);
  }
}
