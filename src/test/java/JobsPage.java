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

public class JobsPage extends BasePage {

    protected By locationList = By.id("office");
    protected By categoryList = By.id("type");
    protected By searchButton = By.xpath("//div[@class='form-group']//button[@type='submit']");

    public JobsPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://schonherz.hu/diakmunkak/budapest");
    }

    public void selectLocation(String location) {
        WebElement locationListElement = waitAndReturnElement(locationList);
        locationListElement.sendKeys(location);
    }

    public void selectCategory(String category) {
        WebElement categoryListElement = waitAndReturnElement(categoryList);
        categoryListElement.sendKeys(category);
    }

    public void clickSearchButton() {
        WebElement searchButtonElement = waitAndReturnElement(searchButton);
        searchButtonElement.click();
    }

}
