package tests;


import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    public void removeContactTests() {
        if (app.contacts().getCountContacts() == 0) {
            app.contacts().createContact(new ContactData("", "Ivan", "Ivanovich", "Moscow", "123456789", "x5@mail.ru", "veselyebobry.ru"));
        }
        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void removeAllContactTests() {
        if (app.contacts().getCountContacts() == 0) {
            app.contacts().createContact(new ContactData("", "Ivan", "Ivanovich", "Moscow", "123456789", "x5@mail.ru", "veselyebobry.ru"));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCountContacts());
    }
}
