import org.example.AccountCreatedPage;
import org.example.RegisterAccountPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static util.TestUtil.generateRandomEmail;

public class RegisterAccountTests extends BaseTest{
    private RegisterAccountPage registerAccountPage;
    private String registerPageURL = "https://ecommerce-playground.lambdatest.io/index.php?route=account/register";

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Navigating to " + registerPageURL);
        driver.get(registerPageURL);
        registerAccountPage = new RegisterAccountPage(driver);
    }

    @Test
    public void registerNewAccountWithMandatoryFieldsTest() throws Exception {
        registerAccountPage.insertFirstName("Cristi");
        registerAccountPage.insertLastName("Hari");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("01234567");
        registerAccountPage.setPassword("Password123!");
        registerAccountPage.setPasswordConfirmation("Password123!");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();

        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        String actualText = accountCreatedPage.getParagraphText();
        String expectedText = "Congratulations! Your new account has been successfully created!";
        Assert.assertEquals(actualText, expectedText, "Actual text is not the expected one.");
    }
    @Test
    public void registerAccountWithoutFirstNameTest() {
        registerAccountPage.insertLastName("Hari");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("01234567");
        registerAccountPage.setPassword("Password123!");
        registerAccountPage.setPasswordConfirmation("Password123!");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();

        String actualValue = registerAccountPage.getFirstNameErrorMessage();
        String expectedValue = "First Name must be between 1 and 32 characters!";
        Assert.assertEquals(actualValue, expectedValue, "Error message is not the expected one.");
    }
}
