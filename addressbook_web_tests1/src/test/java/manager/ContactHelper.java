package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase{

    public ContactHelper(ApplicationManager manager) {
        super(manager);

    }

    public int getCountContacts() {
         return manager.driver.findElements(By.name("selected[]")).size();
    }

    public boolean isContactPresent() {
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contact) {
        pressAddNewContact();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void removeContact() {
        selectContact();
        removeSelectedContacts();
        returnToHomePage();
    }

    private void pressAddNewContact() {
        click(By.linkText("add new"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("lastname"), contact.lastname());
        type(By.name("address"), contact.address());
        type(By.name("mobile"), contact.mobile());
        type(By.name("email"), contact.email());
        type(By.name("homepage"), contact.homepage());
    }
    private void selectContact() {
        click(By.name("selected[]"));
    }

    private void removeSelectedContacts() {
        click(By.name("delete"));
    }

    private void returnToHomePage() {
        click(By.linkText("home page"));
    }
    private void submitContactCreation() {
        click(By.name("submit"));
    }

    public void removeAllContacts() {
        selectAllContacts();
        removeSelectedContacts();
    }

    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }
}
