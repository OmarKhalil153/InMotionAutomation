package com.inmotion.base;

/**
 * File: BaseTest.java
 * Purpose: Part of the InMotion Hosting Selenium/TestNG framework.
 * Notes:
 *  - Low-level comments explain what each locator/method/action does.
 *  - No functional changes were made—only comments added.
 *  - Allure reporting is integrated via a TestNG listener.
 */

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

/**
 * Method: public class BaseTest
 * Description: See inline comments for step-by-step behavior.
 */
public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);


        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    // ^ Explicit wait to handle dynamic elements / asynchronous UI.
        try {
            Files.createDirectories(Paths.get("screenshots"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void captureFailure(ITestResult result) {
        if (!result.isSuccess()) {
            takeScreenshot("FAIL_" + result.getName());
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void takeScreenshot(String name) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File dest = new File("screenshots/" + name + "_" + timestamp + ".png");
        try {
            Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}