package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Register {
  private WebDriver driver;
  private WebElement firstName;
  private WebElement middleName;
  private WebElement lastName;
  private WebElement email;
  private WebElement password;
  private WebElement confirmPassword;

  public Register(WebDriver driver) {
    this.driver = driver;
    firstName = driver.findElement(By.cssSelector("#firstname"));
    middleName = driver.findElement(By.cssSelector("#middlename"));
    lastName = driver.findElement(By.cssSelector("#lastname"));
    email = driver.findElement(By.cssSelector("#email_address"));
    password = driver.findElement(By.cssSelector("#password"));
    confirmPassword = driver.findElement(By.cssSelector("#confirmation"));
  }

  public void inputFirstName(String firstName) {
    this.firstName.sendKeys(firstName);
  }

  public void inputMiddleName(String middleName) {
    this.middleName.sendKeys(middleName);
  }

  public void inputLastName(String lastName) {
    this.lastName.sendKeys(lastName);
  }

  public void inputEmail(String email) {
    this.email.sendKeys(email);
  }

  public void inputPassword(String password) {
    this.password.sendKeys(password);
  }

  public void inputConfirmPassword(String confirmPassword) {
    this.confirmPassword.sendKeys(confirmPassword);
  }
}
