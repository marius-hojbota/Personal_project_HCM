package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) { this.driver = driver; }

    private By emptyCartElement = By.xpath("//*[@id=\"content\"]/p");
    private By searchButton = By.xpath(".//button[@class = 'type-text']");
    private By cartTableRow = By.xpath("//*[@id=\"content\"]/form/div/table/tbody/tr");
    private By removeItemFromCart = By.xpath("//div[@class = 'input-group-append']//button[2]");

    public String getEmptyCartMessage() { return driver.findElement(emptyCartElement).getText(); }
    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }
    public List<WebElement> getCartItems() { return driver.findElements(cartTableRow); }
    public void clickToRemoveItemFromCartButton() { driver.findElements(removeItemFromCart).get(0).click(); }
}
