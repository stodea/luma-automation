package com.softwaretestingboard.magento;

import base.ExtentManager;
import base.Hooks;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.CatalogPage;
import pageobjects.HomePage;
import pageobjects.ProductPage;

import java.io.IOException;

@Listeners(resources.Listeners.class)

public class OrderPlacementTests extends Hooks {

    public OrderPlacementTests() throws IOException {
        super();
    }

    @Test
    public void checkEmptyCartMessage() throws IOException {
        ExtentManager.log("Starting checkEmptyCartMessage test ...");

        HomePage homepage = new HomePage();
        homepage.waitForPageLoad();
        homepage.getCartBtn().click();
        ExtentManager.pass("Shopping cart was successfully opened");

        String noItemsMessage = "You have no items in your shopping cart.";
        waitForElementText(homepage.getMiniCart(), noItemsMessage);

        checkResult(homepage.getMiniCart().getText().contains(noItemsMessage), "Empty cart message shown");
    }

    @Test
    public void addItemToCart() throws IOException {
        ExtentManager.log("Starting addItemToCart test ...");

        HomePage homepage = new HomePage();
        homepage.getWomenMenuLink().click();
        CatalogPage catalogPage = new CatalogPage();
        catalogPage.getTopsWomenLink().click();
        catalogPage.getProductItems().get(1).click();
        ExtentManager.pass("Reached product page");

        ProductPage productPage = new ProductPage();
        productPage.getSizeBtn().click();
        productPage.getColorBtn().click();
        ExtentManager.pass("Selected size and color");

        productPage.getAddToCartBtn().click();
        waitForElementVisible(productPage.getSuccessMessage());

        checkResult(productPage.getSuccessMessage().isDisplayed(),"Success message shown");
        checkResult(homepage.getCartItemsNumber().getText(), "1", "Item shown in cart");
    }

    @Test
    public void removeItemFromCart() {
        checkResult("x", "y", "Item removed from the cart");
    }

    @Test
    public void checkoutProduct() {
        checkResult("x", "x", "Item checkout");
    }

}
