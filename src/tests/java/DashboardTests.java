import org.example.DashboardPage;
import org.example.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static util.TestUtil.generateRandomTelephoneNumber;

public class DashboardTests extends BaseTest{

    private DashboardPage dashboardPage;
    private LoginPage loginPage;
    private String loginPageURL = "https://ecommerce-playground.lambdatest.io/index.php?route=account/login";

    @BeforeClass
    public void setPreconditions() {
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        System.out.println("Navigating to " + loginPageURL);
        driver.get(loginPageURL);
    }

    @Test
    public void verifyDashboardFirstSection() {
        String expectedFirstSectionHeaderText = "My Account";
        String expectedEditAccountElementText = "Edit your account information";

        Assert.assertEquals(dashboardPage.getFirstSectionHeaderText(), expectedFirstSectionHeaderText,
                "First section header text is not the expected one");
        Assert.assertEquals(dashboardPage.getEditAccountElementText(), expectedEditAccountElementText,
                "Edit account element text is not the expected one");
    }
    @Test
    public void editAccountTelephoneNumber() throws Exception {
        loginWithExistingAccount();
        dashboardPage.clickEditAccountElement();
        Thread.sleep(1000);
        dashboardPage.clearTelephoneNumber();
        dashboardPage.insertNewNumber(generateRandomTelephoneNumber());
        dashboardPage.clickContinueButton();
        String actualValue = dashboardPage.getAccountSuccessfullyUpdatedMessage();
        String expectedValue = "Success: Your account has been successfully updated.";
        Assert.assertEquals(actualValue, expectedValue, "The message is not the expected one.");
    }

    public void loginWithExistingAccount() {
        loginPage.insertEmail("me@too.com");
        loginPage.insertPassword("me2p@ssword");
        loginPage.clickLogin();
    }
}
