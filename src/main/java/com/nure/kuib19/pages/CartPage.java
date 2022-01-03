package com.nure.kuib19.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CartPage extends BasePage {

    @AndroidFindBy(id = "basket_item_cost")
    private static MobileElement price;

    @AndroidFindBy(id = "basket_go_to_personal_info")
    private static MobileElement confirmOrderButton;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ImageView")
    private static MobileElement deleteFromCartButton;

    @AndroidFindBy(xpath = "//android.widget.Button[normalize-space(@text) = 'OK']")
    private static MobileElement attentionOKButton;

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

    public static boolean isDeleteFromCartButtonDisplayed() {

        return waitElement(deleteFromCartButton, TIME_TO_WAIT);
    }

    public static void clickDeleteFromCartButton() {
        clickButton(deleteFromCartButton);
        waitElement(attentionOKButton, TIME_TO_WAIT);
        clickButton(attentionOKButton);
    }

    public static boolean isCartEmpty() {
        return waitElement("//android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.TextView",
                TIME_TO_WAIT);
    }
}