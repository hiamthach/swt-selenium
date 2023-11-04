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

// 4. Click on 'REORDER' link , change QTY & click Update

// 5. Verify Grand Total is changed

// 6. Complete Billing & Shipping Information

// 7. Verify order is generated and note the order number

import org.testng.annotations.Test;

import driver.driverFactory;
import pom.CheckoutPage;
import pom.LoginPage;

public class TC08 {
  @Test
  public void testTC08() {

    WebDriver driver = driverFactory.getChromeDriver();
    String firstName = "Thach";
    String middleName = "Ngoc";
    String lastName = "Le";
    String email = "thachln@gmail.com";
    String password = "123456789";
    String address = "123 Phan Van Tri";
    String city = "Ho Chi Minh City";
    String state = "Georgia";
    String zip = "123456";
    String country = "United States";
    String telephone = "1234567890";
    String qty = "20";

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
      driver.findElement(By.cssSelector("#my-orders-table .first:first-child .link-reorder")).click();
      String grandTotalSelector = "#shopping-cart-totals-table tfoot .a-right:nth-child(2) .price";
      String grandTotal = driver.findElement(By.cssSelector(grandTotalSelector)).getText();

      WebElement qtyInput = driver
          .findElement(By.cssSelector("#shopping-cart-table tbody .first:first-child .product-cart-actions .qty"));
      qtyInput.clear();
      qtyInput.sendKeys(qty);
      driver
          .findElement(
              By.cssSelector("#shopping-cart-table tbody .first:first-child .product-cart-actions .btn-update"))
          .click();

      // Step 5
      String newGrandTotal = driver.findElement(By.cssSelector(grandTotalSelector)).getText();

      Assert.assertNotEquals(grandTotal, newGrandTotal);

      System.out.println("Grand Total: " + grandTotal);

      // Step 6
      driver.findElement(By.cssSelector(".button[title='Proceed to Checkout']")).click();

      // Step 7
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

      driver.findElement(By.cssSelector("#billing\\:use_for_shipping_no")).click();
      driver.findElement(By.cssSelector("#billing-buttons-container .button")).click();

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

      wait.until(ExpectedConditions
          .visibilityOf(driver.findElement(By.cssSelector("#checkout-step-shipping_method"))));

      driver.findElement(By.cssSelector("#shipping-method-buttons-container button")).click();

      wait.until(ExpectedConditions
          .visibilityOf(driver.findElement(By.cssSelector("#checkout-step-payment"))));
      driver.findElement(By.cssSelector("#checkout-step-payment #p_method_checkmo")).click();
      driver.findElement(By.cssSelector("#payment-buttons-container .button")).click();

      wait.until(ExpectedConditions
          .visibilityOf(driver.findElement(By.cssSelector("#checkout-step-review"))));
      driver.findElement(By.cssSelector("#checkout-step-review #review-buttons-container .button")).click();

      // Step 8
      // wait for the page navigating
      wait.until(ExpectedConditions.urlContains("success"));

      WebElement orderNumber = driver
          .findElement(By.cssSelector(".sub-title ~ p > a"));
      System.out.println("Order number: " + orderNumber.getText());

      // screenshot the result
      Utils.takeScreenshot(driver, "TC08.png");
      System.out.println("Test case TC08 is passed");
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail("Test case TC08 is failed:" + e.getMessage());
    }

    driver.quit();
  }

}
