package pages;

import Utils.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class MainPage extends BasePage {

    private String genderTabParamXpath = "//div[contains(@class, 'header__gender-switch_desktop')]/span[contains(@class, 'header__gender') and contains(text(), '%s')]";
    private String tabMenuParamXpath = "//li[contains(@class, 'header__item-level-1')]//a[contains(text(), '%s')]";
    private String categoryParamXpath = "//li[contains(@class, 'header__subnav-item')]//a[contains(text(), '%s')]";
    private String languageMenuParamXpath =  "//a[contains(@class, 'language-selector__link') and text()='%s']";

    private final By languageMenuNotRUXpath =  By.xpath("//span[contains(@class, 'dropdown-link with-bg')]");
    private final By languageMenuXpath =  By.xpath("//span[contains(@class, 'language-selector__link')]");
    private final By activeGenderTabXpath = By.xpath("//div[contains(@class, 'header__gender-switch_desktop')]/span[contains(@class, 'header__gender_active')]");
    private final By BonusCardPageXpath = By.xpath("//a[contains(@title, 'Подарочные карты')]");
    private final By favoritesBtnXpath = By.xpath("//a[contains(@title, 'Избранное')]");
    private final By geoPopupCloseBtnXpath = By.xpath("//button[contains(@class, 'geo-popup__close-button')]");
    private final By searchBtnXpath = By.xpath("//div[contains(@class, 'field_view_search')]");
    private final By searchTextFieldXpath = By.xpath("//input[contains(@placeholder, 'Поиск')]");

    public MainPage(Browser browser) {
        super(browser);
        wait.until(ExpectedConditions.visibilityOfElementLocated(geoPopupCloseBtnXpath));
        WebElement geoPopupCloseBtn = driver.findElement(geoPopupCloseBtnXpath);
        if(geoPopupCloseBtn.isDisplayed())
            geoPopupCloseBtn.click();
    }

    public MainPage switchGender(String gender){
        /*#1 Мужское
        #2 Детское
        #0 по умолчанию Женское*/
        By genderTabXpath = By.xpath(genderTabParamXpath.replace("%s", gender));
        wait.until(ExpectedConditions.visibilityOfElementLocated(genderTabXpath));
        driver.findElement(genderTabXpath).click();
        return this;
    }

    public void verifyActiveGenderTab(String gender){
        String activeTabName = driver.findElement(activeGenderTabXpath).getText();
        Assert.assertTrue(activeTabName.contains(gender), "Активная вкладка ожидалась " + gender + " , но обнаружена " + activeTabName);
    }

    public MainPage switchLanguage(String language){
        wait.until(ExpectedConditions.visibilityOfElementLocated(languageMenuXpath));
        driver.findElement(languageMenuXpath).click();
        driver.findElement(By.xpath(languageMenuParamXpath.replace("%s", language))).click();
        return this;
    }

    public void verifyCurrentLanguage(String language){
        String actualLang;
        if (!language.contains("Русский"))
            actualLang = driver.findElement(languageMenuNotRUXpath).getText();
        else
            actualLang = driver.findElement(languageMenuXpath).getText();

        Assert.assertTrue(actualLang.equals(language), "Ожидалось " + language + ", но найдено " + actualLang);
    }

    public BonusCardPage goToBonusCardPage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(BonusCardPageXpath));
        driver.findElement(BonusCardPageXpath).click();
        return new BonusCardPage(browser);
    }

    public CatalogPage goToCategory(String tab, String category){
        By tabMenuXpath = By.xpath(tabMenuParamXpath.replace("%s", tab));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tabMenuXpath));

        Actions action = new Actions(driver);
        action
                .moveToElement(driver
                        .findElement(tabMenuXpath))
                .perform();

        By categoryXpath = By.xpath(categoryParamXpath.replace("%s", category));
        wait.until(ExpectedConditions.elementToBeClickable(categoryXpath));
        driver.findElement(categoryXpath).click();
        return new CatalogPage(browser);
    }

    public FavoritesPage goToFavoritePage(){
        driver.findElement(favoritesBtnXpath).click();
        return new FavoritesPage(browser);
    }

    public CatalogPage searchRequest(String request){
        WebElement searchBtn = driver.findElement(searchBtnXpath);
        searchBtn.click();
        searchBtn.findElement(searchTextFieldXpath).sendKeys(request + Keys.RETURN);
        return new CatalogPage(browser);
    }
}
