package tests;

/**
 * File: InMotionE2ETest.java
 * Purpose: Part of the InMotion Hosting Selenium/TestNG framework.
 */

import com.inmotion.base.BaseTest;
import org.testng.annotations.Test;
import pages.*;

/**
 * Method: public class InMotionE2ETest extends BaseTest
 * Description: See inline comments for step-by-step behavior.
 */
public class InMotionE2ETest extends BaseTest {



    @Test(priority = 1)
    public void openWebsiteTest() {
        HomePage homePage = new HomePage(driver);
        Verifications verifications = new Verifications(driver);
        //Open The website
        homePage.openHomePage();
        //Verify That The page title contains InMotion Hosting, and that the top menu includes “Domains”, “Web Hosting”, etc.
        verifications.titleAndMenuVerification();
        homePage.goToDomainSearch();
    }


    @Test(priority = 2)
    public void searchDomainTest() {
        DomainSearchPage domainSearchPage = new DomainSearchPage(driver);
        Verifications verifications = new Verifications(driver);
        domainSearchPage.searchDomain("myautomationtest123.com");
        String message = domainSearchPage.getResultText();
        verifications.priceFormatVerification(message);
        takeScreenshot("DomainSearchResult");
        domainSearchPage.addDomainToCart();
    }

    @Test(priority = 3)
    public void verifyDomainInCart() {
        CartPage cartPage = new CartPage(driver);
        String cartContents = cartPage.getCartContents();
        Verifications verifications = new Verifications(driver);
        verifications.cartPriceFormatVerification(cartContents);
        takeScreenshot("CartWithDomain");
    }

    @Test(priority = 4)
    public void addHostingPlanTest() throws InterruptedException {
        HostingPage hostingPage = new HostingPage(driver);
        CartPage cartPage = new CartPage(driver);
        Verifications verifications = new Verifications(driver);
        hostingPage.selectPlan("myautomationtest123.com");
        verifications.hostingVerification(cartPage);
        takeScreenshot("CartWithDomainAndPowerPlan");
    }

    @Test(priority = 5)
    public void cartPersistenceAfterRefresh() throws InterruptedException {
        Verifications verifications = new Verifications(driver);
        Thread.sleep(1000);
        driver.navigate().refresh();
        CartPage cartPage = new CartPage(driver);
        String contents = cartPage.getCartContents();
        verifications.refreshVerification(contents);
    }

    @Test(priority = 6)
    public void removeDomainFromCart() {
        CartPage cartPage = new CartPage(driver);
        Verifications verifications = new Verifications(driver);
        cartPage.removeDomain();
        verifications.domainRemovedVerification(cartPage);
    }

    @Test(priority = 7)
    public void updateHostingPlanTest() throws InterruptedException {
        CartPage cartPage = new CartPage(driver);
        Verifications verifications = new Verifications(driver);
        HostingPage hostingPage = new HostingPage(driver);
        cartPage.removeHostingPlan();
        hostingPage.selectAspirePlan("myautomationtest123.com");
        verifications.cartAspireUpdateVerification(cartPage);
        takeScreenshot("CartWithAspirePlan");
    }

    @Test(priority = 8)
    public void invalidDomainSearchTest() {
        DomainSearchPage domainSearchPage = new DomainSearchPage(driver);
        Verifications verifications = new Verifications(driver);
        domainSearchPage.searchInvalidDomain("google.com");
        String message = domainSearchPage.getInvalidResultText();
        verifications.invalidDomainNameVerification(message);
        takeScreenshot("InvalidDomainSearch");
    }
}