# InMotion Automation (Updated with Allure)

This project uses **Selenium WebDriver (Java) + TestNG + Maven** to validate key flows on the InMotion Hosting website.  
It now includes **Allure Reports** with automatic attachment of your **existing screenshots** saved under `screenshots/`.

## Requirements
- JDK 11+
- Maven 3.8+
- Google Chrome + ChromeDriver (managed by WebDriverManager)

## How to Run
```bash
mvn clean test
```
This will execute the TestNG suite from `testng.xml`. Screenshots captured by your tests are saved into `./screenshots`.

## Allure Report (auto-open)
After tests complete, the **Allure report opens automatically** (via `allure-maven` goal `serve`).  
If you prefer manual steps:
```bash
# Generate the report
mvn allure:report
# Or serve the report locally
mvn allure:serve
```
Reports are based on files in `target/allure-results`.

## Screenshot Attachments
- Your tests already call `takeScreenshot("...")` and save PNGs in `screenshots/`.
- The custom **`AllureListener`** attaches any screenshots created **during each test’s run window** (and also files that include the method name) to the Allure test.
- Attachments are added for **passed**, **failed**, and **skipped** tests.

## Project Structure
```
src/
  main/java/pages/        # Page Objects and verification helpers
  test/java/com/inmotion  # Base test, listener, and E2E tests
screenshots/              # Existing screenshots captured by your code
testng.xml                # Suite configuration (listener registered)
pom.xml                   # Maven + Allure config
```

## Notes
- No production logic was changed—only comments were added and Allure hooks were introduced.
- If you want to disable auto-opening of the report, run:
  ```bash
  mvn -DskipAllureServe=true clean test
  ```
  Or remove the `allure-maven` execution from `pom.xml`.