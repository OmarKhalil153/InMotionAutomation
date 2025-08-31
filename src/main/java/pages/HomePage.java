package pages;

/**
 * File: HomePage.java
 * Purpose: Part of the InMotion Hosting Selenium/TestNG framework.
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Method: public class HomePage
 * Description: See inline comments for step-by-step behavior.
 */
public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By domainsTab = By.cssSelector("a[title='Domains']");
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.


    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    // ^ Explicit wait to handle dynamic elements / asynchronous UI.
    }

    public void openHomePage() {
        // Deletes all the cookies for the current domain
        driver.manage().deleteAllCookies();
        driver.get("https://www.inmotionhosting.com/web-hosting/");
        wait.until(ExpectedConditions.titleContains("InMotion Hosting"));
    }

    public void goToDomainSearch() {
        WebElement domains = wait.until(ExpectedConditions.visibilityOfElementLocated(domainsTab));
        new Actions(driver).moveToElement(domains).perform();
        wait.until(ExpectedConditions.elementToBeClickable(domainsTab)).click();
        // Click action to trigger UI behavior/navigation.
    }
}