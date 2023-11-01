package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {
  private WebDriver driver;
  private WebElement firstName;
  private WebElement middleName;
  private WebElement lastName;
  private WebElement address;
  private WebElement city;
  private WebElement state;
  private WebElement zip;
  private WebElement country;
  private WebElement telephone;

  public CheckoutPage(WebDriver driver, String id) {
    this.driver = driver;
    firstName = driver.findElement(By.cssSelector("#" + id + "\\:firstname"));
    middleName = driver.findElement(By.cssSelector("#" + id + "\\:middlename"));
    lastName = driver.findElement(By.cssSelector("#" + id + "\\:lastname"));
    address = driver.findElement(By.cssSelector("#" + id + "\\:street1"));
    city = driver.findElement(By.cssSelector("#" + id + "\\:city"));
    if (id.equals("shipping")) {
      state = driver.findElement(By.cssSelector("#" + id + "\\:country_id"));

    } else {
      state = driver.findElement(By.cssSelector("#" + id + "\\:region_id"));
    }
    zip = driver.findElement(By.cssSelector("#" + id + "\\:postcode"));
    country = driver.findElement(By.cssSelector("#" + id + "\\:country_id"));
    telephone = driver.findElement(By.cssSelector("#" + id + "\\:telephone"));
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

  public void inputAddress(String address) {
    this.address.sendKeys(address);
  }

  public void inputCity(String city) {
    this.city.sendKeys(city);
  }

  public void inputState(String state) {
    this.state.sendKeys(state);
  }

  public void inputZip(String zip) {
    this.zip.sendKeys(zip);
  }

  public void inputCountry(String country) {
    this.country.sendKeys(country);
  }

  public void inputTelephone(String telephone) {
    this.telephone.sendKeys(telephone);
  }
}
