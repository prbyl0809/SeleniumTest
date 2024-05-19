import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import java.util.*;  

import java.net.URL;
import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTests {
    public WebDriver driver;
    
    @Before
    public void setup() throws MalformedURLException  {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3");
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
    }

    @Test
    public void testLogin() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterEmail("your-email@example.com");
        loginPage.enterPassword("yourpassword");
        loginPage.clickLoginButton();

        LoggedInPage loggedInPage = new LoggedInPage(driver);
        WebElement logOutButtonElement = loggedInPage.waitAndReturnElement(loggedInPage.logOutButton);

        boolean isPresentAndVisible = logOutButtonElement != null && logOutButtonElement.isDisplayed();
        Assert.assertTrue(isPresentAndVisible);
    }

    @Test
    public void testLogout() {
        testLogin();

        LoggedInPage loggedInPage = new LoggedInPage(driver);
        loggedInPage.clickLogOutButton();

        LoginPage loginPage = new LoginPage(driver);
        WebElement emailElement = loginPage.waitAndReturnElement(loginPage.emailField);
        
        boolean isPresentAndVisible = emailElement != null && emailElement.isDisplayed();
        Assert.assertTrue(isPresentAndVisible);

    }
    
    @Test
    public void testSearchJobs() {
        testLogin();
        
        JobsPage jobsPage = new JobsPage(driver);

        jobsPage.selectLocation("Budapest");
        jobsPage.selectCategory("Informatikai - support");
        jobsPage.clickSearchButton();

        SearchResultPage searchResultPage = new SearchResultPage(driver);
        WebElement adsElement = searchResultPage.waitAndReturnElement(searchResultPage.ads);

        boolean isPresentAndVisible = adsElement != null && adsElement.isDisplayed();
        Assert.assertTrue(isPresentAndVisible);
    }

    @Test
    public void testInformationPage() {
        InformationPage infromationPage = new InformationPage(driver);
        String bodyText = infromationPage.getBodyText();
        Assert.assertTrue(bodyText.contains("A menedzsment"));
    }

    @Test
    public void testMultiplePages() {
        String[][] pages = {
            {"https://schonherz.hu/gyik", "Szakmai gyakorlatomat tudom-e rajtatok"},
            {"https://schonherz.hu/kapcsolat", "Kelen Iroda"},
            {"https://schonherz.hu/partnereknek", "illetve a legjobb egyetemekre bejutva"},
        };

        for (String[] page : pages) {
            StaticPage staticPage = new StaticPage(driver, page[0], page[1]);
            staticPage.verifyPage(staticPage.expectedText);
        }
    }

    @Test
    public void testHover() {
        MainPage mainPage = new MainPage(driver);
        Actions actions = new Actions(driver);

        WebElement newsButton = mainPage.waitAndReturnElement(By.xpath("//a[@href=\"/hirek\"]"));
        actions.moveToElement(newsButton).perform();

        String hoverColor = newsButton.getCssValue("background-color");
        System.out.println("Color: " + hoverColor);

        String expectedColor = "rgba(16, 235, 255, 1)";
        Assert.assertEquals(expectedColor, hoverColor); 
    }
    
    @Test
    public void testBrowserBackButton() {
        MainPage mainPage = new MainPage(driver);
        String mainPageTitle = driver.getTitle();
        System.out.println("First page title: " + mainPageTitle);

        InformationPage infromationPage = new InformationPage(driver);
        String infromationPageTitle = driver.getTitle();
        System.out.println("Second page title: " + infromationPageTitle);

        driver.navigate().back();

        String titleAfterBack = driver.getTitle();
        System.out.println("Title after back: " + titleAfterBack);

        Assert.assertEquals(mainPageTitle, titleAfterBack);
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
