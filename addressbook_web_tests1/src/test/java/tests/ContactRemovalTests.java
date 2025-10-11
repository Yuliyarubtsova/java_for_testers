package tests;


import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {

    @Test
    public void removeContactTests() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData("Ivan", "Ivanovich", "Moscow", "123456789", "x5@mail.ru", "veselyebobry.ru"));
        }
        app.contacts().removeContact();
    }

    @Test
    public void removeAllContactTests() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData("Ivan", "Ivanovich", "Moscow", "123456789", "x5@mail.ru", "veselyebobry.ru"));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCountContacts());
    }
}
