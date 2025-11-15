package ru.stqa.mantis.manager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class ApplicationMan {

    private WebDriver driver;

    private String browser;
    private Properties properties;
    private SessionHelper sessionHelper;
    private HttpSessionHelper httpSessionHelper;
    private JamesCliHelper jamesCliHelper;
    private MailHelper mailHelper;
    private ContactHelp contacts;
    private LoginHelperM loginHelperM;


    public void init(String browser, Properties properties) {
        this.browser = browser;
        this.properties = properties;
        }

    public WebDriver driver() {
        if (driver == null) {
                if ("firefox".equals(browser)) {
                    driver = new FirefoxDriver();
                } else if ("chrome".equals(browser)) {
                    driver = new ChromeDriver();
                } else {
                    throw new IllegalArgumentException(String.format("Unknown browser %s", browser));
                }
                Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
                driver.get(properties.getProperty("web.baseUrl"));
                driver.manage().window().setSize(new Dimension(757, 691));
                //session().login(properties.getProperty("web.username"), properties.getProperty("web.password"));
        }
        return driver;
    }

    public SessionHelper session() {
        if (sessionHelper == null) {
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }

    public HttpSessionHelper http() {
        if (httpSessionHelper == null) {
            httpSessionHelper = new HttpSessionHelper(this);
        }
        return httpSessionHelper;
    }

    public JamesCliHelper jamesCli() {
        if (jamesCliHelper == null) {
            jamesCliHelper = new JamesCliHelper(this);
        }
        return jamesCliHelper;
    }

    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public LoginHelperM login() {
        if (loginHelperM == null) {
            loginHelperM = new LoginHelperM(this);
        }
        return loginHelperM;
    }
    public String property(String name) {
        return properties.getProperty(name);
    }

    public ContactHelp contacts() {
        if (contacts == null) {
            contacts = new ContactHelp(this);
        }
        return contacts;
    }


}
