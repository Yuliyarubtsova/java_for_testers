package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase{


    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstname : List.of("", "firstname")) {
            for (var lastname : List.of("", "lastname")) {
                for (var address : List.of("", "address")) {
                  for (var mobile : List.of("", "mobile")) {
                      for (var email : List.of("", "email")) {
                          for (var homepage : List.of("", "homepage")) {
                              result.add(new ContactData().withFirstname(firstname).withLastname(lastname).withAddress(address).withMobile(mobile).withEmail(email).withHomepage(homepage));
                          }
                      }
                  }
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            result.add(new ContactData()
                    .withFirstname(randomString(i * 2))
                    .withLastname(randomString(i * 2))
                    .withAddress(randomString(i * 2))
                    .withMobile(randomString(i * 2))
                    .withEmail(randomString(i * 2))
                    .withHomepage(randomString(i * 2)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        int contactCount = app.contacts().getCountContacts();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCountContacts();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }
    @Test
    public void createContacts() {
        app.contacts().createContact(new ContactData("", "Ivan", "Ivanovich", "Moscow", "123456789", "x5@mail.ru", "veselyebobry.ru"));
    }
}
