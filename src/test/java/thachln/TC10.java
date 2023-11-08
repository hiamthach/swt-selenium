package thachln;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/*
1. Go to http://live.techpanda.org/index.php/backendlogin
2. Login the credentials provided
3. Go to Sales-> Orders menu
4. Input OrderId and FromDate -> ToDate
5. Click Search button
6. Screenshot capture.

Expected results:
1) File is downloaded successfully with full information.
*/

import org.testng.annotations.Test;

import driver.driverFactory;

public class TC10 {
  @Test
  public void testTC10() {

    WebDriver driver = driverFactory.getChromeDriver();

    String userName = "user01";
    String password = "guru99com";
    String orderId = "100021113";
    // from 7 days ago
    String fromDate = "11/1/2023";
    String toDate = "11/7/2023";

    try {
      // Step 1
      driver.get("http://live.techpanda.org/index.php/backendlogin");
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
      wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName("h2"))));

      // Step 2
      driver.findElement(By.id("username")).sendKeys(userName);
      driver.findElement(By.id("login")).sendKeys(password);
      driver.findElement(By.cssSelector("input.form-button")).click();

      driver.findElement(By.cssSelector("#message-popup-window > div.message-popup-head > a")).click();

      // Step 3
      driver.findElement(By.cssSelector("#nav > li:nth-child(1) > a > span")).click();

      driver.findElement(By.cssSelector("#nav > li:nth-child(1) > ul > li:nth-child(1) > a > span")).click();

      wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#sales_order_grid_export"))));

      // Step 4
      driver.findElement(By.cssSelector("#sales_order_grid_filter_real_order_id")).sendKeys(orderId);

      driver.findElement(By.cssSelector(".range .range-line:first-child > input")).sendKeys(fromDate);

      driver.findElement(By.cssSelector(".range .range-line:last-child > input")).sendKeys(toDate);

      // Step 5
      driver.findElement(By.cssSelector(".filter-actions button:last-child")).click();

      // Step 6 wait 2 second
      Thread.sleep(2000);

      // screenshot the result
      Utils.takeScreenshot(driver, "TC10.png");
      System.out.println("Test case TC10 is passed");
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail("Test case TC10 is failed:" + e.getMessage());
    }

    driver.quit();
  }

}
