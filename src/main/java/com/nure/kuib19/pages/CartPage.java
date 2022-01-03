package com.nure.kuib19.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CartPage extends BasePage {

    @AndroidFindBy(id = "basket_item_cost")
    private static MobileElement price;

    @AndroidFindBy(id = "basket_go_to_personal_info")
    private static MobileElement confirmOrderButton;

    public static boolean isPriceDisplayed() {
        return waitElement(price, TIME_TO_WAIT);
    }

    public static String getTotalPrice() {
        return price.getText();
    }

    public static boolean isConfirmOrderButtonDisplayed() {
        return waitElement(confirmOrderButton, TIME_TO_WAIT);
    }

    public static DeliveryPage clickConfirmOrderButton() {
        clickButton(confirmOrderButton);
        return new DeliveryPage();
    }
}
