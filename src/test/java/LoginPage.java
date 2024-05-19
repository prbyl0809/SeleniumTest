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

public class LoginPage extends BasePage {

    protected By emailField = By.id("Email");
    protected By passwordField = By.id("Password");
    protected By loginButton = By.xpath("//div[@class='col-md-12']/input[@type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://schonherz.hu/");
    }

    public void enterEmail(String email) {
        WebElement emailElement = waitAndReturnElement(emailField);
        emailElement.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordElement = waitAndReturnElement(passwordField);
        passwordElement.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginButtonElement = waitAndReturnElement(loginButton);
        loginButtonElement.click();
    }

}
