package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * ProductsPage - Page Object for Products/Inventory page
 * Author: Subash A | QA Automation Engineer
 */
public class ProductsPage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "inventory_item")
    private List<WebElement> productList;

    @FindBy(css = ".inventory_item_name")
    private List<WebElement> productNames;

    @FindBy(css = ".inventory_item_price")
    private List<WebElement> productPrices;

    @FindBy(css = "button[id^='add-to-cart']")
    private List<WebElement> addToCartButtons;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public boolean isProductsPageDisplayed() {
        return isDisplayed(pageTitle) && getPageTitle().equalsIgnoreCase("Products");
    }

    public int getProductCount() {
        return productList.size();
    }

    public ProductsPage addFirstProductToCart() {
        if (!addToCartButtons.isEmpty()) {
            click(addToCartButtons.get(0));
        }
        return this;
    }

    public int getCartCount() {
        try {
            return Integer.parseInt(getText(cartBadge));
        } catch (Exception e) {
            return 0;
        }
    }

    public String getFirstProductName() {
        if (!productNames.isEmpty()) {
            return getText(productNames.get(0));
        }
        return "";
    }

    public String getFirstProductPrice() {
        if (!productPrices.isEmpty()) {
            return getText(productPrices.get(0));
        }
        return "";
    }

    public LoginPage logout() {
        click(menuButton);
        click(logoutLink);
        return new LoginPage();
    }
}
