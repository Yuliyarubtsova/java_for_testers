package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.UserData;
import ru.stqa.mantis.model.SignUpData;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class UserRegistrationTests extends TestBaseM {

    static Stream<SignUpData> signUpData() {
        Supplier<SignUpData> randomUser = () -> new SignUpData()
                .withUsername(CommonFunctions.randomString(10))
                .withPassword("password");
        return Stream.generate(randomUser).limit(1);

    }

    static Stream<UserData> userData() {
        Supplier<UserData> randomUser = () -> new UserData()
                .withUsername(CommonFunctions.randomString(10))
                .withPassword("password")
                .withRealname(CommonFunctions.randomString(10))
                .withEmail(String.format("%s@localhost", CommonFunctions.randomString(10)));
        return Stream.generate(randomUser).limit(1);

    }

    @ParameterizedTest
    @MethodSource("signUpData")
    public void canRegisterUser(SignUpData signUpData) throws InterruptedException {
        var username = signUpData.username();
        var password = signUpData.password();
        var email = String.format("%s@localhost", username);
        // создать пользователя (адрес) на почтовом сервере (JamesHelper)
        app.jamesCli().addUser(String.format("%s@localhost", username),
                "password");

        // заполняем форму создания и отправляем (браузер)
        var contact = new UserData()
                .withUsername(username)
                .withEmail(email);
        app.contacts().createContact(contact);

        // ждем почту (MailHelper)
        // извлекаем ссылку из письма
        var newUrl = app.mail().extractUrl(email, password);

        // проходим по ссылке и завершаем регистрацию (браузер)
        app.contacts().regEnd(newUrl, username, password);
        // проверяем, что пользователь может залогиниться (HttpSessionHelper)
        app.login().loginApp(username, password);
        Assertions.assertTrue(app.login().isLoggedIn());
    }

    @ParameterizedTest
    @MethodSource("userData")
    public void canRegisterUserWithApiRest(UserData userData) throws InterruptedException {
        var username = userData.username();
        var password = userData.password();
        var email = userData.email();
        // создать пользователя (адрес) на почтовом сервере (JamesHelper)
        app.jamesApi().addUser(userData);

        // заполняем форму создания и отправляем (браузер)
        app.rest().addUser(userData);

        // ждем почту (MailHelper)
        // извлекаем ссылку из письма
        var newUrl = app.mail().extractUrl(email, password);

        // проходим по ссылке и завершаем регистрацию (браузер)
        app.contacts().regEnd(newUrl, username, password);
        // проверяем, что пользователь может залогиниться (HttpSessionHelper)
        app.login().loginApp(username, password);
        Assertions.assertTrue(app.login().isLoggedIn());
    }


}
