package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase{

    public ContactHelper(ApplicationManager manager) {
        super(manager);

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
        removeSelectedContact();
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

    private void removeSelectedContact() {
        click(By.name("delete"));
    }

    private void returnToHomePage() {
        click(By.linkText("home page"));
    }
    private void submitContactCreation() {
        click(By.name("submit"));
    }

}
