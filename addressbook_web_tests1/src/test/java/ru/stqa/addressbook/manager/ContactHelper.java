package ru.stqa.addressbook.manager;

import org.openqa.selenium.support.ui.Select;
import ru.stqa.addressbook.model.ContactData;
import org.openqa.selenium.By;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);

    }

    public int getCountContacts() {
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void createContactWithPhoto(ContactData contact) {
        pressAddNewContact();
        fillContactFormWithPhoto(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void createContactWithPhoto(ContactData contact, GroupData group) {
        pressAddNewContact();
        fillContactFormWithPhoto(contact);
        selectGroup(group);
        submitContactCreation();
        returnToHomePage();
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    public void createContactWithoutPhoto(ContactData contact) {
        pressAddNewContact();
        fillContactFormWithoutPhoto(contact);
        submitContactCreation();
        returnToHomePage();
    }


    public void removeContact(ContactData contact) {
        selectContact(contact);
        removeSelectedContacts();
        returnToHomePage();
    }

    public void removeContactFromGroup(GroupData group, ContactData contact) {
        pressGroupList(group);
        selectContact(contact);
        pressRemoveFromGroup();
        returnToHome();
    }

    public void addContactToGroup(ContactData contact, GroupData group) {
        selectContact(contact);
        selectGroupForContact(group);
        pressAddTo();
        returnToHome();
    }

    private void pressAddTo() {
        click(By.name("add"));
    }

    private void selectGroupForContact(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }

    private void returnToHome() {
        click(By.linkText("home"));
    }

    private void pressRemoveFromGroup() {
        click(By.name("remove"));
    }

    private void pressGroupList(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        initContactModification(contact);
        fillContactFormWithoutPhoto(modifiedContact);
        submitContactModification();
        returnToHomePage();

    }

    private void submitContactModification() {
        click(By.cssSelector("input[type='submit'][name='update'][value='Update']"));
    }

    private void pressAddNewContact() {
        click(By.linkText("add new"));
    }

    private void fillContactFormWithPhoto(ContactData contact) {
        type(By.name("lastname"), contact.lastname());
        type(By.name("firstname"), contact.firstname());
        type(By.name("address"), contact.address());
        type(By.name("mobile"), contact.mobile());
        type(By.name("email"), contact.email());
        type(By.name("homepage"), contact.homepage());
        attach(By.name("photo"), contact.photo());

    }

    private void fillContactFormWithoutPhoto(ContactData contact) {
        type(By.name("lastname"), contact.lastname());
        type(By.name("firstname"), contact.firstname());
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

    private void initContactModification(ContactData contact) {
        click(By.cssSelector(String.format("a[href*='edit.php?id=%s'] img", contact.id())));
    }

    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public List<ContactData> getList() {
        var contacts = new ArrayList<ContactData>();
        var trs = manager.driver.findElements(By.cssSelector("tr[name='entry']"));
        for (var tr : trs) {
            var checkbox = tr.findElement(By.cssSelector("input[type='checkbox'][name='selected[]']"));
            var id = checkbox.getAttribute("value");
            var lastname = tr.findElement(By.cssSelector("td:nth-child(2)")).getText();
            var firstname = tr.findElement(By.cssSelector("td:nth-child(3)")).getText();
            contacts.add(new ContactData().withId(id).withLastname(lastname).withFirstname(firstname));
        }
        return contacts;
    }


}
