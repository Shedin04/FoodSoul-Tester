package com.nure.kuib19;

import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void checkStartMenu() {
        String menuItem = "POKE L";
        String productTitle = "Poke L Збери сам";
        softAssert.assertTrue(homePage.checkMenuItem(menuItem));
        homePage.clickMenuItem(menuItem);
        softAssert.assertTrue(homePage.isTitlePresent(productTitle));
        softAssert.assertAll();
    }

    @Test(description = "Check types of delivery")
    public void checkTypesOfDelivery() {
        String firstNameOfDelivery = "Delivery";
        String secondNameOfDelivery = "Pickup";
        String pickupAddress = "PokeWay_Соборная";
        softAssert.assertTrue(homePage.checkTextTypeOfDeliveryButton(secondNameOfDelivery));
        homePage.clickTypeOfDeliveryPassiveButton();
        basePage.clickBackButton();
        softAssert.assertTrue(homePage.checkTextTypeOfDeliveryButton(secondNameOfDelivery));
        homePage.clickTypeOfDeliveryPassiveButton();
        homePage.selectPickUpAddress(pickupAddress);
        softAssert.assertTrue(homePage.getSelectedAddressText().contains(secondNameOfDelivery));
        softAssert.assertTrue(homePage.checkTextTypeOfDeliveryButton(firstNameOfDelivery));
        homePage.selectPickUpAddress(firstNameOfDelivery);
        softAssert.assertAll();
    }
}