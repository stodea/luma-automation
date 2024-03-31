package pageobjects;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class ModalPage extends BasePage {

    public WebDriver driver;

    By okBtn = By.cssSelector(".action-accept.action-primary");

    public ModalPage() throws IOException {
        super();
    }

    public WebElement getOkBtn() {
        this.driver = getDriver();
        return driver.findElement(okBtn);
    }
}
