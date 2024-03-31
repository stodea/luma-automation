package pageobjects;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class ProductPage extends BasePage {

    public WebDriver driver;

    public String optionCssLocator = "[option-label='%s']";

    By addToCartBtn = By.cssSelector("button#product-addtocart-button");
    By sizeBtn = By.cssSelector("[attribute-code='size'] [index='0']");
    By colorBtn = By.cssSelector("[attribute-code='color'] [index='0']");
    By quantityInput = By.id("qty");
    By allertMessage = By.cssSelector("div[role='alert']");
    By successMessage = By.cssSelector("[data-ui-id='message-success']");


    public ProductPage() throws IOException {
        super();
    }

    public WebElement getAddToCartBtn() {
        this.driver = getDriver();
        return driver.findElement(addToCartBtn);
    }

    public WebElement getSizeBtn() {
        this.driver = getDriver();
        return driver.findElement(sizeBtn);
    }

    public WebElement getColorBtn() {
        this.driver = getDriver();
        return driver.findElement(colorBtn);
    }

    public WebElement getQuantityInput() {
        this.driver = getDriver();
        return driver.findElement(quantityInput);
    }

    public WebElement getAllertMessage() {
        this.driver = getDriver();
        return driver.findElement(allertMessage);
    }

    public WebElement getSuccessMessage() {
        this.driver = getDriver();
        return driver.findElement(successMessage);
    }

    public WebElement getOption(String optionToSelect) {
        this.driver = getDriver();
        return driver.findElement(By.cssSelector(String.format(optionCssLocator, optionToSelect)));
    }

}
