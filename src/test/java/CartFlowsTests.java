import org.example.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static util.TestUtil.generateRandomEmail;

public class CartFlowsTests extends BaseTest {
    private RegisterAccountPage registerAccountPage;
    private WishlistPage wishlistPage;
    private SearchResultsPage searchResultsPage;
    private DashboardPage dashboardPage;
    private CartPage cartPage;
    private LoginPage loginPage;
    private Actions action;
    private String registerUrl = "https://ecommerce-playground.lambdatest.io/index.php?route=account/register";
    private String mainPageUrl = "https://ecommerce-playground.lambdatest.io/index.php?route=common/home";

    @BeforeClass
    public void setUpPreconditions() {
        driver.manage().window().maximize();
        registerAccountPage = new RegisterAccountPage(driver);
        wishlistPage = new WishlistPage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        dashboardPage = new DashboardPage(driver);
        cartPage = new CartPage(driver);
        loginPage = new LoginPage(driver);
        action = new Actions(driver);
        registerAccountPage = new RegisterAccountPage(driver);
        driver.get(mainPageUrl);
    }

    @Test
    public void addItemToCartAuthenticatedUser() throws Exception {
        System.out.println("Creating new account to be logged in...");
        createAccount();
        System.out.println("Creating new account to be logged in... Done");
        String expectedResult = "Your shopping cart is empty!";
        dashboardPage.clickCartButton();
        searchResultsPage.clickEditCartButton();
        Thread.sleep(2000);
        String actualResult = cartPage.getEmptyCartMessage();
        Assert.assertEquals(actualResult, expectedResult, "Text from element is not the expected one.");
        wishlistPage.enterTextToSearch("iMac");
        wishlistPage.clickSearchButton();
        Thread.sleep(1000);
        WebElement item = searchResultsPage.getFirstItem2();
        action.moveToElement(item).build().perform();
        Thread.sleep(1000);
        WebElement button = searchResultsPage.getAddtoCartButton();
        action.moveToElement(button).click().build().perform();
        Thread.sleep(1000);
        searchResultsPage.clickToClosePopupButton();
        dashboardPage.clickCartButton();
        searchResultsPage.clickEditCartButton();
        int noOfItems = cartPage.getCartItems().size();
        Assert.assertTrue(noOfItems == 1, "Cart is empty");
        cartPage.clickMyAccountElement();
        dashboardPage.clickLogoutButton();
    }
    @Test
    public void addItemToCartGuestUser() throws Exception {
        String expectedResult = "Your shopping cart is empty!";
        dashboardPage.clickCartButton();
        searchResultsPage.clickEditCartButton();
        Thread.sleep(1000);
        String actualResult = cartPage.getEmptyCartMessage();
        Assert.assertEquals(actualResult, expectedResult, "Text from element is not the expected one.");
        wishlistPage.enterTextToSearch("iMac");
        wishlistPage.clickSearchButton();
        Thread.sleep(1000);
        WebElement item = searchResultsPage.getFirstItem2();
        action.moveToElement(item).build().perform();
        Thread.sleep(1000);
        WebElement button = searchResultsPage.getAddtoCartButton();
        action.moveToElement(button).click().build().perform();
        Thread.sleep(1000);
        searchResultsPage.clickToClosePopupButton();
        dashboardPage.clickCartButton();
        searchResultsPage.clickEditCartButton();
        int noOfItems = cartPage.getCartItems().size();
        Assert.assertTrue(noOfItems == 1, "Cart is empty");
        cartPage.clickToRemoveItemFromCartButton();
        Thread.sleep(2000);
    }
    @Test
    public void removeItemFromCartAuthenticatedUser() throws Exception {
        System.out.println("Creating new account to be logged in...");
        createAccount();
        System.out.println("Creating new account to be logged in... Done");
        String expectedResult = "Your shopping cart is empty!";
        dashboardPage.clickCartButton();
        Thread.sleep(1000);
        searchResultsPage.clickEditCartButton();
        Thread.sleep(2000);
        String actualResult = cartPage.getEmptyCartMessage();
        Assert.assertEquals(actualResult, expectedResult, "Text from element is not the expected one.");
        wishlistPage.enterTextToSearch("iMac");
        wishlistPage.clickSearchButton();
        Thread.sleep(1000);
        WebElement item = searchResultsPage.getFirstItem2();
        action.moveToElement(item).build().perform();
        Thread.sleep(1000);
        WebElement button = searchResultsPage.getAddtoCartButton();
        action.moveToElement(button).click().build().perform();
        Thread.sleep(1000);
        searchResultsPage.clickToClosePopupButton();
        dashboardPage.clickCartButton();
        searchResultsPage.clickEditCartButton();
        Thread.sleep(1000);
        cartPage.clickToRemoveItemFromCartButton();
        Thread.sleep(2000);
        actualResult = cartPage.getEmptyCartMessage();
        Assert.assertEquals(actualResult, expectedResult, "Text from element is not the expected one.");
        cartPage.clickMyAccountElement();
        dashboardPage.clickLogoutButton();
    }
    @Test
    public void removeItemFromCartGuestUser() throws Exception {
        String expectedResult = "Your shopping cart is empty!";
        dashboardPage.clickCartButton();
        searchResultsPage.clickEditCartButton();
        Thread.sleep(1000);
        String actualResult = cartPage.getEmptyCartMessage();
        Assert.assertEquals(actualResult, expectedResult, "Text from element is not the expected one.");
        wishlistPage.enterTextToSearch("iMac");
        wishlistPage.clickSearchButton();
        Thread.sleep(1000);
        WebElement item = searchResultsPage.getFirstItem2();
        action.moveToElement(item).build().perform();
        Thread.sleep(1000);
        WebElement button = searchResultsPage.getAddtoCartButton();
        action.moveToElement(button).click().build().perform();
        Thread.sleep(1000);
        searchResultsPage.clickToClosePopupButton();
        dashboardPage.clickCartButton();
        searchResultsPage.clickEditCartButton();
        Thread.sleep(1000);
        cartPage.clickToRemoveItemFromCartButton();
        Thread.sleep(2000);
        actualResult = cartPage.getEmptyCartMessage();
        Assert.assertEquals(actualResult, expectedResult, "Text from element is not the expected one.");
    }

    public void createAccount() throws InterruptedException {
        cartPage.clickMyAccountElement();
        Thread.sleep(1000);
        loginPage.clickRegisterAccountElement();
        registerAccountPage.insertFirstName("John");
        registerAccountPage.insertLastName("Doe");
        registerAccountPage.insertEmail(generateRandomEmail());
        registerAccountPage.insertPhoneNumber("01233456");
        registerAccountPage.setPassword("Password123!");
        registerAccountPage.setPasswordConfirmation("Password123!");
        registerAccountPage.checkPrivacyPolicy();
        registerAccountPage.clickContinue();
    }
}
