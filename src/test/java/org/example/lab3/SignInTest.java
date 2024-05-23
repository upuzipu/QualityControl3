package org.example.lab3;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SignInTest {
    private WebDriver driver;
    Dotenv dotenv = Dotenv.load();


    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1400, 1080));
        driver.get("https://www.buzz.ai/");
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
    }


    @AfterEach
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void sighInTest() throws InterruptedException {
        String my_login = dotenv.get("MY_LOGIN");
        String my_password = dotenv.get("MY_PASSWORD");
        WebElement signin_btn = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[1]/div[2]/a"));
        signin_btn.click();
        Set<String> windowHandles = driver.getWindowHandles();
        driver.switchTo().window(windowHandles.toArray()[1].toString());
        Thread.sleep(5000);
        WebElement email_input = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div/form/div[1]/span/div/input"));
        email_input.sendKeys(my_login);
        WebElement password_input = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div/form/div[2]/span/div/input"));
        password_input.sendKeys(my_password);
        WebElement sign_in_btn = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div/form/button"));
        sign_in_btn.click();
        Thread.sleep(10000);
        WebElement my_account = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/header/div[2]/div/div[3]/div/div/div[1]/div/div/p[2]"));
        assertEquals(my_account.getText(), my_login);
    }
}
