import {Injectable} from '@angular/core';
import {InMemoryDbService} from 'angular-in-memory-web-api';
import {Contact} from "./model/contact";
import {Email} from "./model/email";

@Injectable({
  providedIn: 'root',
})
export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    const contact = [
      {
        id: 1,
        firstName: "firsName",
        lastName: "lastName",
        age: 18,
        isFavorite: true,
        group: "FRIENDS"
      }, {
        id: 2,
        firstName: "Benjamin",
        lastName: "Top",
        age: 77,
        isFavorite: false,
        group: "FAMILY"
      }, {
        id: 3,
        firstName: "Vovka",
        lastName: "Bulkin",
        age: 12,
        isFavorite: true,
        group: "FRIENDS"
      }, {
        id: 4,
        firstName: "Nick",
        lastName: "Dulitl",
        age: 56,
        isFavorite: true,
        group: "NONE"
      }, {
        id: 5,
        firstName: "Otto",
        lastName: "Skin",
        age: 32,
        isFavorite: false,
        group: "FRIENDS"
      }
    ];

    function generateEmail() {
      const emailArr: Email[] = [];
      let id = 1;
      for (let contactElement of contact) {
        for (let i = 1; i < 6; i++) {
          emailArr.push(
            {
              id: id,
              email: `${contactElement.firstName}_${contactElement.lastName}_${i}@email.com`,
              isFavorite: i % 3 === 0,
              contactId: contactElement.id
            }
          );
          id++;
        }
      }
      return emailArr;
    }

    const email: Email[] = generateEmail();
    return {contact, email};
  }

  genId(contacts: Contact[] | Email[]): number {
    return contacts.length > 0 ? Math.max(...contacts.map(contact => contact.id)) + 1 : 11;
  }
}
