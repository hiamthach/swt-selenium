package thachln;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/*  Verify Discount Coupon works correctly

Test Case Description:

1. Go to http://live.techpanda.org/

2. Go to Mobile and add IPHONE to cart

3. Enter Coupon Code

4. Verify the discount generated

TestData:  Coupon Code: GURU50

Expected result:

1) Price is discounted by 5%

*/

import org.testng.annotations.Test;

import driver.driverFactory;

public class TC09 {
  @Test
  public void testTC09() {

    WebDriver driver = driverFactory.getChromeDriver();

    String COUPON_CODE = "GURU50";

    try {
      // Step 1
      driver.get("http://live.techpanda.org/");
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
      wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName("h2"))));

      driver.findElement(By.linkText("MOBILE")).click();

      WebElement iphone = driver.findElement(By.cssSelector(".item:first-child .actions > .btn-cart"));
      iphone.click();

      String priceBefore = driver.findElement(By.cssSelector("#shopping-cart-totals-table tfoot .price"))
          .getText();

      // remove the $ sign and the ',' sign

      Float priceBeforeFloat = Float.parseFloat(priceBefore.substring(1).replace(",", ""));

      System.out.println("Price before: " + priceBeforeFloat);

      WebElement couponCode = driver.findElement(By.cssSelector("#coupon_code"));

      couponCode.sendKeys(COUPON_CODE);

      driver.findElement(By.cssSelector(".discount-form .button-wrapper button:first-child")).click();

      String priceDiscount = driver
          .findElement(By.cssSelector("#shopping-cart-totals-table tbody tr:last-child .price")).getText();
      // remove the -$ sign and the ',' sign
      priceDiscount = priceDiscount.substring(2).replace(",", "");

      Float priceDiscountFloat = Float.parseFloat(priceDiscount);

      System.out.println("Price discount: " + priceDiscountFloat);

      Float expectedPrice = priceBeforeFloat * 0.05f;

      // check the discount price 5%
      Assert.assertEquals(priceDiscountFloat, expectedPrice);

      // check the grand total after discount
      String priceAfter = driver.findElement(By.cssSelector("#shopping-cart-totals-table tfoot .price")).getText();

      Assert.assertNotEquals(priceBefore, priceAfter);

      // screenshot the result
      Utils.takeScreenshot(driver, "TC09.png");
      System.out.println("Test case TC09 is passed");
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail("Test case TC09 is failed:" + e.getMessage());
    }

    driver.quit();
  }

}
