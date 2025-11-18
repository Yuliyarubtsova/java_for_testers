package ru.stqa.addressbook.tests;


import io.qameta.allure.Allure;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    public void removeContactTests() {
        Allure.step("Checking precondition", step -> {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "Ivan", "Ivanovich", "Moscow", "", "123456789", "", "x5@mail.ru", "", "", "veselyebobry.ru", ""));
            app.contacts().returnToHome();
        }
        });
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Allure.step("Validating results", step -> {
        Assertions.assertEquals(newContacts, expectedList);
        });
    }

    @Test
    public void removeAllContactTests() {
        Allure.step("Checking precondition", step -> {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "Ivan", "Ivanovich", "Moscow", "", "123456789", "", "x5@mail.ru", "", "", "veselyebobry.ru", ""));
            app.contacts().returnToHome();
        }
        });

        app.contacts().removeAllContacts();
        Allure.step("Validating results", step -> {
        Assertions.assertEquals(0, app.hbm().getContactCount());
        });
    }

    @Test
    public void removeContactFromGroup() {
        Allure.step("Checking precondition", step -> {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        });
        var group = app.hbm().getGroupList().get(0);
        ContactData contact = null;
        boolean isNewContact = false;
        if (app.hbm().getContactCount() == 0) {
            contact = new ContactData()
                    .withId("")
                    .withFirstname(CommonFunctions.randomString(10))
                    .withLastname(CommonFunctions.randomString(10))
                    .withPhoto(randomFile("src/test/resources/images"));
            app.contacts().createContactWithPhoto(contact, group);
            contact = app.hbm().getContactList().get(0);
            isNewContact = true;
        } else {
            contact = app.hbm().getContactList().get(0);
        }
        var contactsInGroup = app.hbm().getContactsInGroup(group);

        if (!isNewContact && !contactsInGroup.contains(contact)) {
            app.contacts().addContactToGroup(contact, group);
            contactsInGroup = app.hbm().getContactsInGroup(group);
        }

        app.contacts().removeContactFromGroup(group, contact);
        var contactsInGroupNew = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(contactsInGroup.size() - 1, contactsInGroupNew.size());
    }
}
