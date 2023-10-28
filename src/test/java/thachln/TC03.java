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

// 3. In the list of all mobile , click on �ADD TO CART� for Sony Xperia mobile

// 4. Change �QTY� value to 1000 and click �UPDATE� button. Expected that an error is displayed

// "The requested quantity for "Sony Xperia" is not available.

// 5. Verify the error message

// 6. Then click on �EMPTY CART� link in the footer of list of all mobiles. A message "SHOPPING CART IS EMPTY" is shown.

// 7. Verify cart is empty

import org.testng.annotations.Test;

import driver.driverFactory;

public class TC03 {
  @Test
  public void testTC03() {

    WebDriver driver = driverFactory.getChromeDriver();

    try {
      // Step 1
      driver.get("http://live.techpanda.org/");
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
      wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName("h2"))));

      // Step 2
      driver.findElement(By.linkText("MOBILE")).click();

      // Step 3
      WebElement sonyXperia = driver.findElement(By.cssSelector(".item:nth-child(2) .btn-cart"));
      sonyXperia.click();

      // Step 4
      WebElement qty = driver.findElement(By.cssSelector(".first .input-text.qty"));
      qty.clear();
      qty.sendKeys("1000");

      WebElement update = driver.findElement(By.cssSelector(".button.btn-update"));
      update.click();

      // Step 5
      WebElement errorMessage = driver.findElement(By.cssSelector(".error-msg ul:nth-child(1) > li > span"));
      Assert.assertEquals(errorMessage.getText(), "The requested quantity for \"Sony Xperia\" is not available.");

      // Step 6
      driver.findElement(By.cssSelector("#empty_cart_button")).click();

      // Step 7
      WebElement emptyCartMessage = driver.findElement(By.cssSelector(".page-title > h1"));
      Assert.assertEquals(emptyCartMessage.getText(), "SHOPPING CART IS EMPTY");
      // screenshot the result
      Utils.takeScreenshot(driver, "TC03.png");
      System.out.println("Test case TC03 is passed");
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail("Test case TC03 is failed:" + e.getMessage());
    }

    driver.quit();
  }

}
