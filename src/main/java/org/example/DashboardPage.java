package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) { this.driver = driver; }

    private By firstSectionHeader = By.xpath("//*[@id=\"content\"]/div[1]/h2");
    private By editAccountElement = By.xpath(".//div[@id='content']//a[contains(@href, 'account/edit')]");
    private By changePasswordElement = By.xpath(".//div[@id='content']//a[contains(@href, 'account/password')]");
    private By modifyAddressElement = By.xpath(".//div[@id='content']//a[contains(@href, 'account/address')]");
    private By myWishlistELement = By.xpath(".//div[@id='content']//a[contains(@href, 'account/wishlist')]");
    private By newsletterElement = By.xpath(".//div[@id='content']//a[contains(@href, 'account/newsletter')]");
    private By logoutButton = By.xpath("//*[@id=\"column-right\"]/div/a[14]");
    private By cartElement = By.xpath("//*[@id=\"entry_217825\"]/a/div[1]");

    public String getFirstSectionHeaderText() { return driver.findElement(firstSectionHeader).getText(); }
    public String getEditAccountElementText() { return driver.findElement(editAccountElement).getText(); }
    public void clickLogoutButton() { driver.findElement(logoutButton).click(); }
    public void clickCartButton() { driver.findElement(cartElement).click(); }
}