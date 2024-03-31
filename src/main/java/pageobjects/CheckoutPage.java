package pageobjects;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class CheckoutPage extends BasePage {

    public WebDriver driver;

    By proceedToCheckoutBtn = By.cssSelector("button[data-role='proceed-to-checkout']");
    By orderTotal = By.cssSelector("[data-th='Order Total'] [data-bind]");
    By emailField = By.cssSelector("[data-role='email-with-possible-login'] #customer-email");
    By firstNameField = By.cssSelector("input[name='firstname']");
    By lastNameField = By.cssSelector("input[name='lastname']");
    By streetAddressField = By.cssSelector("input[name='street[0]']");
    By cityField = By.cssSelector("input[name='city']");
    By stateDropdown = By.cssSelector("select[name='region_id']");
    By zipField = By.cssSelector("input[name='postcode']");
    By countryDropdown = By.cssSelector("select[name='country_id']");
    By phoneField = By.cssSelector("input[name='telephone']");
    By nextBtn = By.cssSelector("button[data-role='opc-continue']");
    By placeOrderBtn = By.cssSelector(".payment-method-content [type='submit']");
    By purchaseMessage = By.cssSelector("[data-ui-id='page-title-wrapper']");
    By loadingIcon = By.cssSelector(".loader");
    By shippingPrice = By.cssSelector("[data-bind*='price_excl_tax']");


    public CheckoutPage() throws IOException {
        super();
    }

    public WebElement getProceedToCheckoutBtn() {
        this.driver = getDriver();
        return driver.findElement(proceedToCheckoutBtn);
    }

    public WebElement getOrderTotal() {
        this.driver = getDriver();
        return driver.findElement(orderTotal);
    }

    public WebElement getEmailField() {
        this.driver = getDriver();
        return driver.findElement(emailField);
    }

    public WebElement getFirstNameField() {
        this.driver = getDriver();
        return driver.findElement(firstNameField);
    }

    public WebElement getLastNameField() {
        this.driver = getDriver();
        return driver.findElement(lastNameField);
    }

    public WebElement getStreetAddressField() {
        this.driver = getDriver();
        return driver.findElement(streetAddressField);
    }

    public WebElement getCityField() {
        this.driver = getDriver();
        return driver.findElement(cityField);
    }

    public WebElement getStateDropdown() {
        this.driver = getDriver();
        return driver.findElement(stateDropdown);
    }

    public WebElement getZipField() {
        this.driver = getDriver();
        return driver.findElement(zipField);
    }

    public WebElement getCountryDropdown() {
        this.driver = getDriver();
        return driver.findElement(countryDropdown);
    }

    public WebElement getPhoneField() {
        this.driver = getDriver();
        return driver.findElement(phoneField);
    }

    public WebElement getNextBtn() {
        this.driver = getDriver();
        return driver.findElement(nextBtn);
    }

    public WebElement getPlaceOrderBtn() {
        this.driver = getDriver();
        return driver.findElement(placeOrderBtn);
    }

    public WebElement getPurchaseMessage() {
        this.driver = getDriver();
        return driver.findElement(purchaseMessage);
    }

    public WebElement getLoadingIcon() {
        this.driver = getDriver();
        return driver.findElement(loadingIcon);
    }

    public List<WebElement> getShippingPrices() {
        this.driver = getDriver();
        return driver.findElements(shippingPrice);
    }

    public void waitForPageLoad() {
        this.driver = getDriver();
        waitForElementVisible(driver.findElement(loadingIcon));
        waitForElementInvisible(driver.findElement(loadingIcon));
    }

}
