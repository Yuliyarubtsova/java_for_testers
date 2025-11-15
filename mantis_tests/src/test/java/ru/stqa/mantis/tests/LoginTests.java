package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTests extends TestBaseM {

    @Test
    void canLogin() {
        app.login().loginApp("administrator", "root");
        Assertions.assertTrue(app.login().isLoggedIn());
    }


}
