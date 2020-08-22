package pages;

import Utils.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductDetailPage extends BasePage {

    private final By productNameXpath = By.xpath("//div[contains(@class, 'item__specification')]//h1/a[contains(@class, 'ng-star-inserted')]");
    private final By addToFavBtnXpath = By.xpath("//div[contains(text(), 'В избранное')]");
    private final By addToCartBtnXpath = By.xpath("//span[contains(text(), 'Добавить в корзину')]/..");
    private final By notifAddToCartXpath = By.xpath("//product-added-popup");

    public String productNameFav;

    public ProductDetailPage(Browser browser) {
        super(browser);
    }

    public String getProductName(){
        return driver.findElement(productNameXpath).getText();
    }

    public ProductDetailPage addToFavorite(){
        driver.findElement(addToFavBtnXpath).click();
        this.productNameFav = getProductName();
        return this;
    }

    public ProductDetailPage addToCart(){
        driver.findElement(addToCartBtnXpath).click();
        return this;
    }

    public boolean isVisibleNotificationAddToCart(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(notifAddToCartXpath));
        return driver.findElement(notifAddToCartXpath).isDisplayed();
    }

}
