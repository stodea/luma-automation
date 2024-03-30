package com.softwaretestingboard.magento;

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
    public void addItemToCart() throws IOException {
        HomePage homepage = new HomePage();
        homepage.getWomenMenuLink().click();

        CatalogPage catalogPage = new CatalogPage();
        catalogPage.getTopsWomenLink().click();
        catalogPage.getProductItems().get(1).click();

        ProductPage productPage = new ProductPage();
        productPage.getSizeBtn().click();
        productPage.getColorBtn().click();
        productPage.getAddToCartBtn().click();
        waitForElementVisible(productPage.getSuccessMessage());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(productPage.getSuccessMessage().isDisplayed());
        softAssert.assertEquals(homepage.getCartItemsNumber().getText(), "1");
        softAssert.assertAll();

    }

    @Test
    public void checkEmptyCartMessage() throws IOException {
        HomePage homepage = new HomePage();
        homepage.waitForPageLoad();
        homepage.getCartBtn().click();

        String message = "You have no items in your shopping cart.";
        waitForElementText(homepage.getMiniCart(), message);
        Assert.assertTrue(homepage.getMiniCart().getText().contains(message));
    }

    @Test
    public void removeItemFromShoppingCart() {
        Assert.fail();
    }

}
