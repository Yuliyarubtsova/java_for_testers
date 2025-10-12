package manager;

import model.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{

    public ContactHelper(ApplicationManager manager) {
        super(manager);

    }

    public int getCountContacts() {
         return manager.driver.findElements(By.name("selected[]")).size();
    }

//    public boolean isContactPresent() {
//        return manager.isElementPresent(By.name("selected[]"));
//    }

    public void createContact(ContactData contact) {
        pressAddNewContact();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void removeContact(ContactData center) {
        selectContact(center);
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
    private void selectContact(ContactData center) {
        click(By.cssSelector(String.format("input[value='%s']", center.id())));
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

    public List<ContactData> getList() {
        var contacts = new ArrayList<ContactData>();
        var tds = manager.driver.findElements(By.cssSelector("td.center"));
        for (var td : tds) {
            var name = td.getText();
            var checkbox = td.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new ContactData().withId(id).withFirstname(name));
        }
        return contacts;
    }
}
