package com.qa.stepdefs;

import com.qa.config.ConfigReader;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import com.qa.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.testng.Assert;

/**
 * LoginSteps - Cucumber Step Definitions for Login feature
 * Author: Subash A | QA Automation Engineer
 */
public class LoginSteps {

    private LoginPage loginPage;
    private ProductsPage productsPage;

    @Before
    public void setUp() {
        DriverManager.initDriver();
        DriverManager.getDriver().get(ConfigReader.getBaseUrl());
        loginPage = new LoginPage();
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed");
    }

    @When("I enter username {string}")
    public void i_enter_username(String username) {
        loginPage.enterUsername(username);
    }

    @When("I enter password {string}")
    public void i_enter_password(String password) {
        loginPage.enterPassword(password);
    }

    @When("I click the login button")
    public void i_click_the_login_button() {
        try {
            productsPage = loginPage.clickLogin();
        } catch (Exception e) {
            // Expected for failed login scenarios
        }
    }

    @Then("I should be redirected to the products page")
    public void i_should_be_redirected_to_the_products_page() {
        Assert.assertTrue(productsPage.isProductsPageDisplayed(),
                "Should be redirected to products page");
    }

    @Then("the page title should be {string}")
    public void the_page_title_should_be(String expectedTitle) {
        Assert.assertEquals(productsPage.getPageTitle(), expectedTitle,
                "Page title should match");
    }

    @Then("I should see an error message containing {string}")
    public void i_should_see_an_error_message_containing(String expectedError) {
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message should be displayed");
        Assert.assertTrue(loginPage.getErrorMessage().contains(expectedError),
                "Error message should contain: " + expectedError);
    }

    @Then("the login result should be {string}")
    public void the_login_result_should_be(String result) {
        if (result.equals("success")) {
            Assert.assertTrue(productsPage.isProductsPageDisplayed(),
                    "Should be on products page for successful login");
        } else {
            Assert.assertTrue(loginPage.isErrorDisplayed(),
                    "Error should be shown for failed login");
        }
    }
}
