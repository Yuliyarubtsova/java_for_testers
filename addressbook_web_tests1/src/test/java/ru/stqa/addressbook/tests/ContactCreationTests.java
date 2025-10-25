package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{


    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
//        for (var firstname : List.of("", "firstname")) {
//            for (var lastname : List.of("", "lastname")) {
//                for (var address : List.of("", "address")) {
//                  for (var mobile : List.of("", "mobile")) {
//                      for (var email : List.of("", "email")) {
//                          for (var homepage : List.of("", "homepage")) {
//                              result.add(new ContactData().withFirstname(firstname).withLastname(lastname).withAddress(address).withMobile(mobile).withEmail(email).withHomepage(homepage));
//                          }
//                      }
//                  }
//                }
//            }
//        }

        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>() {});
        result.addAll(value);
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContactWithoutPhoto(contact);
        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()).withAddress("").withMobile("").withEmail("").withHomepage(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }
    @Test
    public void createContacts() {
        app.contacts().createContactWithPhoto(new ContactData("", "Ivan", "Ivanovich", "Moscow", "123456789", "x5@mail.ru", "veselyebobry.ru", ""));
    }

    @Test
    void canCreateContact() {
        var contact = new ContactData()
                .withFirstname(CommonFunctions.randomString(10))
                .withLastname(CommonFunctions.randomString(10))
                .withPhoto(randomFile("src/test/resources/images"));
        app.contacts().createContactWithPhoto(contact);
    }
}
