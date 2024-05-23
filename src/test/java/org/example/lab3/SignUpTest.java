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

public class SignUpTest {
    private WebDriver driver;


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
    public void signUpTest() throws InterruptedException {
        String email_text = "egornovikov" + SaltGenerator.generateSalt() + "@gmail.com";
        String password_text = "password" + SaltGenerator.generateSalt();
        String workspace = "workspace" + SaltGenerator.generateSalt();
        WebElement signup_btn = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[1]/div[2]/div[1]/button"));
        signup_btn.click();
        Thread.sleep(2000);
        WebElement first_name = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div/span/form/div[1]/div[1]/span/div/input"));
        WebElement last_name = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div/span/form/div[1]/div[2]/span/div/input"));
        WebElement email = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div/span/form/div[1]/div[3]/span/div/input"));
        WebElement password = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div/span/form/div[1]/div[4]/span/div/input"));
        WebElement confirm_pass = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div/span/form/div[1]/div[5]/span/div/input"));
        WebElement privacy = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div/span/form/div[2]/div/label"));
        first_name.sendKeys("TestUser");
        last_name.sendKeys("UserTest");
        email.sendKeys(email_text);
        password.sendKeys(password_text);
        confirm_pass.sendKeys(password_text);
        privacy.click();
        WebElement create_acc = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/div/span/form/section/button"));
        create_acc.click();
        Thread.sleep(10000);
        WebElement workspace_name = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/span[2]/form/div/span/div/input"));
        workspace_name.sendKeys(workspace);
        WebElement create_workspace = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[3]/span[2]/form/section/button"));
        create_workspace.click();
        Thread.sleep(10000);
        WebElement my_email = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/header/div[2]/div/div[3]/div/div/div[1]/div/div/p[2]"));
        assertEquals(my_email.getText(), email_text);
    }

}
