package thachln;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

// Test Steps:

// 1. Go to http://live.techpanda.org/

// 2. Click on �MOBILE� menu

// 3. In mobile products list , click on �Add To Compare� for 2 mobiles (Sony Xperia & Iphone)

// 4. Click on �COMPARE� button. A popup window opens

// 5. Verify the pop-up window and check that the products are reflected in it

// Heading "COMPARE PRODUCTS" with selected products in it.

// 6. Close the Popup Windows

import org.testng.annotations.Test;

import driver.driverFactory;

public class TC04 {
  @Test
  public void testTC04() {

    WebDriver driver = driverFactory.getChromeDriver();

    try {
      // Step 1
      driver.get("http://live.techpanda.org/");
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
      wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName("h2"))));

      // Step 2
      driver.findElement(By.linkText("MOBILE")).click();

      // Step 3
      WebElement sonyXperiaCompare = driver.findElement(By.cssSelector(".item:nth-child(2) .link-compare"));
      sonyXperiaCompare.click();

      WebElement iphoneCompare = driver.findElement(By.cssSelector(".item:nth-child(3) .link-compare"));
      iphoneCompare.click();

      // Step 4
      WebElement compare = driver.findElement(By.cssSelector(".button[title='Compare']"));
      compare.click();

      for (String handle : driver.getWindowHandles()) {
        driver.switchTo().window(handle);
      }

      // Step 5
      WebElement popup = driver.findElement(By.cssSelector(".page-title h1"));
      Assert.assertEquals(popup.getText(), "COMPARE PRODUCTS");

      // screenshot the result
      Utils.takeScreenshot(driver, "TC04.png");
      // Step 6
      WebElement close = driver.findElement(By.cssSelector(".button[title='Close Window']"));
      close.click();

      for (String handle : driver.getWindowHandles()) {
        driver.switchTo().window(handle);
      }

      System.out.println("Test case TC04 is passed");
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail("Test case TC04 is failed:" + e.getMessage());
    }

    driver.quit();
  }

}
