import {Pipe, PipeTransform} from '@angular/core';
import {Contact} from "../model/contact";

@Pipe({name: 'contactSearch'})
export class ContactSearch implements PipeTransform {
  transform(value: Contact[], searchItem: string): Contact[] {
    if (!value)
      return [];
    if (!searchItem)
      return value;

    return value.filter(value => {
      const item = searchItem.toLowerCase();
      const contract = value.firstName + value.lastName;
      return contract.toLowerCase().includes(item);
    })
  }
}
