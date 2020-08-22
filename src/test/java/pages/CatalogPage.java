package pages;

import Utils.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import java.util.List;

public class CatalogPage extends BasePage{

    private final By firstProductXpath = By.xpath("//div[contains(@class, 'product-list__item')]");
    private final By productNameXpath = By.xpath("//p[contains(@class, 'product__description')]");

    public CatalogPage(Browser browser) {
        super(browser);
    }

    public ProductDetailPage goToNumberProductDetail(int number){
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductXpath));
        driver.findElements(firstProductXpath).get(number - 1).click();
        return new ProductDetailPage(browser);
    }

    public CatalogPage verifySearchResultByName(String name) {
        List<WebElement> productsName;
        productsName = driver.findElements(productNameXpath);
        for (WebElement e : productsName) {
            Assert.assertTrue(e.getText().contains(name));
        }
        return this;
    }

}
