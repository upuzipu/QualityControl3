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

public class TopbarTest {
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
    public void productsVideoNavigation() throws InterruptedException {
        WebElement products_nav = driver.findElement(By.xpath("//*[@id=\"w-dropdown-toggle-0\"]/div"));
        products_nav.click();
        Thread.sleep(2000);
        WebElement link = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[1]/div[1]/nav/ul/li[1]/div/nav/div/div/div[1]/div[2]/div/div[1]"));
        String temp_text = link.getText();
        link.click();
        Thread.sleep(3000);
        WebElement header = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/h1"));
        assertEquals(temp_text, header.getText());
    }

    @Test
    public void productsEmailNavigation() throws InterruptedException {
        WebElement products_nav = driver.findElement(By.xpath("//*[@id=\"w-dropdown-toggle-0\"]/div"));
        products_nav.click();
        Thread.sleep(2000);
        WebElement link = driver.findElement(By.xpath("//*[@id=\"w-node-_0a717de6-5353-c150-5887-b6f44856a7aa-ed8dffe9\"]/div[2]/div/div[2]/a"));
        String temp_text = link.getText();
        link.click();
        Thread.sleep(3000);
        WebElement header = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/h1"));
        assertEquals(temp_text, header.getText());
    }

    @Test
    public void productsSocialNavigation() throws InterruptedException {
        WebElement products_nav = driver.findElement(By.xpath("//*[@id=\"w-dropdown-toggle-0\"]/div"));
        products_nav.click();
        Thread.sleep(2000);
        WebElement link = driver.findElement(By.xpath("//*[@id=\"w-node-_0a717de6-5353-c150-5887-b6f44856a7aa-ed8dffe9\"]/div[2]/div/div[3]/a"));
        String temp_text = link.getText();
        link.click();
        Thread.sleep(3000);
        WebElement header = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/h1"));
        assertEquals(temp_text, header.getText());
    }
}
