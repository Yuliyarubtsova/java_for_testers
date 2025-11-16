package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import ru.stqa.mantis.model.UserData;
import ru.stqa.mantis.model.MailMessage;

import java.util.List;

import static ru.stqa.mantis.tests.TestBaseM.app;

public class UserHelper extends HelperB{
    public UserHelper(ApplicationMan manager) {
        super(manager);
    }

    public void startCreation(UserData contact) {
        app.login().loginApp("administrator", "root");
        pressAddNewContact();
        fillContactForm(contact);
        submitContactCreation();

    }

    private void submitContactCreation() {
        click(By.cssSelector("input[type='submit'][value='Create User']"));

    }

    private void fillContactForm(UserData contact) {
        type(By.name("username"), contact.username());
        type(By.name("realname"), contact.realname());
        type(By.name("email"), contact.email());

    }

    private void pressAddNewContact() {
        click(By.xpath("//a[.//span[@class='menu-text' and normalize-space()='Manage']]"));
        click(By.cssSelector("a[href='/mantisbt-2.26.4/manage_user_page.php']"));
        click(By.cssSelector("a[href='manage_user_create_page.php']"));

    }


    private void fillEditAccountForm(String username) {
    }

    public void finishCreation(List<MailMessage> url, String password) {
        manager.driver().get(url.toString());
        fillEditAccountForm(password);

        click(By.xpath("//span[@class='bigger-110' and text()='Update User']"));
    }
}

