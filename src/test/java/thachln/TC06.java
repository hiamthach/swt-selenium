package thachln;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

// Test Steps:

// 1. Go to http://live.techpanda.org/

// 2. Click on my account link

// 3. Login in application using previously created credential

// 4. Click on MY WISHLIST link

// 5. In next page, Click ADD TO CART link

// 6. Enter general shipping country, state/province and zip for the shipping cost estimate

// 7. Click Estimate

// 8. Verify Shipping cost generated

// 9. Select Shipping Cost, Update Total

// 10. Verify shipping cost is added to total

// 11. Click "Proceed to Checkout"

// 12a. Enter Billing Information, and click Continue

// 12b. Enter Shipping Information, and click Continue

// 13. In Shipping Method, Click Continue

// 14. In Payment Information select 'Check/Money Order' radio button. Click Continue

// 15. Click 'PLACE ORDER' button

// 16. Verify Oder is generated. Note the order number

import org.testng.annotations.Test;

import driver.driverFactory;
import pom.CheckoutPage;
import pom.LoginPage;
import pom.PurchaseProduct;

public class TC06 {
  @Test
  public void testTC06() {

    WebDriver driver = driverFactory.getChromeDriver();
    String firstName = "Thach";
    String middleName = "Ngoc";
    String lastName = "Le";
    String email = "thachln@gmail.com";
    String password = "123456789";
    String address = "123 Phan Van Tri";
    String city = "Ho Chi Minh City";
    String state = "State";
    String zip = "123456";
    String country = "Vietnam";
    String telephone = "1234567890";

    try {
      PurchaseProduct purchaseProduct = new PurchaseProduct(driver, email, password);
      purchaseProduct.action();
      // // Step 1
      // driver.get("http://live.techpanda.org/");
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
      // wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName("h2"))));

      // // Step 2
      // driver.findElement(By.cssSelector(".links:nth-child(4) .first > a")).click();

      // // Step 3
      // LoginPage login = new LoginPage(driver);
      // login.inputEmail(email);
      // login.inputPassword(password);
      // driver.findElement(By.cssSelector("#send2")).click();

      // // Step 4
      // driver.findElement(By.cssSelector(".block-content > ul > li:nth-child(8) >
      // a")).click();

      // // Step 5
      // driver.findElement(By.cssSelector(".button[title='Add to Cart']")).click();

      // // Step 6
      // driver.findElement(By.cssSelector("#country")).sendKeys(country);
      // driver.findElement(By.cssSelector("#region")).sendKeys(state);
      // driver.findElement(By.cssSelector("#postcode")).sendKeys(zip);

      // // Step 7
      // driver.findElement(By.cssSelector(".buttons-set span > span")).click();

      // Step 8
      WebElement shippingCost = driver.findElement(By.cssSelector(".a-right .price"));
      System.out.println("Shipping cost: " + shippingCost.getText());

      // Step 9
      driver.findElement(By.cssSelector("#s_method_flatrate_flatrate")).click();
      driver.findElement(By.cssSelector(".button[title='Update Total']")).click();

      // Step 10
      WebElement total = driver.findElement(By.cssSelector(".a-right .price"));
      System.out.println("Total: " + total.getText());

      // Step 11
      driver.findElement(By.cssSelector(".button[title='Proceed to Checkout']")).click();

      // Step 12a
      WebElement billingAddress = driver.findElement(By.cssSelector("#billing-address-select"));
      Select selectBillingAddress = new Select(billingAddress);
      selectBillingAddress.selectByVisibleText("New Address");

      CheckoutPage billingInfo = new CheckoutPage(driver, "billing");
      billingInfo.inputFirstName(firstName);
      billingInfo.inputMiddleName(middleName);
      billingInfo.inputLastName(lastName);
      billingInfo.inputAddress(address);
      billingInfo.inputCity(city);
      billingInfo.inputState(state);
      billingInfo.inputZip(zip);
      billingInfo.inputCountry(country);
      billingInfo.inputTelephone(telephone);

      driver.findElement(By.cssSelector("#billing-buttons-container .button")).click();

      // Step 12b
      // Click pop-up open
      wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#shipping-address-select"))));

      WebElement shippingAddress = driver.findElement(By.cssSelector("#shipping-address-select"));
      Select selectShippingAddress = new Select(shippingAddress);
      selectShippingAddress.selectByVisibleText("New Address");

      CheckoutPage shippingInfo = new CheckoutPage(driver, "shipping");
      shippingInfo.inputFirstName(firstName);
      shippingInfo.inputMiddleName(middleName);
      shippingInfo.inputLastName(lastName);
      shippingInfo.inputAddress(address);
      shippingInfo.inputCity(city);
      shippingInfo.inputState(state);
      shippingInfo.inputZip(zip);
      shippingInfo.inputCountry(country);
      shippingInfo.inputTelephone(telephone);

      driver.findElement(By.cssSelector("#shipping-buttons-container .button")).click();

      // Step 13
      // Wait for the page to load
      wait.until(ExpectedConditions
          .visibilityOf(driver.findElement(By.cssSelector("#checkout-step-shipping_method"))));

      driver.findElement(By.cssSelector("#opc-shipping_method #shipping-method-buttons-container button")).click();

      // Step 14
      // Wait for the page to load
      wait.until(ExpectedConditions
          .visibilityOf(driver.findElement(By.cssSelector("#checkout-step-payment"))));
      driver.findElement(By.cssSelector("#checkout-step-payment #p_method_checkmo")).click();
      driver.findElement(By.cssSelector("#payment-buttons-container .button")).click();

      // Step 15
      // Wait for the page to load
      wait.until(ExpectedConditions
          .visibilityOf(driver.findElement(By.cssSelector("#checkout-step-review"))));
      driver.findElement(By.cssSelector("#checkout-step-review #review-buttons-container .button")).click();

      // Step 16
      WebElement orderNumber = driver.findElement(By.cssSelector(".col-main p:nth-child(1) a"));
      System.out.println("Order number: " + orderNumber.getText());

      // screenshot the result
      Utils.takeScreenshot(driver, "TC06.png");
      System.out.println("Test case TC06 is passed");
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail("Test case TC06 is failed:" + e.getMessage());
    }

    driver.quit();
  }

}
