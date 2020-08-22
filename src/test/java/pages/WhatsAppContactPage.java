package pages;

import Utils.Browser;
import org.openqa.selenium.By;

public class WhatsAppContactPage extends BasePage {

    private final By startChatMsgXpath = By.xpath("//h1[contains(text(), 'Начните чат в WhatsApp')]");

    public WhatsAppContactPage(Browser browser) {
        super(browser);
    }

    public boolean isVisibleStartChatWith(String companyName){
        return driver.findElement(startChatMsgXpath).getText().contains(companyName);
    }
}
