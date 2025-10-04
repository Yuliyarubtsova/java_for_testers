package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactCreationTests extends TestBase{
    private static WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("http://localhost/addressbook/");
        driver.manage().window().setSize(new Dimension(1328, 699));
        driver.findElement(By.name("user")).sendKeys("admin");
        driver.findElement(By.name("pass")).click();
        driver.findElement(By.name("pass")).sendKeys("secret");
        driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
    }

    @AfterEach
    public void tearDown() {
        driver.findElement(By.linkText("Logout")).click();
        driver.quit();
    }

    @Test
    public void createContacts() {
        createContact("Ivan", "Ivanovich", "Moscow", "123456789", "x5@mail.ru", "veselyebobry.ru" );
    }

    private void createContact(String firstname, String lastname, String address, String mobile, String email, String homepage ) {
        driver.findElement(By.linkText("add new")).click();
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).sendKeys(firstname);
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).sendKeys(lastname);
        driver.findElement(By.name("address")).click();
        driver.findElement(By.name("address")).sendKeys(address);
        driver.findElement(By.name("mobile")).click();
        driver.findElement(By.name("mobile")).sendKeys(mobile);
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("homepage")).click();
        driver.findElement(By.name("homepage")).sendKeys(homepage);
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.linkText("home page")).click();
    }

    @Test
    public void createContactsWithEmpty() {
        createContact("", "", "", "", "", "" );
    }
}
