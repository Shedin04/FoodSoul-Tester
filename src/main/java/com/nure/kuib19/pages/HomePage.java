package com.nure.kuib19.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.List;
import java.util.Locale;

public class HomePage extends BasePage {

    /**
     * Starting elements
     */

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView")
    private static List<MobileElement> cities;

    @AndroidFindBy(id = "custom_dialog_button")
    private static MobileElement attentionContinueButton;

    @AndroidFindBy(id = "custom_dialog_message")
    private static MobileElement attentionText;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout/android.widget.LinearLayout[2]/android.widget.Button")
    private static MobileElement attentionOkButton;

    @AndroidFindBy(xpath = "//android.widget.RelativeLayout/android.widget.TextView[1]")
    private static List<MobileElement> productTitles;

    /**
     * Main menu elements
     */

    @AndroidFindBy(xpath = "//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView")
    private static List<MobileElement> mainMenuItems;

    @AndroidFindBy(id = "pd_button_pickup")
    private static MobileElement typeOfDeliveryButton;

    @AndroidFindBy(id = "item_location_name")
    private static List<MobileElement> pickUpAddresses;

    @AndroidFindBy(id = "pd_options_title")
    private static MobileElement selectedAddressText;

    public boolean checkCityButton() {
        return checkButton(cities.get(0));
    }

    public void clickCity(String cityName) {
        cities.stream().filter(city -> city.getText().equals(cityName)).findFirst().ifPresent(RemoteWebElement::click);
    }

    public boolean checkAttentionContinueButton() {
        return checkButton(attentionContinueButton);
    }

    public void clickAttentionContinueButton() {
        clickButton(attentionContinueButton);
    }

    public boolean checkAttentionText(String text) {
        waitElement(attentionText, TIME_TO_WAIT);
        return attentionText.getText().equals(text);
    }

    public void clickAttentionOkButton() {
        waitElement(attentionOkButton, TIME_TO_WAIT);
        clickButton(attentionOkButton);
    }

    public boolean checkMenuItem(String itemName) {
        return mainMenuItems.stream().anyMatch(item -> item.getText()
                .equals(itemName.toUpperCase(Locale.ROOT)));
    }

    public void clickMenuItem(String itemName) {
        mainMenuItems.stream().filter(item -> item.getText()
                .equals(itemName.toUpperCase(Locale.ROOT))).findFirst().ifPresent(RemoteWebElement::click);
    }

    public boolean isTitlePresent(String productTitle) {
        return productTitles.stream().anyMatch(title -> title.getText()
                .equals(productTitle));
    }

    public boolean checkTextTypeOfDeliveryButton(String typeOfDelivery) {
        waitElement(typeOfDeliveryButton, TIME_TO_WAIT);
        return typeOfDeliveryButton.getText().equals(typeOfDelivery);
    }

    public void clickTypeOfDeliveryPassiveButton() {
        waitElement(typeOfDeliveryButton, TIME_TO_WAIT);
        clickButton(typeOfDeliveryButton);
    }

    public void selectPickUpAddress(String pickupAddress) {
        pickUpAddresses.stream().filter(adr -> adr.getText().equals(pickupAddress))
                .findFirst()
                .ifPresent(RemoteWebElement::click);
    }

    public String getSelectedAddressText() {
        return selectedAddressText.getText();
    }
}