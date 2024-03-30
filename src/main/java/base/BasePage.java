package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import static resources.Constants.DEFAULT_WAIT_TIME;

public class BasePage {

    private final Properties prop;
    public String url;
    public static String screenshotDestinationPath;

    public BasePage() throws IOException {
        prop = new Properties();
        FileInputStream data = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\config.properties");
        prop.load(data);
    }

    public static WebDriver getDriver() {
        return WebDriverInstance.getDriver();
    }

    public static void waitForElementVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(DEFAULT_WAIT_TIME));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementText(WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(DEFAULT_WAIT_TIME));
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public String getUrl() {
        url = prop.getProperty("url");
        return url;
    }

    public static String takeSnapshot(String name) throws IOException {
        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String destFile = System.getProperty("user.dir") + "\\target\\screenshots\\" + name + timestamp() + ".png";
        screenshotDestinationPath = destFile;

        try {
            FileUtils.copyFile(srcFile, new File(destFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return name;
    }

    public static String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
    }

    public static String getScreenshotDestinationPath() {
        return screenshotDestinationPath;
    }

    public static void checkResult(String actualResult, String expectedResult, String message){
        try {
            Assert.assertEquals(actualResult, expectedResult);
            ExtentManager.pass(message);

        } catch (AssertionError e) {
            ExtentManager.fail(message + " - FAIL");
            Assert.fail(message + " - FAIL");
        }
    }

    public static void checkResult(Boolean actualResult, String message){
        try {
            Assert.assertTrue(actualResult);
            ExtentManager.pass(message);

        } catch (AssertionError e) {
            ExtentManager.fail(message + " - FAIL");
            Assert.fail(message + " - FAIL");
        }
    }

}
