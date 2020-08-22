package pages;

import Utils.Browser;
import org.openqa.selenium.By;

public class BonusCardPage extends BasePage {

    private final By selectSumBoxXpath = By.xpath("//div[contains(@class, 'select__current')]");
    private final By addToCartBtnXpath = By.xpath("//button[contains(@class, 'gift-buy__button')]");

    private String selectSumParamXpath = "//span[@class='select__text' and contains(text(), '%s ₽')]";

    public BonusCardPage(Browser browser) {
        super(browser);
    }

    public BonusCardPage selectSum(int sum){
        driver.findElement(selectSumBoxXpath).click();
        driver.findElement(By.xpath(selectSumParamXpath.replace("%s", "" + sum))).click();
        return this;
    }

    public CheckoutPage goToCheckout() {
        driver.findElement(addToCartBtnXpath).click();
        return new CheckoutPage(browser);
    }

}
