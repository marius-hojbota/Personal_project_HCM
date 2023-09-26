package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterAccountPage extends BasePage{

    public RegisterAccountPage(WebDriver driver) { this.driver = driver; }

    private By firstNameInput = By.id("input-firstname");
    private By lastNameInput = By.id("input-lastname");
    private By emailInput = By.id("input-email");
    private By telephoneInput = By.id("input-telephone");
    private By passwordInput = By.id("input-password");
    private By passwordConfirmationInput = By.id("input-confirm");
    private By privacyPolicyCheckbox = By.xpath("//label[@for='input-agree']");
    private By continueButton = By.xpath("//input[@value='Continue']");
    private By firstNameErrorElement = By.xpath(".//input[@name = 'firstname']/following-sibling::div");

    public void insertFirstName(String firstName) { driver.findElement(firstNameInput).sendKeys(firstName); }
    public void insertLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }
    public void insertEmail(String email){
        driver.findElement(emailInput).sendKeys(email);
    }
    public void insertPhoneNumber(String phoneNumber){
        driver.findElement(telephoneInput).sendKeys(phoneNumber);
    }
    public void setPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }
    public void setPasswordConfirmation(String passwordConfirmation) { driver.findElement(passwordConfirmationInput).sendKeys(passwordConfirmation); }
    public void checkPrivacyPolicy() {
        driver.findElement(privacyPolicyCheckbox).click();
    }
    public void clickContinue() {
        driver.findElement(continueButton).click();
    }
    public String getFirstNameErrorMessage() {
        return driver.findElement(firstNameErrorElement).getText();
    }

}
