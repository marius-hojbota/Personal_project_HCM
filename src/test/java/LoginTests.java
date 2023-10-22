import org.example.DashboardPage;
import org.example.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static util.TestUtil.generateRandomEmail;
public class LoginTests extends BaseTest{

    private LoginPage loginPage;

    private DashboardPage dashboardPage;
    private String loginPageURL = "https://ecommerce-playground.lambdatest.io/index.php?route=account/login";

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Navigating to " + loginPageURL);
        driver.get(loginPageURL);
        driver.manage().window().fullscreen();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    @Test
    public void invalidCredentialsTest() {
        loginPage.insertEmail(generateRandomEmail());
        loginPage.insertPassword("randomPassword");
        loginPage.clickLogin();
        String actualValue = loginPage.getErrorMessage();
        String expectedValue = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertEquals(actualValue, expectedValue, "The error message is not the expected one.");
    }
    @Test
    public void validEmailInvalidPasswordTest() {
        loginPage.insertEmail("me@too.com");
        loginPage.insertPassword("randomPassword");
        loginPage.clickLogin();
        String actualValue = loginPage.getNoMatchWarningMessage();
        String expectedValue = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertEquals(actualValue, expectedValue, "The error message is not the expected one.");
    }
    @Test
    public void invalidEmailValidPasswordTest() {
        loginPage.insertEmail(generateRandomEmail());
        loginPage.insertPassword("me2p@ssword");
        loginPage.clickLogin();
        String actualValue = loginPage.getNoMatchWarningMessage();
        String expectedValue = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertEquals(actualValue, expectedValue, "The error message is not the expected one.");
    }
    @Test
    public void validCredentialsTest() {
        loginPage.insertEmail("me@too.com");
        loginPage.insertPassword("me2p@ssword");
        loginPage.clickLogin();
        String expectedFirstSectionHeaderText = "My Account";
        String expectedEditAccountElementText = "Edit your account information";
        Assert.assertEquals(dashboardPage.getFirstSectionHeaderText(), expectedFirstSectionHeaderText,
                "First section header text is not the expected one");
        Assert.assertEquals(dashboardPage.getEditAccountElementText(), expectedEditAccountElementText,
                "Edit account element text is not the expected one");
        dashboardPage.clickLogoutButton();
    }
}
