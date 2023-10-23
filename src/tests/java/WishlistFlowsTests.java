import org.example.DashboardPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.example.RegisterAccountPage;
import org.example.SearchResultsPage;
import org.example.WishlistPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static util.TestUtil.generateRandomEmail;
import static util.TestUtil.generateRandomTelephoneNumber;

public class WishlistFlowsTests extends BaseTest {

    private RegisterAccountPage registerAccountPage;
    private WishlistPage wishlistPage;
    private SearchResultsPage searchResultsPage;
    private Actions action;
    private DashboardPage dashboardPage;
    private String registerUrl = "https://ecommerce-playground.lambdatest.io/index.php?route=account/register";

    @BeforeClass
    public void setUpPreconditions() {
        driver.manage().window().maximize();
        registerAccountPage = new RegisterAccountPage(driver);
        wishlistPage = new WishlistPage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        action = new Actions(driver);
        dashboardPage = new DashboardPage(driver);
        System.out.println("Creating new account to be logged in...");
        registerAccountPage = new RegisterAccountPage(driver);
    }

    @Test
    public void addItemToWishlist() throws Exception {
        createAccount();
        System.out.println("Creating new account to be logged in... Done");
        String expectedResult = "No results!";
        wishlistPage.clickWishlist();
        String actualResult = wishlistPage.getNoResultsMessage();
        Assert.assertEquals(actualResult, expectedResult, "Text from element is not the expected one.");
        wishlistPage.enterTextToSearch("Apple Cinema 30\"");
        wishlistPage.clickSearchButton();
        Thread.sleep(3000);
        WebElement item = searchResultsPage.getFirstItem();
        action.moveToElement(item).build().perform();
        Thread.sleep(1000);
        WebElement button = searchResultsPage.getAddToWishlistButton();
        action.moveToElement(button).click().build().perform();
        Thread.sleep(1000);
        searchResultsPage.clickToClosePopupButton();
        searchResultsPage.clickWishlist();
        int noOfItems = wishlistPage.getWishlistItems().size();
        Assert.assertTrue(noOfItems == 1, "Wishlist is empty");
        dashboardPage.clickLogoutButton();
    }
    @Test
    public void removeItemFromWishlist() throws Exception {
        createAccount();
        System.out.println("Creating new account to be logged in... Done");
        String expectedResult = "No results!";
        Thread.sleep(1000);
        wishlistPage.clickWishlist();
        Thread.sleep(1000);
        String actualResult = wishlistPage.getNoResultsMessage();
        Assert.assertEquals(actualResult, expectedResult, "Text from element is not the expected one.");
        wishlistPage.enterTextToSearch("Apple Cinema 30\"");
        wishlistPage.clickSearchButton();
        Thread.sleep(3000);
        WebElement item = searchResultsPage.getFirstItem();
        action.moveToElement(item).build().perform();
        Thread.sleep(1000);
        WebElement button = searchResultsPage.getAddToWishlistButton();
        action.moveToElement(button).click().build().perform();
        Thread.sleep(1000);
        searchResultsPage.clickToClosePopupButton();
        searchResultsPage.clickWishlist();
        Thread.sleep(1000);
        int noOfItems = wishlistPage.getWishlistItems().size();
        Assert.assertTrue(noOfItems == 1, "Wishlist is empty");
        wishlistPage.clickRemoveItemFromWishlistButton();
        actualResult = wishlistPage.getNoResultsMessage();
        Assert.assertEquals(actualResult, expectedResult, "Text from element is not the expected one.");
        dashboardPage.clickLogoutButton();
    }
    @Test
    public void addToWishlistAsGuest() throws Exception {
        driver.get(registerUrl);
        wishlistPage.enterTextToSearch("Apple Cinema 30\"");
        wishlistPage.clickSearchButton();
        Thread.sleep(1000);
        WebElement item = searchResultsPage.getFirstItem();
        action.moveToElement(item).build().perform();
        Thread.sleep(1000);
        WebElement button = searchResultsPage.getAddToWishlistButton();
        action.moveToElement(button).click().build().perform();
        Thread.sleep(1000);
        String expectedResult = "Login";
        String actualResult = searchResultsPage.getMustLoginToAddToWishlistMessage();
        Assert.assertEquals(actualResult, expectedResult, "Text from element is not the expected one.");
    }

    public void createAccount() {
        driver.get(registerUrl);
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
