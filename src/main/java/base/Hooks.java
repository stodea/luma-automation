package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

public class Hooks extends BasePage {

    public Hooks() throws IOException {
        super();
    }

    @BeforeMethod
    public void setup() {
        getDriver().get(getUrl());
    }

    @AfterMethod
    public void tearDown() {
        WebDriverInstance.cleanupDriver();
    }

}
