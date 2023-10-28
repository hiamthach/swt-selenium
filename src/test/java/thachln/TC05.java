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

// 2. Click on my account link

// 3. Click Create an Account link and fill New User information excluding the registered Email ID.

// 4. Click Register

// 5. Verify Registration is done. Expected account registration done.

// 6. Go to TV menu

// 7. Add product in your wish list - use product - LG LCD

// 8. Click SHARE WISHLIST

// 9. In next page enter Email and a message and click SHARE WISHLIST

// 10.Check wishlist is shared. Expected wishlist shared successfully.

import org.testng.annotations.Test;

import bom.Register;
import driver.driverFactory;

public class TC05 {
  @Test
  public void testTC05() {

    WebDriver driver = driverFactory.getChromeDriver();

    try {
      // Step 1
      driver.get("http://live.techpanda.org/");
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
      wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName("h2"))));

      // Step 2
      driver.findElement(By.cssSelector(".links:nth-child(4) .first > a")).click();

      // Step 3
      driver.findElement(By.cssSelector(".new-users .button")).click();

      Register register = new Register(driver);
      register.inputFirstName("Thach");
      register.inputMiddleName("Ngoc");
      register.inputLastName("Le");
      register.inputEmail("thachln" + System.currentTimeMillis() + "@gmail.com");
      register.inputPassword("123456789");
      register.inputConfirmPassword("123456789");

      // Step 4
      driver.findElement(By.cssSelector(".buttons-set .button[title='Register']")).click();

      // Step 5
      WebElement successMessage = driver.findElement(By.cssSelector(".success-msg span"));
      if (!successMessage.getText().equals("Thank you for registering with Main Website Store.")) {
        throw new Exception("The registration is not successful");
      }

      // Step 6
      driver.findElement(By.linkText("TV")).click();

      // Step 7
      driver.findElement(By.cssSelector(".item:nth-child(1) .link-wishlist")).click();

      // Step 8
      driver.findElement(By.cssSelector(".button.btn-share")).click();

      // Step 9
      WebElement emailInput = driver.findElement(By.cssSelector("#email_address"));
      emailInput.sendKeys("thachsdafsf@gmail.com");
      WebElement messageInput = driver.findElement(By.cssSelector("#message"));
      messageInput.sendKeys("Hello");

      driver.findElement(By.cssSelector(".buttons-set .button[title='Share Wishlist']")).click();

      // Step 10
      String successMessageText = driver.findElement(By.cssSelector(".success-msg span")).getText();
      if (!successMessageText.equals("Your Wishlist has been shared.")) {
        throw new Exception("The wishlist is not shared");
      }

      // screenshot the result
      Utils.takeScreenshot(driver, "TC05.png");
      System.out.println("Test case TC05 is passed");
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail("Test case TC05 is failed:" + e.getMessage());
    }

    driver.quit();
  }

}
