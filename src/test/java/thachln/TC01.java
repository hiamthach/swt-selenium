package thachln;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import driver.driverFactory;

// Test Steps

// Step 1. Go to http://live.techpanda.org/

// Step 2. Verify Title of the page

// Step 3. Click on -> MOBILE -> menu

// Step 4. In the list of all mobile , select SORT BY -> dropdown as name

// Step 5. Verify all products are sorted by name

public class TC01 {
  @Test
  public void testTC01() {
    WebDriver driver = driverFactory.getChromeDriver();

    try {
      // Step 1
      driver.get("http://live.techpanda.org/");
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
      wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName("h2"))));

      String pageTitle = driver.findElement(By.tagName("h2")).getText();

      // Step 2 if not match throw exception
      Assert.assertEquals(pageTitle, "THIS IS DEMO SITE FOR   ");

      // Step 3
      driver.findElement(By.linkText("MOBILE")).click();

      // Step 4
      driver.findElement(By.cssSelector("select[title='Sort By']")).click();
      driver
          .findElement(
              By.cssSelector("option[value='http://live.techpanda.org/index.php/mobile.html?dir=asc&order=name']"))
          .click();

      // Step 5
      String[] productNames = new String[3];

      for (int i = 0; i < 3; i++) {
        productNames[i] = driver
            .findElement(By.cssSelector("ul.products-grid > li:nth-child(" + (i + 1) + ") h2.product-name > a"))
            .getText();
      }

      if (!productNames[0].equals("IPHONE") || !productNames[1].equals("SAMSUNG GALAXY")
          || !productNames[2].equals("SONY XPERIA"))
        throw new Exception("Product names are not match");

      // screenshot the result

      Utils.takeScreenshot(driver, "TC01.png");
      System.out.println("Test case TC01 is passed");
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail("Test case TC01 is failed:" + e.getMessage());
    }

    driver.quit();
  }

}
