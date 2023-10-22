import org.example.AccountCreatedPage;
import org.example.DashboardPage;
import org.example.RegisterAccountPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static util.TestUtil.generateRandomEmail;

public class RegisterAccountTests extends BaseTest {
    private RegisterAccountPage registerAccountPage;
    private DashboardPage dashboardPage;
    private String registerPageURL = "https://ecommerce-playground.lambdatest.io/index.php?route=account/register";

    @BeforeClass
    public void beforeClass() {
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Navigating to " + registerPageURL);
        driver.get(registerPageURL);
        registerAccountPage = new RegisterAccountPage(driver);
        dashboardPage = new DashboardPage(driver);
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
        dashboardPage.clickLogoutButton();
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
        Assert.assertEquals(actualValue, expectedValue, "The error message is not the expected one.");
    }
    @Test
    public void registerAccountWithoutLastNameTest() {
        registerAccountPage.insertFirstName("Cristi");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("01234567");
        registerAccountPage.setPassword("Password123!");
        registerAccountPage.setPasswordConfirmation("Password123!");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();

        String actualValue = registerAccountPage.getLastNameErrorMessage();
        String expectedValue = "Last Name must be between 1 and 32 characters!";
        Assert.assertEquals(actualValue, expectedValue, "The error message is not the expected one.");
    }
    @Test
    public void registerAccountWithoutEmailTest() {
        registerAccountPage.insertFirstName("Cristi");
        registerAccountPage.insertLastName("Hari");
        registerAccountPage.insertPhoneNumber("01234567");
        registerAccountPage.setPassword("Password123!");
        registerAccountPage.setPasswordConfirmation("Password123!");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();

        String actualValue = registerAccountPage.getEmailErrorMessage();
        String expectedValue = "E-Mail Address does not appear to be valid!";
        Assert.assertEquals(actualValue, expectedValue, "The error message is not the expected one.");
    }
    @Test
    public void registerAccountWithoutTelephoneTest() {
        registerAccountPage.insertFirstName("Cristi");
        registerAccountPage.insertLastName("Hari");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.setPassword("Password123!");
        registerAccountPage.setPasswordConfirmation("Password123!");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();

        String actualValue = registerAccountPage.getTelephoneErrorMessage();
        String expectedValue = "Telephone must be between 3 and 32 characters!";
        Assert.assertEquals(actualValue, expectedValue, "The error message is not the expected one.");
    }
    @Test
    public void registerAccountWithoutPasswordTest() {
        registerAccountPage.insertFirstName("Cristi");
        registerAccountPage.insertLastName("Hari");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("01234567");
        registerAccountPage.setPasswordConfirmation("Password123!");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();

        String actualValue = registerAccountPage.getPasswordErrorMessage();
        String expectedValue = "Password must be between 4 and 20 characters!";
        Assert.assertEquals(actualValue, expectedValue, "The error message is not the expected one.");
    }
    @Test
    public void registerNewAccountWithoutCheckingPrivacyPolicyTest() {
        registerAccountPage.insertFirstName("Cristi");
        registerAccountPage.insertLastName("Hari");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("01234567");
        registerAccountPage.setPassword("Password123!");
        registerAccountPage.setPasswordConfirmation("Password123!");
        registerAccountPage.selectSubscriptionYesButton();
        registerAccountPage.clickContinue();

        String actualValue = registerAccountPage.getPrivacyPolicyWarning();
        String expectedValue = "Warning: You must agree to the Privacy Policy!";
        Assert.assertEquals(actualValue, expectedValue, "The error message is not the expected one.");
    }
    @Test
    public void registerNewAccountWithSubscriptionTest() {
        registerAccountPage.insertFirstName("Cristi");
        registerAccountPage.insertLastName("Hari");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("01234567");
        registerAccountPage.setPassword("Password123!");
        registerAccountPage.setPasswordConfirmation("Password123!");
        registerAccountPage.selectSubscriptionYesButton();
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();

        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        String actualText = accountCreatedPage.getParagraphText();
        String expectedText = "Congratulations! Your new account has been successfully created!";
        Assert.assertEquals(actualText, expectedText, "Actual text is not the expected one.");
        dashboardPage.clickLogoutButton();
    }
}
