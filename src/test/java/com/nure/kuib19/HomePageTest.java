package com.nure.kuib19;

import static com.nure.kuib19.pages.BasePage.*;
import static com.nure.kuib19.pages.HomePage.*;
import static com.nure.kuib19.configuration.StringConstants.*;

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

    @Test(description = "Check all types of delivery")
    public void checkTypesOfDelivery() {
        softAssert.assertTrue(isPickupPageButtonDisplayed(), "PickUp button wasn't displayed");
        clickPickupPageButton();
        softAssert.assertTrue(isPickupAddressesDisplayed());
        selectPickupAddress(PICKUP_ADDRESS_1);
    }
}