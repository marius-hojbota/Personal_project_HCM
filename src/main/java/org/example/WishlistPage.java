package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WishlistPage extends BasePage {

    public WishlistPage(WebDriver driver) { this.driver = driver; }

    private By noResultsElement = By.xpath(".//div[@id = 'content']/p");
    private By searchButton = By.xpath("//button[@class = 'type-text']");
    private By itemsTableRow = By.xpath(".//table[@class = 'table table-hover border']/tbody/tr");
    private By removeItemFromWishlist = By.xpath(".//a[contains(@href, 'remove')]");

    public String getNoResultsMessage() { return driver.findElement(noResultsElement).getText(); }
    public void clickSearchButton() { driver.findElement(searchButton).click(); }
    public List<WebElement> getWishlistItems() { return driver.findElements(itemsTableRow); }
    public void clickRemoveItemFromWishlistButton() { driver.findElement(removeItemFromWishlist).click(); }
}
