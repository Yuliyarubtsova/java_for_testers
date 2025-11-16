package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.UserData;

import java.util.stream.Stream;

public class UserCreationTests extends TestBaseM{
    public static Stream<String> randomUser() {
        return Stream.of(CommonFunctions.randomString(8));
    }
    @ParameterizedTest
    @MethodSource("userData")
    void canCreateUser(UserData userData) {
        var email = String.format("%s@localhost", userData);
        var password = "password";
        app.jamesApi().addUser(userData);
        var contact = new UserData()
                .withUsername(userData.username())
                .withEmail(email);

        app.user().startCreation(contact);

      //  var newUrl = app.mail().extractUrl(email, password);
       // app.contacts().regEnd(newUrl, username, password);
       // app.login().loginApp(user, password);
        Assertions.assertTrue(app.login().isLoggedIn());

//        var messages = app.mail().receive(email, password, Duration.ofSeconds(10));
//        var url = app.mail().extractUrl(messages.get(0).content());
//
//        app.user().finishCreation(url, password);

     //   app.http().login(user, password);
     //   Assertions.assertTrue(app.http().isLoggedIn());
    }
}
