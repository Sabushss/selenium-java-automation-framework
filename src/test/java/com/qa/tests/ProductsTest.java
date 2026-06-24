package com.qa.tests;

import com.qa.config.ConfigReader;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * ProductsTest - Test cases for Products page functionality
 * Author: Subash A | QA Automation Engineer
 */
public class ProductsTest extends BaseTest {

    private ProductsPage productsPage;

    @BeforeMethod
    public void loginBeforeTest() {
        productsPage = new LoginPage().loginAs(
                ConfigReader.getTestUsername(),
                ConfigReader.getTestPassword()
        );
    }

    @Test(priority = 1, description = "TC006 - Verify products page loads with correct title")
    public void testProductsPageTitle() {
        Assert.assertEquals(productsPage.getPageTitle(), "Products",
                "Products page title should be 'Products'");
    }

    @Test(priority = 2, description = "TC007 - Verify products are displayed on page")
    public void testProductsAreDisplayed() {
        int count = productsPage.getProductCount();
        Assert.assertTrue(count > 0, "At least one product should be displayed");
        System.out.println("Total products displayed: " + count);
    }

    @Test(priority = 3, description = "TC008 - Verify adding product to cart increments cart count")
    public void testAddProductToCart() {
        int cartCountBefore = productsPage.getCartCount();
        productsPage.addFirstProductToCart();
        int cartCountAfter = productsPage.getCartCount();

        Assert.assertEquals(cartCountAfter, cartCountBefore + 1,
                "Cart count should increment by 1 after adding a product");
    }

    @Test(priority = 4, description = "TC009 - Verify first product has valid name and price")
    public void testProductDetails() {
        String productName = productsPage.getFirstProductName();
        String productPrice = productsPage.getFirstProductPrice();

        Assert.assertFalse(productName.isEmpty(), "Product name should not be empty");
        Assert.assertTrue(productPrice.startsWith("$"), "Product price should start with $");
        System.out.println("Product: " + productName + " | Price: " + productPrice);
    }
}
