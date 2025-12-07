## NHA-201 Automation Testing Project - SauceDemo.com Graduation Project DEPI (Digital Egypt Pioneers Initiative)

This document consolidates information about the UI automation test
suite for SauceDemo.com, developed by Group 201 for the DEPI Software
Testing Track.

### Project Overview

This project represents the graduation project for the Digital Egypt
Pioneers Initiative (DEPI) -- Software Testing Track, Group 201. It is a
comprehensive UI automation test suite designed for the demo e-commerce
website SauceDemo.com. The primary goal is to showcase a clean,
maintainable, and production-ready automation framework that covers the
essential user journeys on the platform.

### Project Objective & Scope

**Objective:** To automate the front-end testing of SauceDemo.com,
validating user interactions and system behavior through a robust test
suite.

**Scope:**

-   Login functionality (valid, invalid, edge cases).
-   Home page and product display.
-   Add to cart functionality.
-   Shopping cart management.
-   Checkout process.
-   End-to-end user workflows.
-   Product filtering and search.

### Team Members & Responsibilities

### Developed by Group 201 -- DEPI Software Testing Track.

| Name | Primary Responsibility | Key Modules/Tasks |
|------|------------------------|-------------------|
| Ali Nabil Ali | Login & Engine | Engine setup, Bot, LoginPage, Login Tests |
| Mohamed Mahmoud Mohamed | Home & Add to Cart | Engine setup, Bot, HomePage, HomePageTests, DataBase |
| Akram Hussen Ibraheem | Cart & Checkout | Engine setup, Bot, Allure-Report, CartPage, CheckoutPage, ConfirmationPage, Cart & Checkout Tests |
| Ola Sabry Abbas | Filtering | Engine setup, Bot, Filtering functionality, FilteringTests |
| Nada Khamis Mohamed | End-to-End | Engine setup, Bot, Allure-Report, DriverFactory, BotData, FluentBot, EndToEndTests, integration testing, overall coordination, Listener, Logger, Screenshot |

### Tools & Technologies

-   **Language:** Java (11+)
-   **Automation:** Selenium WebDriver (4.0+)
-   **Test Runner:** TestNG (7.0+)
-   **Build Tool:** Maven (3.6+)
-   **Design Patterns:** Page Object Model (POM), Singleton, Bot Pattern
-   **Logging:** Log4j2
-   **Reporting:** Allure Reports
-   **IDE:** IntelliJ IDEA 
-   **Testing Website:** SauceDemo.com
-   **Training Company:** Skills Dynamix

### Project Structure

The project follows a layered architecture with a well-defined directory
structure:

**NHA-201 [Team201_SaucedemTesting]/**

.allure/ *(Allure configuration files)*

.idea/ *(IntelliJ IDEA configuration)*

allure-report/ *(Generated Allure reports)*

allure-results/ *(Allure test results)*

**src/main/java/Drivers/**

-   DriverFactory.java *(WebDriver creation and management)*

-   **Engine/**
    -   Bot.java *(Bot pattern implementation)*
    -   BotData.java *(Bot data handling)*
    -   BotLogger.java *(Logging utility)*
    -   FluentBot.java *(Fluent API for Bot pattern)*

-   **SauceDemo Pages/**
    -   CartPage.java *(Shopping cart page object)*
    -   CheckoutConfirmationPage.java *(Order confirmation page object)*
    -   CheckoutPage.java *(Checkout form page object)*
    -   HomePage.java *(Home/Inventory page object)*
    -   LoginPage.java *(Login page object)*
    -   SortingFilterPage.java *(Product filtering page object)*

-   **test/java/Base/**
    -   BaseTest.java *(Abstract base class with setup/teardown)*
    -   BaseTestFluent.java *(Fluent base test class)*

    -   **DataDrivenTest/**
        -   TestDataProvider.java *(Data-driven test data provider)*

    -   **Listener/**
        -   IInvokedMethodResultListener.java *(Test method result listener)*
        -   ITestResultListener.java *(Test result listener)*

    -   **SauceDemoTests/**
        -   CartPageTests.java *(Cart functionality tests)*
        -   CheckoutConfirmationPageTests.java *(Confirmation tests)*
        -   CheckoutPageTests.java *(Checkout process tests)*
        -   EndToEndTest.java *(End-to-end workflow tests)*
        -   HomePageTest.java *(Home page functionality tests)*
        -   LoginPageTests.java *(Login tests)*
        -   SortingFilterPageTest.java *(Filtering and sorting tests)*

    -   resources/ *(Test resources and configuration)*

-   resources/ *(Project resources)*

-   test-output/ *(Test execution output)*

-   pom.xml *(Maven configuration)*

-   README.md *(Project documentation)*

-   testing.xml *(TestNG suite configuration)*

### Architecture & Design Patterns

The framework utilizes several established design patterns to enhance
maintainability and readability:

-   **Page Object Model (POM):** Separates page elements and actions
    from test logic, promoting reusability and reducing code
    duplication.
-   **Singleton Pattern:** Ensures a single instance of WebDriver is
    managed throughout the test suite (via WebDriverManager) for
    resource efficiency.
-   **Bot Pattern:** Combines multiple actions into single, high-level,
    semantic methods (e.g., `performLogin()`) for cleaner test
    scripts.
-   **Abstract Base Class:** Provides shared setup (`@BeforeMethod`)
    and teardown (`@AfterMethod`) logic for all test classes, along
    with common utility methods.

### Test Coverage

The suite includes over 35 test cases covering critical functionalities:

-   **Login Tests:** (valid, invalid, empty fields, locked users).
-   **Inventory Tests:** (product display, add to cart).
-   **Cart Tests:** (item removal, checkout navigation).
-   **Checkout Tests:** (data validation, order completion).
-   **Filtering Tests:** (sorting, result verification).
-   **End-to-End Tests:** (complete purchase flows).

### Implementation Flow

The project was developed in phases:

1.  **Login Module** (Ali Nabil Ali)
2.  **Home & Add to Cart** (Mohamed Mahmoud Mohamed)
3.  **Cart & Checkout** (Akram Hussen Ibraheem)
4.  **Filtering** (Ola Sabry Abbas)
5.  **End-to-End** (Nada Khamis Mohamed)

### Driver Factory Implementation

The DriverFactory class is responsible for creating and managing
WebDriver instances across different browsers:

**Key Features:**

-   Supports multiple browsers (Chrome, Firefox, Edge)
-   Browser-specific configurations
-   Logging for debugging
-   Extensible design for adding new browsers

### Areas for Improvement

The framework can be enhanced in the following areas:

#### 1. Test Data Management

-   Implement external test data sources (JSON, Excel, CSV)
-   Create a dedicated TestDataProvider class
-   Implement data factory patterns for complex objects

#### 2. Wait Strategies

-   Implement element-specific wait conditions
-   Create custom wait utilities for common scenarios
-   Add dynamic timeout configuration

#### 3. Error Handling

-   Create custom exception types for different failure scenarios
-   Implement comprehensive error logging
-   Add recovery mechanisms for flaky tests

#### 4. Code Optimization

-   Reduce code duplication in test setup
-   Extract common assertion patterns into utilities
-   Optimize page object methods for better readability

#### 5. Advanced Features

-   Implement test retry mechanism for flaky tests
-   Add performance metrics and test execution time tracking
-   Integrate with CI/CD pipelines (Jenkins, GitHub Actions)
-   Add cross-browser testing automation

#### 6. Reporting & Documentation

-   Enhance Allure reports with custom annotations
-   Add video recording for failed tests
-   Create comprehensive API documentation

#### 7. Parallel Execution

-   Optimize ThreadLocal usage for better resource management
-   Implement test execution groups
-   Add dynamic test allocation for distributed testing

#### 8. Mobile & API Testing

-   Extend framework to support mobile automation
-   Integrate API testing capabilities
-   Add visual regression testing support

### Summary

The SauceDemo.com UI Automation project is a well-structured,
maintainable, and comprehensive test suite demonstrating professional
automation practices. It covers core functionalities, employs robust
design patterns, and provides detailed reporting, serving as an
excellent example of a graduation project.
