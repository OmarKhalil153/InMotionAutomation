package pages;

/**
 * File: DomainSearchPage.java
 * Purpose: Part of the InMotion Hosting Selenium/TestNG framework.
 */

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

/**
 * Method: public class DomainSearchPage
 * Description: See inline comments for step-by-step behavior.
 */
public class DomainSearchPage {


    private final WebDriver driver;
    private  final WebDriverWait wait;

    private final By searchBox = By.xpath("//input[@id=\"domain_search_domain\"]");
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.
    private final By searchButton = By.xpath("//div[@class=\"row\"]//button[@id=\"domain_submit\"]");
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.
    private final By resultMessage = By.cssSelector("#cdk-accordion-child-0 .ctw-flex.ctw-flex-col.ctw-w-full");
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.
    private final By addToCartButton = By.xpath("(//div[@class=\"ng-star-inserted\"]//button[contains(@class, \"mat-button\") and contains(@class, \"mat-primary\")])[1]");
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.
    private final By domainLink = By.xpath("//div[@class=\"ctw-ml-1 ctw-underlinesemibold\"]/span[text()=\"DOMAIN\"]");
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.
    private final By cartTable = By.xpath("//div[@class=\"amp-cart-column col\"]");
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.
    private final By invalidSearchBox = By.xpath("//input[@id=\"mat-input-0\"]");
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.
    private final By invalidSearchButton = By.xpath("//div[@class=\"sm:ctw-ml-3 ctw-mt-3 sm:ctw-mt-0 ctw-w-full sm:ctw-w-min ng-star-inserted\"]//button[@class=\"mat-focus-indicator btn-spinner mat-button mat-button-base mat-raised-button mat-primary\"]");
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.
    private final By invalidResultMessage = By.xpath("//div[@class=\"ctw-text-center ctw-pb-8 ng-star-inserted\"]/span[@class=\"ctw-font-bold ctw-text-red-700\" and contains(text(), ' google.com is taken. ')]");
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.


    public DomainSearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    // ^ Explicit wait to handle dynamic elements / asynchronous UI.
    }


    public void searchDomain(String domain) {



        if(driver.findElement(searchBox).isDisplayed()){
        // Find a single element on the page.

            WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));

            input.clear();

            input.sendKeys(domain);
        // Typing into an input field.

            wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        // Click action to trigger UI behavior/navigation.

            final WebElement checkOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//payment-form[@class=\"ctw-mt-6 ctw-mb-16 amp-checkout\"]")));
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.

            if(!checkOut.isDisplayed()){
                wait.until(ExpectedConditions.visibilityOf(checkOut));

            }else {wait.until(ExpectedConditions.elementToBeClickable(domainLink)).click();}
        // Click action to trigger UI behavior/navigation.

        }
        else if (driver.findElement(cartTable).isDisplayed()) {wait.until(ExpectedConditions.elementToBeClickable(domainLink)).click();}
        // Click action to trigger UI behavior/navigation.
        // Find a single element on the page.

    }

    public void searchInvalidDomain(String invalidDomain) {


        final WebElement checkOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//payment-form[@class=\"ctw-mt-6 ctw-mb-16 amp-checkout\"]")));
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.

        if(!checkOut.isDisplayed()){
            wait.until(ExpectedConditions.visibilityOf(checkOut));

        }else {wait.until(ExpectedConditions.elementToBeClickable(domainLink)).click();}
        // Click action to trigger UI behavior/navigation.

            WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(invalidSearchBox));

            input.clear();

            input.sendKeys(invalidDomain);
        // Typing into an input field.

            wait.until(ExpectedConditions.elementToBeClickable(invalidSearchButton)).click();
        // Click action to trigger UI behavior/navigation.

    }

    public String getResultText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(resultMessage)).getText();
    }

    public String getInvalidResultText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(invalidResultMessage)).getText();
    }

    public void addDomainToCart() {

        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        // Click action to trigger UI behavior/navigation.
    }


}