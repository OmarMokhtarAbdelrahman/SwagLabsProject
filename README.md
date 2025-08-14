# 🛒 SwagLabs Demo Web Automation

## 📌 Project Description
This project automates functional testing for the **SwagLabs** demo e-commerce application.  
It validates core workflows like **login**, **product browsing**, **cart management**, and **checkout** to ensure application stability and reliability.  

## 🛠 Tech Stack
- **Language:** Java  
- **Automation Framework:** Selenium WebDriver  
- **Test Framework:** TestNG  
- **Build Tool:** Maven  
- **Reporting:** Allure Reports  

---

## 📂 Folder Structure

### `src/main/java`
```
swagLabsMain
 ├── driver
 │    └── DriverFactory.java                # Manages WebDriver instances
 │
 ├── pageObjects                            # Page Object Model classes
 │    ├── CartPage.java
 │    ├── CheckoutPage.java
 │    ├── FinalCheckoutPage.java
 │    ├── LoginPage.java
 │    ├── ProductDetailsPage.java
 │    ├── ProductsPage.java
 │    └── PurchaseInfoPage.java
 │
 ├── resources
 │    └── data.properties                   # Test configuration data
 │
 └── utils                                  # Utility/helper classes
      ├── AbstractComponent.java
      ├── AllureListener.java
      ├── BrowserActions.java
      ├── ElementsActions.java
      ├── JsonDataUtility.java
      ├── LoggingUtility.java
      ├── RetryConfig.java
      ├── Scrolling.java
      └── Waits.java
```

### `src/test/java`
```
swagLabsTests
 ├── testComponents
 │    └── BaseTest.java                     # Test base setup & teardown
 │
 ├── testData
 │    └── testData.json                     # Test input data
 │
 └── tests
      ├── cart
      │    └── CheckingCartAfterLogoutTests.java
      │
      ├── checkout
      │    └── CheckoutErrorValidationsTests.java
      │
      ├── e2e
      │    └── SubmittingOrderTests.java
      │
      ├── login
      │    ├── LoginErrorValidationsTests.java
      │    └── ValidLoginTests.java
      │
      └── productManagement
           └── AddingAndRemovingProductsTest.java
```

---

## ⚙️ Setup
1. **Clone the repository**  
```bash
git clone <your-repo-link>
cd <project-folder>
```
2. **Open in your IDE** (e.g., IntelliJ IDEA or Eclipse)  
3. **Ensure Java & Maven are installed**  

No manual dependency setup is needed — all dependencies are managed in `pom.xml`.

---

## ▶️ Running Tests
Run all tests from the terminal:
```bash
mvn clean test
```

Or run specific test classes directly from your IDE.

---

## 🗂 TestNG XML Files

The project includes multiple **TestNG suite XML files** to organize and run different sets of tests.

| File Name                | Description |
|--------------------------|-------------|
| **`testng.xml`**         | Main suite that runs all test cases in the project (full regression). |
| **`login-tests.xml`**    | Contains only login-related test cases. |
| **`checkout-tests.xml`** | Contains only checkout-related test cases. |
| **`smoke-tests.xml`**    | Minimal set of high-priority tests to quickly verify application stability. |

To execute a suite:
```bash
mvn clean test -DsuiteXmlFile=<file-name>.xml
```

Example:
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

## 📊 Test Reports
After running tests, generate and open Allure reports with:
```bash
allure serve
```
This will automatically launch the report in your default browser.

---

## 🚀 Future Improvements
- Add more negative and boundary test cases  
- Implement parallel execution for faster runs  
- Add cross-browser testing (Chrome, Firefox, Edge)  
- Integrate with CI/CD pipelines (Jenkins, GitHub Actions)  
