package pages;

import Utils.Browser;
import org.openqa.selenium.By;

public class FavoritesPage extends BasePage {

    private final By brandNameXpath = By.xpath("//div[contains(@class, 'product-info__brand')]");

    public FavoritesPage(Browser browser) {
        super(browser);
    }

    public String getBrandName(){
        return driver.findElement(brandNameXpath).getText();
    }
}
