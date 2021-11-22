package com.qa.uat;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ViewCustomerTest {

  private WebDriver driver;

  @BeforeEach
  void setup() {
    System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
    this.driver = new ChromeDriver();
    this.driver.manage().window().setSize(new Dimension(1366, 768));

  }

  @Test
  void buttonTest() {

    driver.get("http://localhost:9000/HTML/SearchCustomers.html");

    WebElement textBox = driver.findElement(By.id("idNumberInput"));
    WebElement searchButton = driver.findElement(By.id("searchButton"));

    textBox.sendKeys("0");
    searchButton.click();
    driver.manage().timeouts().implicitlyWait(1L, TimeUnit.SECONDS);

    WebElement noIDCard = driver.findElement(By.xpath("//*[@id=\"starterDiv\"]/div"));

    System.out.println(noIDCard.getCssValue("backgroundColor"));

    Assertions.assertTrue(noIDCard.getCssValue("backgroundColor").contains("rgba(255, 193, 7, 1)"));
  }

  @AfterEach
  void teardown() {
    driver.close();
  }

}
