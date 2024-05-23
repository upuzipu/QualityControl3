package org.example.lab3;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.cdimascio.dotenv.Dotenv;
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

public class ChatTest {

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
    public void chatTest() throws InterruptedException {

        /*
        Работает через раз по какой-то причине...
        Сначала была проблема, что из-за того, что на сайте инфа хранится в куках, он НЕ парсил данные и из-за этого
        вместо сценария нажатия на кнопку и появления чата, он предлагал мне зарегистрироваться, а потом как зарегистрировался
        появлялась кнопка, но при повторных ретестах её опять не было :/
        */

        WebElement chat_btn = driver.findElement(By.xpath("/html/body/div[6]/div"));
        chat_btn.click();
        Thread.sleep(1000);
        WebElement send_msg_btn = driver.findElement(By.xpath("//*[@id=\"spaces-home\"]/div[2]/div/div/div/div[2]/div[2]/div"));
        send_msg_btn.click();
        Thread.sleep(2000);
        WebElement i_want_demo_btn = driver.findElement(By.xpath("//*[@id=\"spaces-messages\"]/div[2]/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/button"));
        i_want_demo_btn.click();
        Thread.sleep(3000);
        WebElement i_want_demo_txt = driver.findElement(By.xpath("//*[@id=\"spaces-messages\"]/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div/div[1]/div[2]/div[1]/div/div[1]/div"));
        assertEquals("I want a demo!", i_want_demo_txt.getText());
    }
}
