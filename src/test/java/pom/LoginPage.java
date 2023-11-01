package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
  private WebDriver driver;
  private WebElement email;
  private WebElement password;

  public LoginPage(WebDriver driver) {
    this.driver = driver;
    email = driver.findElement(By.cssSelector("#email"));
    password = driver.findElement(By.cssSelector("#pass"));
  }

  public void inputEmail(String email) {
    this.email.sendKeys(email);
  }

  public void inputPassword(String password) {
    this.password.sendKeys(password);
  }
}
