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

public class editCustomerTest {

  private WebDriver driver;

  @BeforeEach
  void setup() {
    System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
    this.driver = new ChromeDriver();
    this.driver.manage().window().setSize(new Dimension(1366, 768));

  }

  @Test
  void deleteBookTest() {

    driver.get("http://localhost:9000/HTML/EditCustomer.html");

    WebElement deleteButton1 =
        driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[9]/button[2]"));

    deleteButton1.click();

    WebElement deleteButton2 = driver.findElement(By.id("deleteCustomerButton"));

    deleteButton2.click();

    driver.manage().timeouts().implicitlyWait(1L, TimeUnit.SECONDS);


    WebElement deleteSuccessAlert = driver.findElement(By.id("alertUpdateCustomerDiv"));

    Assertions.assertTrue(deleteSuccessAlert.getText().contains("Success"));

  }


  @AfterEach
  void teardown() {
    driver.close();
  }

}
