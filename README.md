# 🚀 InMotion Automation Testing Suite

This project provides end-to-end UI automation tests for the InMotion Hosting website.  
It is built using Selenium WebDriver (Java) + TestNG + Maven and follows a maintainable Page Object Model (POM) design.

## 📋 Features
- Automated validation of key flows on InMotion Hosting
- Page Object Model (POM) for clean, reusable code
- TestNG test framework for execution and reporting
- Screenshots captured during test execution
- Easy integration with CI/CD pipelines

## 🛠️ Tech Stack
- Language: Java
- Build Tool: Maven
- Test Framework: TestNG
- Automation Library: Selenium WebDriver
- Browser: Google Chrome (via WebDriverManager)

## ⚙️ Setup & Requirements
Make sure you have the following installed:
- JDK 11+
- Maven 3.8+
- Google Chrome

Maven will automatically download dependencies (e.g., WebDriverManager).

## ▶️ Running the Tests
Run the following command in the project root:

mvn clean test

This will execute the TestNG suite defined in testng.xml.  
Screenshots are automatically captured and saved under the ./screenshots/ directory.

## 📂 Project Structure
src/
  main/java/pages/        # Page Objects (encapsulate locators & actions)
  test/java/com/inmotion  # Base test class and E2E test cases
screenshots/              # Screenshots captured during execution
testng.xml                # TestNG suite configuration
pom.xml                   # Maven dependencies & build configuration

## 📝 Notes
- No production logic was changed; extensive inline comments have been added for clarity.
- Tests follow TestNG annotations (@BeforeClass, @Test, @AfterClass) to structure execution.
- This project can be extended with CI/CD pipelines for automated runs.

✅ With this setup, you can confidently run, maintain, and scale UI test automation for InMotion Hosting.
