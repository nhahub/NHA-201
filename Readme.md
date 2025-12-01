<h1 align="center">
  SauceDemo.com UI Automation Testing
</h1>

<h3 align="center">
  DEPI (Digital Egypt Pioneers Initiative) – Software Testing Track
</h3>

<h3 align="center">
  Graduation Project – Group 201
</h3>



---

## Contents

- [About the Project](#about-the-project)
- [Team](#team)
- [Training and Supervision](#training-and-supervision)
- [Project Structure](#project-structure)
- [Tools & Technologies](#tools--technologies)

---

## About the Project
This project is the graduation project for the Digital Egypt Pioneers Initiative (DEPI) – Software Testing Track, Group 201.  
It is a UI automation test suite for the demo e-commerce website SauceDemo.com.  
The main goal of this project is to demonstrate a clean and maintainable test automation framework that covers the main user journey: login, viewing products, adding items to the cart, proceeding to checkout, completing an order, and verifying the final confirmation page.

---

## Team
This project was developed by Group 201 – DEPI Software Testing Track.

Participating students:
- Ali Nabil Ali
- Akram Hussen Ibraheem
- Mohamed Mahmoud Mohamed
- Nada Khamis Mohamed
- Ola Sabry Abbas

---

## Training and Supervision
- Training company: Skills Dynamix
- Instructor: E. M. Saied Alshafey

---

## Project Structure

1) Engine Layer  
   Packages / Classes:
- src/main/java/Engine
    - Bot
    - BotLogs
    - FluentBot

2) Page Layer  
   Packages / Classes:
- src/main/java/SauceDemoPages
    - HomePage
    - LoginPage
    - CartPage
    - CheckoutPage
    - CheckoutConfirmationPage
    - SortingFilterPage

3) Test Layer  
   Packages / Classes:
- src/test/java/Base
    - BaseTest
- src/test/java/DataDrivenTest
    - TestDataProvider
- src/test/java/SauceDemoTests
    - HomePageTest
    - LoginPageTests
    - CartPageTests
    - CheckoutPageTests
    - CheckoutConfirmationPageTests
    - SortingFilterPageTest

4) Configuration  
   Files:
- pom.xml

---

## Tools & Technologies

- Java – main programming language for the automation framework
- Selenium WebDriver – browser automation for the SauceDemo UI
- TestNG – test runner and assertion framework for organizing and executing tests
- Maven – build and dependency management tool
- IntelliJ IDEA – IDE used to develop and run the test suite
- Page Object Model (POM) – design pattern for separating page elements/actions from test logic
- Allure Report – used to generate visual and interactive test execution reports

---
