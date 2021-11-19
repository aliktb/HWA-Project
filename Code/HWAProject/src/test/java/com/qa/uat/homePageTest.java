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

public class homePageTest {


  private WebDriver driver;


  @BeforeEach
  void setup() {
    System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
    this.driver = new ChromeDriver();
    this.driver.manage().window().setSize(new Dimension(1366, 768));

  }

  @Test
  void visitLibraryTest() {

    driver.get("http://localhost:9000/index.html");

    WebElement visitLibraryButton =
        driver.findElement(By.xpath("/html/body/div/div/div/div/div[1]/div/a"));

    visitLibraryButton.click();

    Assertions.assertEquals("http://localhost:9000/HTML/ViewBooks.html", driver.getCurrentUrl());
  }

  @AfterEach
  void teardown() {
    driver.close();
  }

}
