package com.nure.kuib19;

import static com.nure.kuib19.pages.BasePage.*;
import static com.nure.kuib19.pages.HomePage.*;
import static com.nure.kuib19.pages.CartPage.*;
import static com.nure.kuib19.pages.DeliveryPage.*;
import static com.nure.kuib19.configuration.StringConstants.*;

import com.nure.kuib19.pages.*;
import org.testng.annotations.Test;

import java.util.Locale;

public class HomePageTest extends BaseTest {

    @Test(description = "Checking the main HomePage elements")
    public void checkStartMenu() {
        softAssert.assertTrue(isPageDisplayed(), "There is no displayed page");
        softAssert.assertEquals(getPageTitleText().toUpperCase(Locale.ROOT), MENU, "Incorrect title");
        softAssert.assertTrue(isMenuButtonDisplayed(), "Menu button wasn't displayed");
        clickMenuButton();
        clickMenuButton();
        softAssert.assertTrue(isBannersDisplayed(), "There is no banners");
        softAssert.assertTrue(isTopMenuButtonsDisplayed(), "There is no top menu button");
        softAssert.assertTrue(isPickupPageButtonDisplayed(), "PickUp button wasn't displayed");
        softAssert.assertTrue(isDeliveryPageButtonDisplayed(), "There is no delivery page button");
        softAssert.assertAll();
    }

    @Test(description = "Trying to place an order")
    public void checkPlaceOrder() {
        HomePage homePage = new HomePage();
        softAssert.assertTrue(isPickupPageButtonDisplayed(), "PickUp button wasn't displayed");
        clickPickupPageButton();
        softAssert.assertTrue(isPickupAddressesDisplayed(), "There is no pickup addresses");
        selectPickupAddress(PICKUP_ADDRESS_1);
        softAssert.assertTrue(isMenuCategoriesDisplayed(), "There is no menu categories");
        selectMenuCategory(MENU_CATEGORY_1);
        selectProduct();
        addCountOfProduct();
        CartPage cartPage = addToCart();
        softAssert.assertTrue(isPriceDisplayed(), "Price isn't displayed");
        softAssert.assertEquals(PRODUCT_TOTAL_PRICE, getTotalPrice(), "Incorrect price");
        softAssert.assertTrue(isConfirmOrderButtonDisplayed(), "There is no confirm button");
        DeliveryPage deliveryPage = clickConfirmOrderButton();
        softAssert.assertTrue(isInputFormsDisplayed(), "Input form isn't displayed");
        inputName(NAME);
        inputNumber(NUMBER);
        selectOdds(ODDS);
        softAssert.assertTrue(isSendOrderButtonDisplayed(), "There is no send order button");
        clickSendOrderButton();
        softAssert.assertAll();
    }


    @Test(description = "User can remove products from basket")
    public void checkRemoveProduct() {
        HomePage homePage = new HomePage();
        selectMenuCategory(MENU_CATEGORY_2);
        selectProduct();
        addCountOfProduct();
        CartPage cartPage = addToCart();
        softAssert.assertTrue(isPriceDisplayed(), "Price isn't displayed");
        softAssert.assertEquals(PRODUCT_TOTAL_PRICE_2, getTotalPrice(), "Incorrect price");
        clickMenuButton();
        softAssert.assertTrue(isMenuButtonDisplayed(), "There is no menu button");
        selectMenuButton(MENU);
        clickMenuButton();
        selectMenuButton(CART);
        softAssert.assertTrue(isDeleteFromCartButtonDisplayed(), "There is no delete from cart button");
        clickDeleteFromCartButton();
        softAssert.assertTrue(isCartEmpty(), "Cart isn't empty");
        softAssert.assertAll();
    }
}