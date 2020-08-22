package pages;

import Utils.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ContactsPage extends BasePage {

    private final By mapXpath = By.xpath("//div[contains(@class, 'map__container')]");
    private final By mapMarkerXpath = By.xpath("//div[contains(@title, 'ЦУМ')]/img");
    private final By writeToWPXpath = By.xpath("//a[contains(@class, 'map__item-social')]");

    public ContactsPage(Browser browser) {
        super(browser);
    }

    public boolean mapIsVisible(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(mapXpath));
        return driver.findElement(mapXpath).isDisplayed();
    }

    public boolean mapMarkerIsVisible(){
        return driver.findElement(mapMarkerXpath).isEnabled();                          //isDisplayed здесь не работает
    }

    public WhatsAppContactPage writeToWP(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(writeToWPXpath));
        driver.findElement(writeToWPXpath).click();
        return new WhatsAppContactPage(browser);
    }
}
