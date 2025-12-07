## Automation Testing Project - SauceDemo.com Graduation Project DEPI (Digital Egypt Pioneers Initiative)
Developed by Group 201 for the DEPI Software
Testing Track.

### Project Overview

This project is an End‑to‑End UI Automation Framework built using Java, Selenium WebDriver, TestNG, Maven, and Allure Reports. It follows modern automation best practices, including:

Page Object Model (POM)

Fluent Design Pattern

Reusable Utilities & Custom Bot Wrapper

Structured Test Suites via testng.xml

Allure Reporting Integration

The project automates the full purchase flow on the SauceDemo web application.

## The framework is built to:

Provide readable, maintainable test scripts

Separate test logic from business logic

Support data-driven testing using JSON

Generate detailed test execution reports using allure-report

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
| Nada Khamis Mohamed | End-to-End | Engine setup, Bot, Allure-Report, DriverFactory, BotData, FluentBot, EndToEndTests, integration testing, overall coordination, Listener, Logger, Screenshot, testng.xml |

### Tools & Technologies

Design Patterns: Page Object Model + Fluent Pattern
-   **Language:** Java (17+)
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


```
NHA-201 [Team201_SaucedemoTesting]/
│
├── .allure/                              # Allure configuration files
├── .idea/                                # IntelliJ IDEA configuration
├── allure-report/                        # Generated Allure reports
├── allure-results/                       # Allure test results
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── Drivers/
│   │       │   └── DriverFactory.java    # WebDriver creation and management
│   │       │
│   │       ├── Engine/
│   │       │   ├── Bot.java              # Bot pattern implementation
│   │       │   ├── BotData.java          # Bot data handling
│   │       │   ├── BotLogger.java        # Logging utility
│   │       │   └── FluentBot.java        # Fluent for Bot pattern
│   │       │
│   │       └── SauceDemoPages/
│   │           ├── CartPage.java         # Shopping cart page object
│   │           ├── CheckoutConfirmationPage.java # Order confirmation page object
│   │           ├── CheckoutPage.java     # Checkout form page object
│   │           ├── HomePage.java         # Home/Inventory page object
│   │           ├── LoginPage.java        # Login page object
│   │           └── SortingFilterPage.java # Product filtering page object
│   │
│   ├── test/
│   │   └── java/
│   │       ├── Base/
│   │       │   ├── BaseTest.java         # Abstract base class with setup/teardown
│   │       │   └── BaseTestFluent.java   # Fluent base test class
│   │       │
│   │       ├── DataDrivenTest/
│   │       │   └── TestDataProvider.java # Data-driven test data provider
│   │       │
│   │       ├── Listener/
│   │       │   ├── IInvokedMethodResultListener.java # Test method result listener
│   │       │   └── ITestResultListener.java          # Test result listener
│   │       │
│   │       └── SauceDemoTests/
│   │           ├── CartPageTests.java                # Cart functionality tests
│   │           ├── CheckoutConfirmationPageTests.java # Confirmation tests
│   │           ├── CheckoutPageTests.java            # Checkout process tests
│   │           ├── EndToEndTest.java                 # End-to-end workflow tests
│   │           ├── HomePageTest.java                 # Home page functionality tests
│   │           ├── LoginPageTests.java               # Login tests
│   │           └── SortingFilterPageTest.java        # Filtering and sorting tests
│   │
│   └── resources/                        # Test resources and configuration
│
├── resources/                            # Project resources
├── test-output/                          # Test execution output
├── pom.xml                               # Maven configuration
├── README.md                             # Project documentation
└── testing.xml                           # TestNG suite configuration
```


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


### Implementation Flow
The project was developed in phases:

1.  **Login Module** (Ali Nabil Ali)
2.  **Home & Add to Cart** (Mohamed Mahmoud Mohamed)
3.  **Cart & Checkout** (Akram Hussen Ibraheem)
4.  **Filtering** (Ola Sabry Abbas)
5.  **End-to-End** (Nada Khamis Mohamed)

🧩 Key Components
1️⃣ BotData (Engine folder)

Handles reading and parsing JSON test data.

Centralized JSON reader

Supports array and object extraction

Ensures clean data-driven testing

2️⃣ BotLogger (Engine folder)

Custom logger based on log4j2.

Logs test execution steps

Creates structured logs for Allure

Easy troubleshooting during failures

3️⃣ DriverFactory (Drivers folder)

Manages all WebDriver setup & teardown.

Supports multiple browsers

Ensures thread safety when needed

Reduces duplicated setup code in tests

🧱 Framework Design
✔ Page Object Model (POM)

Each page has its own class and methods representing UI actions.

✔ TestNG

Used for:

Test structure

Parallel execution

DataProviders

Listeners

✔ Allure Reporting

The project supports Allure with:

Screenshots on failure

Logs attached to report

Step-level reporting

### Driver Factory Implementation

The DriverFactory class is responsible for creating and managing
WebDriver instances across different browsers:

**Key Features:**

-   Supports multiple browsers (Chrome, Firefox, Edge)
-   Browser-specific configurations
-   Logging for debugging
-   Extensible design for adding new browsers

✨ Future Enhancements

Add more test suites

Integrate CI/CD (GitHub Actions)

Support for WebDriver Manager
