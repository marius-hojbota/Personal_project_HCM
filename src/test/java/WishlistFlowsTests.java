import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.example.RegisterAccountPage;
import org.example.SearchResultsPage;
import org.example.WishlistPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static util.TestUtil.generateRandomEmail;

public class WishlistFlowsTests extends BaseTest {

    private RegisterAccountPage registerAccountPage;
    private WishlistPage wishlistPage;
    private SearchResultsPage searchResultsPage;
    private Actions action;
    private String registerUrl = "https://ecommerce-playground.lambdatest.io/index.php?route=account/register";

    @BeforeClass
    public void setUpPreconditions() {
        registerAccountPage = new RegisterAccountPage(driver);
        wishlistPage = new WishlistPage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        action = new Actions(driver);
        System.out.println("Creating new account to be logged in...");
        registerAccountPage = new RegisterAccountPage(driver);
        createAccount();
        System.out.println("Creating new account to be logged in... Done");
    }

    @Test
    public void addItemToWishlist() throws Exception {
        driver.manage().window().fullscreen();
        String expectedResult = "No results!";
        wishlistPage.clickWishlist();
        String actualResult = wishlistPage.getNoResultsMessage();
        Assert.assertEquals(actualResult, expectedResult, "Text from element is not the expected one.");
        wishlistPage.enterTextToSearch("Apple Cinema 30\"");
        wishlistPage.clickSearchButton();
        Thread.sleep(1000);
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
//        wishlistPage.clickRemoveItemFromWishlistButton();
//        actualResult = wishlistPage.getNoResultsMessage();
//        Assert.assertEquals(actualResult, expectedResult, "Text from element is not the expected one.");
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