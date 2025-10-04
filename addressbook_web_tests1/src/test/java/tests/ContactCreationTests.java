package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase{

    @Test
    public void createContacts() {
        app.contacts().createContact(new ContactData("Ivan", "Ivanovich", "Moscow", "123456789", "x5@mail.ru", "veselyebobry.ru"));
    }

    @Test
    public void createContactsWithEmpty() {
        app.contacts().createContact(new ContactData());
    }

    @Test
    public void createContactsWithLastnameOnly() {
        app.contacts().createContact(new ContactData().withLastname("Ivanovich"));
    }
}
