package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {

    WebDriver driver;

    private By errorMessage = By.xpath(".//div[@class = 'alert alert-danger alert-dismissible']");
    private By wishlistHeartElement = By.xpath(".//a[@aria-label = 'Wishlist']");
    private By searchInput = By.name("search");

    public String getErrorMessage() { return driver.findElement(errorMessage).getText(); }
    public void clickWishlist() { driver.findElement(wishlistHeartElement).click(); }
    public void enterTextToSearch(String searchText) { driver.findElement(searchInput).sendKeys(searchText); }
}
