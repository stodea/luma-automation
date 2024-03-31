package com.softwaretestingboard.magento;

import base.ExtentManager;
import base.Hooks;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.*;

import java.io.IOException;

@Listeners(resources.Listeners.class)

public class OrderPlacementTests extends Hooks {

    public OrderPlacementTests() throws IOException {
        super();
    }

    @Test
    public void checkEmptyCartMessage() throws IOException {
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
    public void removeItemFromCart() throws IOException {
        addItemToCart();

        HomePage homepage = new HomePage();
        homepage.getCartBtn().click();
        ExtentManager.pass("Shopping cart was successfully opened");

        homepage.getRemoveItemButton().click();
        ModalPage modalPage = new ModalPage();
        modalPage.getOkBtn().click();
        ExtentManager.pass("Confirm remove Ok button was successfully clicked");

        String noItemsMessage = "You have no items in your shopping cart.";
        waitForElementText(homepage.getMiniCart(), noItemsMessage);
        checkResult(homepage.getMiniCart().getText().contains(noItemsMessage), "Item removed from cart");
    }

    @Test
    public void checkoutProduct() throws IOException {
        addItemToCart();

        HomePage homepage = new HomePage();
        homepage.getCartBtn().click();
        homepage.getMiniCartViewCartLink().click();
        CheckoutPage checkoutPage = new CheckoutPage();
        waitForElementVisible(checkoutPage.getOrderTotal());
        ExtentManager.pass("Checkout cart page was reached");

        checkoutPage.getProceedToCheckoutBtn().click();
        waitForElementVisible(checkoutPage.getLoadingIcon());
        waitForElementInvisible(checkoutPage.getLoadingIcon());
        waitForElementVisible(checkoutPage.getNextBtn());
        ExtentManager.pass("Checkout shipping page was reached");

        checkoutPage.getEmailField().sendKeys("test@test.com");
        checkoutPage.getFirstNameField().sendKeys("firstname");
        checkoutPage.getLastNameField().sendKeys("lastname");
        checkoutPage.getStreetAddressField().sendKeys("address");
        checkoutPage.getCityField().sendKeys("city");
        Select country = new Select(checkoutPage.getCountryDropdown());
        country.selectByVisibleText("Romania");
        checkoutPage.waitForPageLoad();
        Select state = new Select(checkoutPage.getStateDropdown());
        state.selectByVisibleText("Alba");
        checkoutPage.getZipField().sendKeys("5555");
        checkoutPage.getPhoneField().sendKeys("555");
        checkoutPage.waitForPageLoad();
        waitForElementToBeClickable(checkoutPage.getNextBtn());
        clickElement(checkoutPage.getNextBtn());
        checkoutPage.waitForPageLoad();
        waitForElementToBeClickable(checkoutPage.getPlaceOrderBtn());
        ExtentManager.pass("Checkout payment page was reached");

        checkoutPage.getPlaceOrderBtn().click();
        checkoutPage.waitForPageLoad();
        String purchaseMessage = "Thank you for your purchase!";
        waitForElementText(checkoutPage.getPurchaseMessage(), purchaseMessage);
        checkResult(checkoutPage.getPurchaseMessage().getText(), purchaseMessage, "Order placed for item");
    }

}
