package thachln;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

// Test Steps:

// 1. Goto http://live.techpanda.org/

// 2. Click on �MOBILE� menu

// 3. In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)

// 4. Click on Sony Xperia mobile

// 5. Read the Sony Xperia mobile from detail page.

// 6. Compare Product value in list and details page should be equal ($100).

import org.testng.annotations.Test;

import driver.driverFactory;

public class TC02 {
  @Test
  public void testTC02() {

    WebDriver driver = driverFactory.getChromeDriver();

    try {
      // Step 1
      driver.get("http://live.techpanda.org/");
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
      wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName("h2"))));

      // Step 2
      driver.findElement(By.linkText("MOBILE")).click();

      // Step 3
      String cost = driver.findElement(By.cssSelector("#product-price-1 > .price")).getText();

      // Step 4
      WebElement sonyXperia = driver.findElement(By.cssSelector(".item:nth-child(2) .product-name > a"));
      sonyXperia.click();
      // Step 5
      String cost2 = driver.findElement(By.cssSelector(".price")).getText();
      // Step 6
      Assert.assertEquals(cost, cost2);

      // screenshot the result
      Utils.takeScreenshot(driver, "TC02.png");
      System.out.println("Test case TC02 is passed");
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail("Test case TC02 is failed:" + e.getMessage());
    }

    driver.quit();
  }

}
