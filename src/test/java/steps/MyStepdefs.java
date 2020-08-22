package steps;

import Utils.Browser;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.*;

public class MyStepdefs{

    private MainPage mainPage;
    private BonusCardPage bonusCardPage;
    private CheckoutPage checkoutPage;
    private CatalogPage catalogPage;
    private ProductDetailPage productDetailPage;
    private FavoritesPage favoritesPage;
    private ContactsPage contactsPage;
    private WhatsAppContactPage whatsAppContactPage;

    private final String URL_CATALOG = "https://www.tsum.ru/catalog/";
    private final String URL_CONTACTS = "https://www.tsum.ru/contacts/";

    private final String targetUrl = "https://tsum.ru";

    private Browser browser;

    @Before
    public void stepSetup(){
        this.browser = new Browser();
        this.browser.setUp();
    }

    @After
    public void quit(){
        browser.quit();
    }

    @Given("main page")
    public void mainPage() {
        browser.getUrl(targetUrl);
        mainPage = new MainPage(browser);
    }

    @When("switch gender on {string}")
    public void switchGenderOnMale(String genderTab) {
        mainPage.switchGender(genderTab);
    }

    @Then("current gender is {string}")
    public void currentGenderIs(String activeTab) {
        mainPage.verifyActiveGenderTab(activeTab);
    }

    @When("switch language on {string}")
    public void switchLanguageOn(String language) {
        mainPage.switchLanguage(language);
    }

    @Then("current language is {string}")
    public void currentLanguageIs(String language) {
        mainPage.verifyCurrentLanguage(language);
    }

    @And("I click bonus cards")
    public void iClickBonusCards() {
        bonusCardPage = mainPage.goToBonusCardPage();
    }

    @And("I choose {int}")
    public void iChoosePrice(int sum) {
        bonusCardPage.selectSum(sum);
    }

    @When("I click put to cart")
    public void iClickPutToCart() {
        checkoutPage = bonusCardPage.goToCheckout();
    }

    @Then("I will see {int} on cart")
    public void iWillSeePriceOnCart(int expectedSum) {
        int actualSum = checkoutPage.getAmount();
        Assert.assertEquals(actualSum, expectedSum);
    }

    @And("I expand tab {string} and choose category {string}")
    public void iExpandTabAndChooseCategory(String tab, String category){
        catalogPage = mainPage.goToCategory(tab, category);
    }

    @And("I open detail product № {int}")
    public void iOpenDetailProduct(int number){
        productDetailPage = catalogPage.goToNumberProductDetail(number);
    }

    @When("adding to favorites")
    public void addingToFavorites() {
        productDetailPage.addToFavorite();
    }

    @And("I go to favorites")
    public void iGoToFavorites(){
        favoritesPage = mainPage.goToFavoritePage();
    }

    @Then("I will see product in favorites")
    public void iWillSeeProductInFavorites() {
        String actualBrandName = favoritesPage.getBrandName();
        String expectedBrandName = productDetailPage.productNameFav;
        Assert.assertEquals(actualBrandName, expectedBrandName);
    }

    @Given("catalog page")
    public void catalogPage() {
        browser.getUrl(URL_CATALOG);
        catalogPage = new CatalogPage(browser);
    }

    @When("adding to cart")
    public void addingToCart() {
        productDetailPage.addToCart();
    }

    @Then("I will see notification")
    public void iWillSeeNotification() {
        Assert.assertTrue(productDetailPage.isVisibleNotificationAddToCart());
    }

    @When("I search {string}")
    public void iSearch(String searchRequest) {
        catalogPage = mainPage.searchRequest(searchRequest);
    }

    @Then("I will see products {string}")
    public void iWillSeeProducts(String nameProducts) {
        catalogPage.verifySearchResultByName(nameProducts);
    }

    @Given("contacts page")
    public void contactsPage() {
        browser.getUrl(URL_CONTACTS);
        contactsPage = new ContactsPage(browser);
    }

    @When("I will see map")
    public void iWillSeeMap() {
        Assert.assertTrue(contactsPage.mapIsVisible());
    }

    @Then("I will see marker")
    public void iWillSeeMarker() {
        Assert.assertTrue(contactsPage.mapMarkerIsVisible());
    }

    @When("I click write to TSUM")
    public void iClickWriteToTSUM() {
        whatsAppContactPage = contactsPage.writeToWP();
    }

    @Then("I will see message send to {string}")
    public void iWillSeeMessageSendTo(String companyName) {
        Assert.assertTrue(whatsAppContactPage.isVisibleStartChatWith(companyName));
    }
}
