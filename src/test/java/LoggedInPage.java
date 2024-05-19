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

public class LoggedInPage extends BasePage {

    protected By logOutButton = By.xpath("//a[@href=\"javascript:document.getElementById('logoutForm').submit()\"]");

    public LoggedInPage(WebDriver driver) {
        super(driver);
    }

    public void clickLogOutButton() {
        WebElement logOutButtonElement = waitAndReturnElement(logOutButton);
        logOutButtonElement.click();
    }

}
