package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {

    WebDriver driver;

    private By errorMessage = By.xpath(".//div[@class = 'alert alert-danger alert-dismissible']");
    private By wishlistHeartElement = By.xpath(".//a[@aria-label = 'Wishlist']");
    private By searchInput = By.name("search");
    private By myAccountElement = By.xpath("//*[@id=\"widget-navbar-217834\"]/ul/li[6]/a/div/span");

    public String getErrorMessage() { return driver.findElement(errorMessage).getText(); }
    public void clickWishlist() { driver.findElement(wishlistHeartElement).click(); }
    public void enterTextToSearch(String searchText) { driver.findElement(searchInput).sendKeys(searchText); }
    public void clickMyAccountElement() { driver.findElement(myAccountElement).click(); }
}
