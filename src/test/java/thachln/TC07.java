package thachln;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

// Test Steps:

// 1. Go to http://live.techpanda.org/

// 2. Click on My Account link

// 3. Login in application using previously created credential

// 4. Click on 'My Orders'

// 5. Click on 'View Order'

// 6. Click on 'Print Order' link

import org.testng.annotations.Test;

import driver.driverFactory;
import pom.LoginPage;

public class TC07 {
  @Test
  public void testTC07() {

    WebDriver driver = driverFactory.getChromeDriver();
    String email = "thachln@gmail.com";
    String password = "123456789";

    try {
      // Step 1
      driver.get("http://live.techpanda.org/");
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
      wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName("h2"))));

      // Step 2
      driver.findElement(By.cssSelector(".links:nth-child(4) .first > a")).click();

      // Step 3
      LoginPage loginPage = new LoginPage(driver);
      loginPage.inputEmail(email);
      loginPage.inputPassword(password);

      driver.findElement(By.cssSelector("#send2")).click();

      // Step 4
      driver.findElement(By.cssSelector(".block-content > ul > li:nth-child(4) > a")).click();

      // Step 5
      driver.findElement(By.cssSelector("#my-orders-table .first:first-child a:first-child")).click();

      // Step 6
      driver.findElement(By.cssSelector(".link-print")).click();

      // screenshot the result
      Utils.takeScreenshot(driver, "TC07.png");
      System.out.println("Test case TC07 is passed");
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail("Test case TC07 is failed:" + e.getMessage());
    }

    driver.quit();
  }

}
