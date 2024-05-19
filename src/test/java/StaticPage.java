import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


public class StaticPage extends BasePage {

    public String url;
    public String expectedText;

    public StaticPage(WebDriver driver, String url, String expectedText) {
        super(driver);
        this.url = url;
        this.expectedText = expectedText;
        this.driver.get(this.url);
    }

    public void verifyPage(String text) {
        String bodyText = getBodyText();
        Assert.assertTrue("Expected text not found in page body", bodyText.contains(text));
    }
}
