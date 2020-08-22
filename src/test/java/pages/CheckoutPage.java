package pages;

import Utils.Browser;
import org.openqa.selenium.By;

public class CheckoutPage extends BasePage {

    private final By amountXpath = By.xpath("//div[contains(@class, 'amount-header')]/span[2]");

    public CheckoutPage(Browser browser) {
        super(browser);
    }

    public int getAmount(){
        return Integer.parseInt(driver.findElement(amountXpath).getText().replaceAll("\\D", ""));
    }
}
