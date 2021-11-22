package com.qa.uat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ViewBooksTest {

  private WebDriver driver;

  @BeforeEach
  void setup() {
    System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
    this.driver = new ChromeDriver();
    this.driver.manage().window().setSize(new Dimension(1366, 768));

  }

  @Test
  void buttonTest() {

    driver.get("http://localhost:9000/HTML/ViewBooks.html");

    WebElement anchorHome =
        driver.findElement(By.xpath("//*[@id=\"navbarNavDropdown\"]/ul/li[1]/a"));

    System.out.println(anchorHome);

    anchorHome.click();

    Assertions.assertEquals("http://localhost:9000/index.html", driver.getCurrentUrl());
  }

  @AfterEach
  void teardown() {
    driver.close();
  }

}
