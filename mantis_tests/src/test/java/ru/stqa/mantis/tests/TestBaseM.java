package ru.stqa.mantis.tests;

import org.junit.jupiter.api.BeforeEach;
import ru.stqa.mantis.manager.ApplicationMan;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestBaseM {
    public static ApplicationMan app;

    @BeforeEach
    public void setUp() throws IOException {
        if (app == null) {
            var properties = new Properties();
            properties.load(new FileReader(System.getProperty("target","local.properties")));
            app = new ApplicationMan();
            app.init(System.getProperty("browser", "firefox"), properties);
        }
    }
}
