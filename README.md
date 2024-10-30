
# HerbaVita Cucumber BDD Project

##  Project Description

The HerbaVita project is focused on testing an e-commerce application for health and wellness products. The platform enables users to browse a wide range of products, add items to their shopping cart, complete purchases, and manage their personal profiles. The main objectives of this testing project are to validate the core functionalities of the application, ensure seamless user experience, and verify that the platform aligns with the specified requirements.

Key features of the HerbaVita application include:

- **Product Browsing**: Users can browse different categories of health and wellness products, filter results, and view detailed product descriptions.
- **Shopping Cart**: Users can add products to their cart, update quantities, and view the total price before proceeding to checkout.
- **Checkout Process**: Secure and streamlined checkout, including entering shipping details and selecting payment methods.
- **User Profile Management**: Allows users to update personal information, view order history, and manage saved addresses.
- **Authentication**: Secure login and logout features to protect user data and sessions.
- **Search Functionality**: Users can search for specific products or categories using keywords.

Testing ensures that these features function correctly and reliably, focusing on user interactions, data processing, and compliance with business requirements. Additionally, the project verifies performance across different scenarios, such as successful purchases, profile updates, and error handling for invalid inputs.
## Table of Contents

1. [Project Structure](#project-structure)
2. [Dependency Injection](#dependency-injection)  <!-- Добавяме линк към новата секция тук -->
3. [Installation Steps](#installation)
4. [Running Tests](#running-tests)
5. [Reporting](#reporting)
6. [Languages, Tools and Frameworks](#languages-tools-and-frameworks)


## Project Structure


- **src/test/java**: Contains all test-related classes and resources.
    - **cucumberOptions**: Houses classes for test execution.
        - `FailedTestRunner.java` – For re-running failed tests.
        - `JUnitTestRunner.java` – For executing test scenarios.

    - **features**: Gherkin feature files, defining test scenarios for various functionalities such as login, search product, product details and checkout.

    - **pageObjects**: Implements the Page Object Model (POM) pattern, providing modular and maintainable code for different pages.
        - `BasePage.java` – Base class that includes common functionalities shared across all pages, such as navigation methods, wait conditions, and element locators.
        - `HomePage.java` – Page object class specifically for the home page of the HerbaVita application. This class handles elements and actions specific to the home page, including:
            - Navigation to different sections of the website.
            - Interactions with featured products or categories.
            - Searching for items or accessing promotional banners.
            - Verifying homepage elements like welcome messages or product highlights.
        - **Additional Page Classes** – Separate classes for each distinct page or functionality within the HerbaVita application, ensuring modularity and ease of maintenance.

    - **stepDefinitions**: Contains step definition classes, where each method corresponds to a step in the Gherkin feature files. This is where the test steps are implemented.
        - `Hooks.java`: Manages setup and teardown actions for scenarios and steps. Includes:
            - `@After` – Closes the browser after each scenario.
            - `@AfterStep` – Captures screenshots on failure, adding them to the report for easier debugging.

  - **utilities**: Contains utility classes for common functions and shared resources across the project:
      - `TestBase.java` – Manages the WebDriver setup and browser configurations based on properties from `global.properties`. Supports multiple browsers (Chrome, Firefox, Edge) and headless mode.
      - `TestContextSetup.java` – Provides a shared context for all classes, allowing easy access to `WebDriver`, `PageObjectManager`, and other common utilities.
      - `GenericUtils.java` – Contains helper methods, such as `SwitchWindowsToChild()` for handling multiple windows in browser sessions.     

- **src/test/resources**:
    - **global.properties**: Contains essential configuration settings for the project, such as browser preferences and environment URLs. For detailed descriptions of each setting, refer to comments in the `global.properties` file.
    - **extent.properties**: Configures settings for ExtentReports, including report paths, output formats, and screenshot handling. Detailed configurations can be found in the `extent.properties` file.


- **test-output**:
    - Stores generated reports and logs after test execution.
    - **ExtentReport-[DateTime]** folders – Each folder contains:
        - An HTML report (`Spark.html`) for a specific test execution, allowing detailed test result review.
        - Automatically generated screenshots for any failed tests, which are attached to the report for easier debugging and issue analysis.

- **pom.xml**: Maven configuration file that manages project dependencies, plugins, and reporting configurations

## Dependency Injection

The project utilizes **Dependency Injection** with `cucumber-picocontainer` to manage and provide shared resources across various classes, such as `WebDriver`, `PageObjectManager`, and `GenericUtils`. This approach improves modularity, makes the codebase more testable, and facilitates the management of dependencies, particularly in complex testing environments. By leveraging `cucumber-picocontainer`, dependencies are automatically injected into classes, simplifying test setup and reducing the need for manual instantiation.



## Installation

To set up and run the tests locally, ensure you have Java and Maven installed.

1. Clone the repository:
   ```bash
   git clone https://github.com/FGargov/HerbaVita-Project-Cucumber.git
   ```
2. Navigate to the project directory:
   ```bash
   cd HerbaVita-Project-Cucumber
   ```
3. Install dependencies using Maven:
   ```bash
   mvn clean install
   ```

## Running Tests

The project uses **JUnit** for test execution. You can run the tests using the following command:
```bash
mvn test
```

You can also run specific groups of tests using tags, such as @SmokeTest or @RegressionTest, by specifying the tag in the Maven command:

#### Run Smoke Tests:
```
mvn test -Dcucumber.filter.tags="@SmokeTest"
```

#### Run Regression Tests:
```
mvn test -Dcucumber.filter.tags="@RegressionTest"
```


### Test Execution Options

- **JUnitTestRunner**: Runs the entire test suite.
- **FailedTestRunner**: Re-runs only the failed scenarios for improved debugging.


## Reporting

This project generates detailed test reports with **ExtentReports**, configured through Maven in the `pom.xml` file.

To view reports after execution, open the generated HTML report located in the most recent `ExtentReport` folder under:
```
test-output/ExtentReport-[DateTime]/SparkReport/Spark.html
```

## Languages, Tools and Frameworks

To run this project, make sure you have the following installed on your system:
- **Java** – Programming language for writing test scripts (Java SDK 11 or later).
- **Cucumber** – BDD tool to write test scenarios in Gherkin syntax.
- **Selenium WebDriver** – Automates browser interactions.
- **JUnit 4** – Test framework for managing test execution.
- **ExtentReports** – Generates visual reports for test results.

The project is compatible with Windows, macOS, and Linux.



