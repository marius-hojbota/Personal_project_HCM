package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultsPage extends BasePage {

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    private By searchResultItems = By.xpath(".//div[@id = 'content']/p");

    private By addToWishlistButton = By.xpath(".//button[@title = 'Add to Wish List']");

    private By closePopupButton = By.xpath(".//button[@aria-label = 'Close']");

    public void clickFirstItem() {
        driver.findElements(searchResultItems).get(0).click();
    }

    public WebElement getFirstItem() { return driver.findElements(searchResultItems).get(0); }

    public WebElement getAddToWishlistButton() {
        return driver.findElements(addToWishlistButton).get(0);
    }

    public void addFirstItemToWishlist() {
        driver.findElements(addToWishlistButton).get(0).click();
    }

    public void clickToClosePopupButton() {
        driver.findElement(closePopupButton).click();
    }
}
