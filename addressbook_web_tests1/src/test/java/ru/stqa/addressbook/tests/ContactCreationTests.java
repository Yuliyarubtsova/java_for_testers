package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.model.GroupData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


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
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>() {
        });
        result.addAll(value);
        return result;
    }

    public static List<ContactData> singleRandomContact() throws IOException {
        return List.of(new ContactData()
                .withFirstname(CommonFunctions.randomString(10))
                .withLastname(CommonFunctions.randomString(10))
                .withAddress(CommonFunctions.randomString(15)));
    }

    @ParameterizedTest
    @MethodSource("singleRandomContact")
    public void canCreateSingleContact(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContactWithoutPhoto(contact);
        var newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var maxId = newContacts.get(newContacts.size() - 1).id();

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(maxId));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void createContacts() {
        app.contacts().createContactWithoutPhoto(new ContactData("", "Ivan", "Ivanovich", "Moscow", "123456789", "x5@mail.ru", "veselyebobry.ru", ""));
    }

    @Test
    void canCreateContact() {
        var contact = new ContactData()
                .withFirstname(CommonFunctions.randomString(10))
                .withLastname(CommonFunctions.randomString(10))
                .withPhoto(randomFile("src/test/resources/images"));
        app.contacts().createContactWithPhoto(contact);
    }

    @Test
    void canCreateContactInGroup() {
        var contact = new ContactData()
                .withFirstname(CommonFunctions.randomString(10))
                .withLastname(CommonFunctions.randomString(10))
                .withPhoto(randomFile("src/test/resources/images"));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().get(0);

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContactWithPhoto(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }

    @Test
    public void addContactToGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().get(0);
        var contacts = app.hbm().getContactList();
        ContactData contact = null;
        for (var c : contacts) {
            var contactsInGroup = app.hbm().getContactsInGroup(group);
            if (!contactsInGroup.contains(c)) {
                contact = c;
                break;
            }
        }
        if (contact == null) {
            contact = new ContactData()
                    .withFirstname(CommonFunctions.randomString(10))
                    .withLastname(CommonFunctions.randomString(10))
                    .withPhoto(randomFile("src/test/resources/images"));
            app.contacts().createContactWithPhoto(contact);
            contacts = app.hbm().getContactList();
            for (var c : contacts) {
                var contactsInGroup = app.hbm().getContactsInGroup(group);
                if (!contactsInGroup.contains(c)) {
                    contact = c;
                    break;
                }
            }
            var contactsInGroup = app.hbm().getContactsInGroup(group);
            app.contacts().addContactToGroup(contact, group);
            var contactsInGroupNew = app.hbm().getContactsInGroup(group);
            Assertions.assertEquals(contactsInGroup.size() + 1, contactsInGroupNew.size());
        }
    }
}
