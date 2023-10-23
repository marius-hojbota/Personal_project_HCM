package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultsPage extends BasePage {

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    private By searchResultItems = By.xpath(".//div[@class = 'carousel-item active']/img[contains(@title, 'Apple Cinema')]");
    private By addToWishlistButton = By.xpath(".//button[@title = 'Add to Wish List']");
    private By closePopupButton = By.xpath(".//button[@aria-label = 'Close']");
    private By addToCartButton = By.xpath(".//button[@title = 'Add to Cart']");
    private By searchResultItems2 = By.xpath(".//div[@class = 'carousel-item active']/img[contains(@title, 'iMac')]");
    private By editCartButton = By.xpath("//*[@id=\"entry_217850\"]/a");
    private By mustLoginToAddToWishlist = By.xpath("//*[@id=\"notification-box-top\"]/div/div[1]/span");

    public void clickFirstItem() {
        driver.findElements(searchResultItems).get(0).click();
    }
    public WebElement getFirstItem() { return driver.findElements(searchResultItems).get(0); }
    public WebElement getAddToWishlistButton() { return driver.findElements(addToWishlistButton).get(0); }
    public void addFirstItemToWishlist() {
        driver.findElements(addToWishlistButton).get(0).click();
    }
    public void clickToClosePopupButton() { driver.findElement(closePopupButton).click(); }
    public WebElement getAddtoCartButton() { return driver.findElements(addToCartButton).get(0); }
    public WebElement getFirstItem2() { return driver.findElements(searchResultItems2).get(0); }
    public void clickEditCartButton() { driver.findElement(editCartButton).click(); }
    public String getMustLoginToAddToWishlistMessage() { return driver.findElement(mustLoginToAddToWishlist).getText(); }
}
