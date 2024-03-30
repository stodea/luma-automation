package base;

import org.testng.annotations.*;

import java.io.IOException;

public class Hooks extends BasePage {

    public Hooks() throws IOException {
        super();
    }

    @BeforeMethod
    public void setup() {
        getDriver().get(getUrl());
    }

    @AfterTest
    public void tearDown() {
        WebDriverInstance.cleanupDriver();
    }

}
