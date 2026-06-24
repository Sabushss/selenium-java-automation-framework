package com.qa.tests;

import com.qa.config.ConfigReader;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * LoginTest - Test cases for Login functionality
 * Demonstrates: TestNG, DataProvider, POM usage
 * Author: Subash A | QA Automation Engineer
 */
public class LoginTest extends BaseTest {

    // ── TC001: Valid Login ───────────────────────────────────────
    @Test(priority = 1, description = "TC001 - Verify successful login with valid credentials")
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed");

        ProductsPage productsPage = loginPage.loginAs(
                ConfigReader.getTestUsername(),
                ConfigReader.getTestPassword()
        );

        Assert.assertTrue(productsPage.isProductsPageDisplayed(),
                "Products page should be displayed after valid login");
        Assert.assertEquals(productsPage.getPageTitle(), "Products",
                "Page title should be 'Products'");
    }

    // ── TC002: Invalid Login ─────────────────────────────────────
    @Test(priority = 2, description = "TC002 - Verify error message with invalid credentials")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterUsername("invalid_user")
                 .enterPassword("wrong_password")
                 .clickLoginExpectingFailure();

        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message should be displayed");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"),
                "Error message text should match expected");
    }

    // ── TC003: Empty Credentials ─────────────────────────────────
    @Test(priority = 3, description = "TC003 - Verify error message when credentials are empty")
    public void testEmptyCredentials() {
        LoginPage loginPage = new LoginPage();
        loginPage.clickLoginExpectingFailure();

        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message should be displayed");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"),
                "Error should indicate username is required");
    }

    // ── TC004: Data Driven Login ─────────────────────────────────
    @Test(priority = 4, dataProvider = "loginDataProvider",
          description = "TC004 - Verify login with multiple credential sets (Data Driven)")
    public void testLoginDataDriven(String username, String password, String expectedResult) {
        LoginPage loginPage = new LoginPage();

        if (expectedResult.equals("success")) {
            ProductsPage productsPage = loginPage.loginAs(username, password);
            Assert.assertTrue(productsPage.isProductsPageDisplayed(),
                    "Login should succeed for: " + username);
        } else {
            loginPage.enterUsername(username)
                     .enterPassword(password)
                     .clickLoginExpectingFailure();
            Assert.assertTrue(loginPage.isErrorDisplayed(),
                    "Error should be shown for invalid user: " + username);
        }
    }

    @DataProvider(name = "loginDataProvider")
    public Object[][] loginDataProvider() {
        return new Object[][] {
            { "standard_user",   "secret_sauce",  "success" },
            { "locked_out_user", "secret_sauce",  "failure" },
            { "invalid_user",    "wrong_pass",    "failure" },
        };
    }

    // ── TC005: Logout ────────────────────────────────────────────
    @Test(priority = 5, description = "TC005 - Verify user can successfully logout")
    public void testLogout() {
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = loginPage.loginAs(
                ConfigReader.getTestUsername(),
                ConfigReader.getTestPassword()
        );
        Assert.assertTrue(productsPage.isProductsPageDisplayed(), "Should be on products page");

        LoginPage loggedOutPage = productsPage.logout();
        Assert.assertTrue(loggedOutPage.isLoginPageDisplayed(),
                "Should return to login page after logout");
    }
}
