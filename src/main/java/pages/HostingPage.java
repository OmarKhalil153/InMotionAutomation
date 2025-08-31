package pages;

/**
 * File: HostingPage.java
 * Purpose: Part of the InMotion Hosting Selenium/TestNG framework.
 */
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

/**
 * Method: public class HostingPage
 * Description: See inline comments for step-by-step behavior.
 */
public class HostingPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By logo = By.xpath("//div[@class=\"ctw-ml-6 ctw-relative\"]//img[@src=\"assets/svg/imh-logo.svg\"]");
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.
    private final By webHosting = By.cssSelector("#navbarNavDropdown .nav1 > li > a[title='Web Hosting']");
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.
    private final By viewOptions = By.xpath("//a[@href='#web-hosting-page-section' and text()='View Web Hosting Options']");
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.
    private final By domainSearch = By.id("mat-input-0");
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.
    private final By searchButton = By.xpath("//*[@class=\"mat-focus-indicator btn-spinner mat-button mat-button-base mat-raised-button mat-primary\"]");
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.

    public HostingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    // ^ Explicit wait to handle dynamic elements / asynchronous UI.
    }




    public void selectPlan(String domain) throws InterruptedException {

        wait.until(ExpectedConditions.elementToBeClickable(logo)).click();
        // Click action to trigger UI behavior/navigation.
        wait.until(ExpectedConditions.elementToBeClickable(webHosting)).click();
        // Click action to trigger UI behavior/navigation.
        wait.until(ExpectedConditions.elementToBeClickable(viewOptions)).click();
        // Click action to trigger UI behavior/navigation.
        Thread.sleep(3000); // wait for page elements to load

        // Accept or close the cookie banner
        try {
            WebElement cookieBanner = driver.findElement(By.id("onetrust-accept-btn-handler"));
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.
        // Find a single element on the page.
            cookieBanner.click();
        // Click action to trigger UI behavior/navigation.
            Thread.sleep(1000); // give time for banner to disappear
        } catch (Exception e) {
            System.out.println("\tCookie banner not found or already closed.");
        }

        // Click the active plan's "Select Plan" button
        WebElement activePlanButton = driver.findElement(By.cssSelector("div.imh-switcher.active a.cta-link.btn-primary"));
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.
        // Find a single element on the page.
        activePlanButton.click();
        // Click action to trigger UI behavior/navigation.

        WebElement bestValuePlan = driver.findElement(By.xpath("//div[contains(@class,'imh-rostrum-card') and .//h3[text()='Power']]//div[contains(@class,'imh-switcher') and contains(@class,'active')]//a[contains(@class,'atc') and contains(@class,'btn-primary')]"));
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.
        // Find a single element on the page.
        bestValuePlan.click();
        // Click action to trigger UI behavior/navigation.
        /////////////////////////////////////////////////////

        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(domainSearch));
        input.clear();
        input.sendKeys(domain);
        // Typing into an input field.
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        // Click action to trigger UI behavior/navigation.

    }


    public void selectAspirePlan(String domain) throws InterruptedException {

        wait.until(ExpectedConditions.elementToBeClickable(logo)).click();
        // Click action to trigger UI behavior/navigation.
        wait.until(ExpectedConditions.elementToBeClickable(webHosting)).click();
        // Click action to trigger UI behavior/navigation.
        wait.until(ExpectedConditions.elementToBeClickable(viewOptions)).click();
        // Click action to trigger UI behavior/navigation.
        Thread.sleep(3000); // wait for page elements to load
        // Accept or close the cookie banner
        try {
            WebElement cookieBanner = driver.findElement(By.id("onetrust-accept-btn-handler"));
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.
        // Find a single element on the page.
            cookieBanner.click();
        // Click action to trigger UI behavior/navigation.
            Thread.sleep(1000); // give time for banner to disappear
        } catch (Exception e) {
            System.out.println("\tCookie banner not found or already closed.");
        }

        // Click the active plan's "Select Plan" button
        WebElement activePlanButton = driver.findElement(By.xpath("//div[contains(@class,'imh-rostrum-card') and .//h3[text()='Aspire']]//div[contains(@class,'imh-switcher') and contains(@class,'active')]//a[contains(@class,'cta') and contains(@class,'btn-primary')]"));
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.
        // Find a single element on the page.
        activePlanButton.click();
        // Click action to trigger UI behavior/navigation.


        WebElement aspirePlan = driver.findElement(By.xpath("//div[contains(@class,'imh-rostrum-card rostrum-badge') and .//h3[text()='Aspire']]//div[contains(@class,'imh-switcher') and contains(@class,'active')]//a[contains(@class,'atc') and contains(@class,'btn-primary')]"));
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.
        // Find a single element on the page.

        aspirePlan.click();
        // Click action to trigger UI behavior/navigation.


        /////////////////////////////////////////////////////

        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(domainSearch));
        input.clear();
        input.sendKeys(domain);
        // Typing into an input field.
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        // Click action to trigger UI behavior/navigation.


    }
}