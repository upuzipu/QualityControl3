package org.example.lab3;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WaitlistTest {
    private WebDriver driver;
    private final String email = "JudasBidlo" + SaltGenerator.generateSalt() + "@gmail.com";
    private final String phone = SaltGenerator.generateSalt();

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
    public void waitListTest() throws InterruptedException {
        WebElement first_name_input = driver.findElement(By.xpath("/html/body/div/form/fieldset[1]/div[1]/div/input"));
        WebElement last_name_input = driver.findElement(By.xpath("/html/body/div/form/fieldset[1]/div[2]/div/input"));
        WebElement email_input = driver.findElement(By.xpath("/html/body/div/form/fieldset[2]/div[1]/div/input"));
        WebElement phone_input = driver.findElement(By.xpath("/html/body/div/form/fieldset[2]/div[2]/div/input"));
        String first_name = "TestUser";
        first_name_input.sendKeys(first_name);
        String last_name = "TestUser";
        last_name_input.sendKeys(last_name);
        email_input.sendKeys(email);
        phone_input.sendKeys(phone);
        WebElement success_btn = driver.findElement(By.xpath("/html/body/div/form/div[1]/div[2]/input"));
        success_btn.click();
        Thread.sleep(2000);
        WebElement success_txt = driver.findElement(By.xpath("/html/body/div/div/p"));
        String success_msg = "You are now on the waitlist";
        assertEquals(success_txt.getText(), success_msg);
    }
}
