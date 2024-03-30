package pageobjects;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class HomePage extends BasePage {

    public WebDriver driver;

    By womenMenuLink = By.linkText("Women");
    By topsMenuLink = By.linkText("Tops");
    By jacketsMenuLink = By.linkText("Jackets");
    By cartBtn = By.cssSelector(".action.showcart");
    By cartItemsNumber = By.cssSelector(".counter-number");
    By miniCart = By.cssSelector("div#minicart-content-wrapper");
    By miniCartViewCartLink = By.cssSelector("div#minicart-content-wrapper .action.viewcart");
    By greetBanner = By.cssSelector(".header.panel > .header.links > .greet.welcome > .not-logged-in");


    public HomePage() throws IOException {
        super();
    }

    public WebElement getWomenMenuLink() {
        this.driver = getDriver();
        return driver.findElement(womenMenuLink);
    }

    public WebElement getTopsMenuLink() {
        this.driver = getDriver();
        return driver.findElement(topsMenuLink);
    }

    public WebElement getJacketsMenuLink() {
        this.driver = getDriver();
        return driver.findElement(jacketsMenuLink);
    }

    public WebElement getCartItemsNumber() {
        this.driver = getDriver();
        return driver.findElement(cartItemsNumber);
    }

    public WebElement getCartBtn() {
        this.driver = getDriver();
        return driver.findElement(cartBtn);
    }

    public WebElement getMiniCartViewCartLink() {
        this.driver = getDriver();
        return driver.findElement(miniCartViewCartLink);
    }

    public WebElement getMiniCart() {
        this.driver = getDriver();
        return driver.findElement(miniCart);
    }

    public void waitForPageLoad() {
        this.driver = getDriver();
        waitForElementVisible(driver.findElement(greetBanner));
    }

}
