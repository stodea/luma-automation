package com.softwaretestingboard.magento;

import base.ExtentManager;
import base.Hooks;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.*;
import resources.ExcelDataProviders;

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
        checkResult(productPage.getSuccessMessage().isDisplayed(), "Success message shown");
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

    @Test(dataProvider = "dataForOrderProduct", dataProviderClass = ExcelDataProviders.class)
    public void orderProduct(String menu, String category, String productName, String size, String color,
                             String quantity, String email, String firstname, String lastname, String address,
                             String city, String country, String state, String zip, String phone,
                             String totalPrice) throws IOException {
        ExtentManager.log("Starting order test for product: " + productName + " ...");
        HomePage homepage = new HomePage();
        homepage.getLink(menu).click();
        homepage.getLink(category).click();
        CatalogPage catalogPage = new CatalogPage();
        catalogPage.getLink(productName).click();
        ExtentManager.pass("Reached " + productName + " product page");

        ProductPage productPage = new ProductPage();
        productPage.getOption(size).click();
        productPage.getOption(color).click();
        ExtentManager.pass("Selected " + size + " size and " + color + " color");

        productPage.getQuantityInput().clear();
        productPage.getQuantityInput().sendKeys(quantity);
        ExtentManager.pass("Quantity set to " + quantity);

        productPage.getAddToCartBtn().click();
        waitForElementVisible(productPage.getSuccessMessage());
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

        checkoutPage.getEmailField().sendKeys(email);
        checkoutPage.getFirstNameField().sendKeys(firstname);
        checkoutPage.getLastNameField().sendKeys(lastname);
        checkoutPage.getStreetAddressField().sendKeys(address);
        checkoutPage.getCityField().sendKeys(city);
        Select countrySelect = new Select(checkoutPage.getCountryDropdown());
        countrySelect.selectByVisibleText(country);
        checkoutPage.waitForPageLoad();
        Select stateSelect = new Select(checkoutPage.getStateDropdown());
        stateSelect.selectByVisibleText(state);
        checkoutPage.waitForPageLoad();
        checkoutPage.getZipField().sendKeys(zip);
        checkoutPage.getPhoneField().sendKeys(phone);
        checkoutPage.waitForPageLoad();
        waitForElementToBeClickable(checkoutPage.getNextBtn());
        clickElement(checkoutPage.getNextBtn());
        checkoutPage.waitForPageLoad();
        waitForElementToBeClickable(checkoutPage.getPlaceOrderBtn());
        ExtentManager.pass("Checkout payment page was reached");

        waitForElementText(checkoutPage.getOrderTotal(), totalPrice);
        waitForElementText(checkoutPage.getItemsInCart(), quantity);
        String actualPrice = checkoutPage.getTotalPrice().getText();
        checkResult(actualPrice, totalPrice, "Total price is: " + actualPrice + " expected: " + totalPrice);
        String actualItemsNoInCart = checkoutPage.getItemsInCart().getText();
        checkResult(actualItemsNoInCart, quantity, "Itesm number in cart: " + actualItemsNoInCart +
                " expected: " + quantity);

        checkoutPage.getPlaceOrderBtn().click();
        checkoutPage.waitForPageLoad();
        String purchaseMessage = "Thank you for your purchase!";
        waitForElementText(checkoutPage.getPurchaseMessage(), purchaseMessage);
        checkResult(checkoutPage.getPurchaseMessage().getText(), purchaseMessage, "Order placed for item");
        ExtentManager.log("Order test for product: " + productName + " finished.");
    }

}
