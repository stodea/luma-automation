package pageobjects;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class CatalogPage extends BasePage {

    public WebDriver driver;

    By productItems = By.cssSelector(".product-item");
    By topsWomenLink = By.cssSelector("dd [href$='tops-women.html']");


    public CatalogPage() throws IOException {
        super();
    }

    public WebElement getTopsWomenLink() {
        this.driver = getDriver();
        return driver.findElement(topsWomenLink);
    }

    public List<WebElement> getProductItems() {
        this.driver = getDriver();
        return driver.findElements(productItems);
    }

}
