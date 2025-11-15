package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import ru.stqa.mantis.model.ContactDataM;

import static ru.stqa.mantis.tests.TestBaseM.app;

public class ContactHelp extends HelperB {
    public ContactHelp(ApplicationMan manager) {
        super(manager);
    }

    public void createContact(ContactDataM contact) {
        pressSignUp();
        fillContactForm(contact);
        submitContactCreation();

    }

    private void submitContactCreation() {
        click(By.cssSelector("input[type='submit']"));
    }

    private void fillContactForm(ContactDataM contact) {
        type(By.name("username"), contact.username());
        type(By.name("email"), contact.email());
    }

    private void pressSignUp() {
        click(By.linkText("Signup for a new account"));
    }

    public void regEnd(String newUrl, String username, String password) {
        manager.driver().get(newUrl);
        fillEditAccountForm(username, password);
        click(By.xpath("//span[@class='bigger-110' and text()='Update User']"));
    }

    public void fillEditAccountForm(String username, String password) {
        type(By.name("realname"), username);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
    }
}
