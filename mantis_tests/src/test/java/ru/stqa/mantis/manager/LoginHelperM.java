package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class LoginHelperM extends HelperB{
    public LoginHelperM(ApplicationMan manager) {
        super(manager);
    }

    public void loginApp(String username, String password) {

        type(By.name("username"), username);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"),password);
        click(By.cssSelector("input[type='submit']"));
    }

    public boolean isLoggedIn() {
        return isElementPresent(By.cssSelector("span.user-info"));
    }
    }

