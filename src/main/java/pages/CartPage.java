package pages;

/**
 * File: CartPage.java
 * Purpose: Part of the InMotion Hosting Selenium/TestNG framework.
 */

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
/**
 * Method: public class CartPage
 * Description: See inline comments for step-by-step behavior.
 */
public class CartPage {
    private final WebDriverWait wait;

    private final By cartItems = By.xpath("//div[@class=\"mat-expansion-panel-content ng-tns-c56-30 ng-trigger ng-trigger-bodyExpansion\"]");
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.
    private final By removeDomainButton = By.xpath("//div[contains(text(),'Domain Registration')]/ancestor::tr//mat-icon[contains(@class,'remove-cart-item')]");    // adjust as needed
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.
    private final By removeHostingButton = By.xpath("//div[contains(text(),'Power')]/ancestor::tr//mat-icon[contains(@class,'remove-cart-item')]");  // adjust as needed
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.
    private final By totalPriceLabel = By.cssSelector(".cart-total");          // adjust as needed
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.
    private final By choseDomainLater = By.xpath("//div[@class=\"ctw-w-full sm:ctw-w-min ng-star-inserted\"]//span[@class=\"mat-button-wrapper\" and contains(text(), \" Choose my domain later \")]");
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.


    public CartPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    // ^ Explicit wait to handle dynamic elements / asynchronous UI.
    }

    public String getCartContents() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartItems)).getText();
    }

    public void removeDomain() {
        wait.until(ExpectedConditions.elementToBeClickable(removeDomainButton)).click();
        // Click action to trigger UI behavior/navigation.
        wait.until(ExpectedConditions.elementToBeClickable(choseDomainLater)).click();
        // Click action to trigger UI behavior/navigation.
    }

    public void removeHostingPlan() {
        wait.until(ExpectedConditions.elementToBeClickable(removeHostingButton)).click();
        // Click action to trigger UI behavior/navigation.
    }

    public String getTotalPrice() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(totalPriceLabel)).getText();
    }

    public boolean hasItem(String name) {
        return getCartContents().contains(name);
    }




}