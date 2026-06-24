# Selenium Java Automation Framework

> End-to-end test automation framework built using Selenium WebDriver + Java
> **Author:** Subash A | QA Automation Engineer
> **LinkedIn:** [linkedin.com/in/subash-arjunan](https://linkedin.com/in/subash-arjunan)

---

## Framework Overview

A production-ready Selenium automation framework implementing industry best practices for web application testing. Built to demonstrate real-world QA automation engineering skills.

**Application Under Test:** [SauceDemo](https://www.saucedemo.com) (demo e-commerce application)

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 11 |
| Automation | Selenium WebDriver 4.x |
| Test Runner | TestNG 7.x |
| Build Tool | Maven 3.x |
| BDD Framework | Cucumber 7.x + Gherkin |
| CI/CD | Jenkins + GitHub Actions |
| Reports | Extent Reports 5.x |
| Data Driven | Apache POI (Excel) |
| Driver Mgmt | WebDriverManager |
| Version Control | Git |
| Bug Tracking | JIRA |

---

## Framework Architecture

```
selenium-java-automation-framework/
│
├── src/
│   ├── main/java/com/qa/
│   │   ├── config/
│   │   │   └── ConfigReader.java          # Reads config.properties
│   │   ├── pages/
│   │   │   ├── BasePage.java              # Parent POM class with reusable actions
│   │   │   ├── LoginPage.java             # Login page object
│   │   │   └── ProductsPage.java          # Products page object
│   │   └── utils/
│   │       ├── DriverManager.java         # ThreadLocal WebDriver (parallel-safe)
│   │       ├── ExcelUtils.java            # Data Driven Testing via Apache POI
│   │       └── ScreenshotUtils.java       # Auto screenshot on failure
│   │
│   └── test/
│       ├── java/com/qa/
│       │   ├── tests/
│       │   │   ├── BaseTest.java          # Setup/teardown for all tests
│       │   │   ├── LoginTest.java         # Login test cases with DataProvider
│       │   │   └── ProductsTest.java      # Products page test cases
│       │   └── stepdefs/
│       │       └── LoginSteps.java        # Cucumber step definitions
│       └── resources/
│           ├── features/
│           │   └── Login.feature          # BDD Gherkin scenarios
│           └── config/
│               └── config.properties      # Environment configuration
│
├── testng.xml                             # TestNG suite (parallel execution)
├── Jenkinsfile                            # Jenkins CI/CD pipeline
├── .github/workflows/selenium-ci.yml     # GitHub Actions CI pipeline
└── pom.xml                               # Maven dependencies
```

---

## Key Features

- **Page Object Model (POM)** — All UI interactions encapsulated in page classes
- **Parallel Execution** — ThreadLocal DriverManager enables safe parallel test runs
- **Data Driven Testing** — TestNG DataProvider + Apache POI for Excel-based test data
- **Cucumber BDD** — Gherkin feature files for business-readable test scenarios
- **Cross-browser Testing** — Chrome, Firefox, Edge support via WebDriverManager
- **CI/CD Integration** — Jenkins pipeline + GitHub Actions for continuous execution
- **Auto Screenshot** — Captures screenshots automatically on test failure
- **Extent Reports** — Detailed HTML reports with pass/fail/skip statistics
- **Configurable** — All settings managed via config.properties

---

## Design Patterns Used

| Pattern | Implementation |
|---|---|
| Page Object Model | `BasePage`, `LoginPage`, `ProductsPage` |
| Singleton | `DriverManager` (ThreadLocal) |
| Factory Method | `DriverManager.initDriver()` browser selection |
| Data Provider | TestNG `@DataProvider` for data driven tests |

---

## Test Cases Covered

| Test ID | Description | Type |
|---|---|---|
| TC001 | Valid login with correct credentials | Smoke + Regression |
| TC002 | Invalid login shows error message | Regression |
| TC003 | Empty credentials shows validation | Regression |
| TC004 | Data driven login (multiple users) | Regression |
| TC005 | Logout returns to login page | Regression |
| TC006 | Products page title verification | Regression |
| TC007 | Products are displayed on page | Regression |
| TC008 | Add product to cart increments count | Regression |
| TC009 | Product has valid name and price | Regression |

---

## How to Run

### Prerequisites
- Java 11+
- Maven 3.6+
- Chrome browser installed

### Run All Tests
```bash
mvn clean test
```

### Run Smoke Tests Only
```bash
mvn clean test -Dgroups=smoke
```

### Run on Specific Browser
```bash
mvn clean test -Dbrowser=firefox
```

### Run in Headless Mode
```bash
mvn clean test -Dheadless=true
```

### Run with Specific TestNG Suite
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

---

## CI/CD Pipeline

**Jenkins:**
- Checkout → Build → Smoke Tests → Regression Suite → Publish Report
- Auto-archives screenshots on failure
- Publishes Extent HTML report

**GitHub Actions:**
- Triggers on push to `main` / `develop`
- Runs headless Chrome on Ubuntu
- Uploads test reports and failure screenshots as artifacts

---

## Results Achieved (Production Framework)

| Metric | Result |
|---|---|
| Regression time reduction | 40% (3 days to 6 hours) |
| Test coverage | 95%+ |
| Production defect escapes | Zero |
| Test cases automated | 300+ |
| Parallel thread execution | 3 threads |

---

## Author

**Subash A** — QA Automation Engineer
- 4+ Years experience in Selenium + Java automation
- Expertise: Selenium, Java, TestNG, Maven, Jenkins, Cucumber BDD, API Testing
- LinkedIn: [linkedin.com/in/subash-arjunan](https://linkedin.com/in/subash-arjunan)
