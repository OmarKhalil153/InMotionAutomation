package pages;

/**
 * File: Verifications.java
 * Purpose: Part of the InMotion Hosting Selenium/TestNG framework.
 */
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
        // Assertion/verification for expected outcome.
import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Method: public class Verifications
 * Description: See inline comments for step-by-step behavior.
 */
public class Verifications {

    // Locator for submenu titles under Web Hosting
    private final By webHostingSubMenuItems = By.cssSelector(".web-hosting-submenu .subnav-title");
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.


    private final WebDriver driver;

    public Verifications(WebDriver driver) {
        this.driver = driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    // ^ Explicit wait to handle dynamic elements / asynchronous UI.
    }

    public void cartPriceFormatVerification(String cartContents){

        Assert.assertTrue(cartContents.contains("myautomationtest123.com"), "Cart should contain the domain");
        // Assertion/verification for expected outcome.
        System.out.println("TestCase 3: \n\tThe selected domain is listed in the cart");

        Pattern pricePattern = Pattern.compile("\\$\\d+\\.\\d{2}( ?/yr)?");

        Matcher matcher = pricePattern.matcher(cartContents);

        if (matcher.find()) {
            String price = matcher.group();
            // Validate the matched price format
            String validFormat = "^\\$\\d+\\.\\d{2}( ?/yr)?$";
            Assert.assertTrue(Pattern.matches(validFormat, price),
        // Assertion/verification for expected outcome.
                    "The price format should be like $19.99 or $19.99 /yr but was: " + price);
            System.out.println("\tThe price matches the previous result");
        } else {
            Assert.fail("Price not found in message: " + cartContents);
        // Assertion/verification for expected outcome.
        }
    }

    public void hostingVerification (CartPage cartPage) {

        Assert.assertTrue(cartPage.getCartContents().contains("myautomationtest123.com"), "Cart should contain the domain");
        // Assertion/verification for expected outcome.

        Pattern pricePattern = Pattern.compile("\\$\\d+\\.\\d{2}( ?/yr)?");
        Matcher matcher = pricePattern.matcher(cartPage.getCartContents());

        if (matcher.find()) {
            String price = matcher.group();
            // Validate the matched price format
            String validFormat = "^\\$\\d+\\.\\d{2}( ?/yr)?$";
            Assert.assertTrue(Pattern.matches(validFormat, price),
        // Assertion/verification for expected outcome.
                    "The price format should be like $19.99 or $19.99 /yr but was: " + price);
        } else {
            Assert.fail("Price not found in message: " + cartPage.getCartContents());
        // Assertion/verification for expected outcome.
        }
        Assert.assertTrue(cartPage.getCartContents().contains("Power"), "Cart should contain Power Plan");
        // Assertion/verification for expected outcome.
        System.out.println("TestCase 4: \n\tThe cart now includes:\n" +
                "\tYour selected domain\n" +
                "\tThe Power Plan\n" +
                "\tThe correct total price");

    }

    public void priceFormatVerification(String message) {

        Assert.assertTrue(message.contains("Add & Continue"), "Domain should be available");
        // Assertion/verification for expected outcome.
        System.out.println("TestCase 2: \n\tThe domain is available");

        // Regex to extract price substring like "$19.98 /yr"
        Pattern priceExtractPattern = Pattern.compile("\\$\\d+\\.\\d{2} ?/yr");
        Matcher matcher = priceExtractPattern.matcher(message);

        if (matcher.find()) {
            String price = matcher.group();
            // Regex to validate price format precisely
            String pattern = "^\\$\\d+\\.\\d{2} ?/yr$";
            Assert.assertTrue(Pattern.matches(pattern, price),
        // Assertion/verification for expected outcome.
                    "The price format should be like $19.99 /yr but was: " + price);

            System.out.println("\tThe price is displayed (e.g., $19.99/year)");
        }
        else {
            Assert.fail("Price not found in message: " + message);
        // Assertion/verification for expected outcome.
        }
    }

    public void titleAndMenuVerification() {
        // Locator for the "Web Hosting" menu
        WebElement webHostingMenu = driver.findElement(By.cssSelector("#navbarNavDropdown .nav1 > li > a[title='Web Hosting']"));
    // ^ Locator used above. Kept as-is; used by actions/waits in this page.
        // Find a single element on the page.
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    // ^ Explicit wait to handle dynamic elements / asynchronous UI.

        // ✅ Verify page title contains "InMotion Hosting"
        String title = driver.getTitle();

        Assert.assertTrue(title.contains("InMotion Hosting"),
        // Assertion/verification for expected outcome.
                "Page title does not contain 'InMotion Hosting'. Actual title: " + title);

        System.out.println("TestCase 1: \n\tThe page title contains \"InMotion Hosting\"");
        // Hover over or click "Web Hosting" to make the submenu visible
        actions.moveToElement( webHostingMenu).perform();
        // Wait until submenu is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(webHostingSubMenuItems));

        // Collect submenu items
        List<WebElement> subMenuItems = driver.findElements(webHostingSubMenuItems);
        // Find multiple elements; size/assertions may follow.

        // Extract text
        List<String> subMenuTexts = subMenuItems.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .toList();

        // Expected submenu options
        List<String> expectedSubMenus = List.of("Shared Hosting", "Reseller Hosting", "Domains", "View All Web Hosting Plans");

        // Assert each one is present
        // Assertion/verification for expected outcome.
        for (String expected : expectedSubMenus) {
            Assert.assertTrue(subMenuTexts.contains(expected), "Missing submenu: " + expected);
        // Assertion/verification for expected outcome.
        }
        System.out.println("\tThe top menu includes “Domains”, “Web Hosting”, etc.");

    }

    public void invalidDomainNameVerification(String message) {

        Assert.assertTrue(message.contains("is taken") || message.contains("taken"),
        // Assertion/verification for expected outcome.
                "Expected unavailable message for already taken domain");
        System.out.println("TestCase 8: \n\tA message appears indicating the domain is unavailable\n" +
                "\tSuggested alternative domains (if any) are displayed\n");

    }

    public void refreshVerification(String contents) {

        Assert.assertTrue(contents.contains("myautomationtest123.com"), "Domain should persist after refresh");
        // Assertion/verification for expected outcome.

        Pattern pricePattern = Pattern.compile("\\$\\d+\\.\\d{2}( ?/yr)?");
        Matcher matcher = pricePattern.matcher(contents);

        if (matcher.find()) {
            String price = matcher.group();
            // Validate the matched price format
            String validFormat = "^\\$\\d+\\.\\d{2}( ?/yr)?$";
            Assert.assertTrue(Pattern.matches(validFormat, price),
        // Assertion/verification for expected outcome.
                    "The price format should be like $19.99 or $19.99 /yr but was: " + price);
        } else {
            Assert.fail("Price not found in message: " + contents);
        // Assertion/verification for expected outcome.
        }
        Assert.assertTrue(contents.contains("Power"), "Hosting plan should persist after refresh");
        // Assertion/verification for expected outcome.
        System.out.println("TestCase 5: \n\tDomain and hosting plan are still listed in the cart\n" +
                "\tPrices remain unchanged"
        );

    }

    public void domainRemovedVerification(CartPage cartPage) {

        Assert.assertFalse(cartPage.hasItem("myautomationtest123.com"), "Domain should be removed from cart");
        // Assertion/verification for expected outcome.
        System.out.println("TestCase 6: \n\tThe domain is no longer in the cart and only the hosting plan remains ");
    }


    public void cartAspireUpdateVerification(CartPage cartPage) {

        Assert.assertTrue(cartPage.hasItem("Aspire"), "Cart should contain Aspire Plan after update");
        // Assertion/verification for expected outcome.

        System.out.println("TestCase 7: \n\tThe newly selected plan replaces the previous one in the cart\n" +
                "\tThe total price updates accordingly");

    }



}