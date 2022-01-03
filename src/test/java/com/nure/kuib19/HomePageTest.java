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
        softAssert.assertTrue(isPriceDisplayed());
        softAssert.assertEquals(PRODUCT_TOTAL_PRICE, getTotalPrice(), "Incorrect price");
        softAssert.assertTrue(isConfirmOrderButtonDisplayed());
        DeliveryPage deliveryPage = clickConfirmOrderButton();
        softAssert.assertTrue(isInputFormsDisplayed());
        inputName(NAME);
        inputNumber(NUMBER);
        selectOdds(ODDS);
        softAssert.assertTrue(isSendOrderButtonDisplayed());
        clickSendOrderButton();
        softAssert.assertAll();
    }
}