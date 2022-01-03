package com.nure.kuib19.pages;

import com.nure.kuib19.driver.DriverManager;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DeliveryPage extends BasePage {

    @AndroidFindBy(id = "text_input_title")
    private static List<MobileElement> forms;

    @AndroidFindBy(id = "shadow_button")
    private static MobileElement addAddressButton;

    @AndroidFindBy(id = "search_edit")
    private static MobileElement searchAddressField;

    @AndroidFindBy(id = "item_location_name")
    private static List<MobileElement> locations;

    @AndroidFindBy(id = "custom_dialog_button")
    private static MobileElement attentionOKButton;

    @AndroidFindBy(xpath = "//android.widget.Button[normalize-space(@text) = 'SELECT']")
    private static MobileElement selectAddressButton;

    @AndroidFindBy(id = "text_input_title")
    private static List<MobileElement> addressDetailsForms;

    @AndroidFindBy(xpath = "//android.widget.Button[normalize-space(@text) = 'SAVE']")
    private static MobileElement saveAddressButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[normalize-space(@text) = 'Odds from *']")
    private static MobileElement oddsInputForm;

    @AndroidFindBy(id = "send_order")
    private static MobileElement sendOrderButton;

    public static boolean isInputFormsDisplayed() {
        return waitElements(forms, TIME_TO_WAIT);
    }

    public static void inputName(String name) {
        clickButton(forms.get(0));
        DriverManager.getDriver().getKeyboard().sendKeys(name);
        DriverManager.getDriver().hideKeyboard();
    }

    public static void inputNumber(String number) {
        clickButton(forms.get(1));
        DriverManager.getDriver().getKeyboard().sendKeys(number);
        DriverManager.getDriver().hideKeyboard();
    }

    public static void selectAddress(String address) {
        clickButton(forms.get(3));
        waitElement(addAddressButton, TIME_TO_WAIT);
        clickButton(addAddressButton);
        waitElement(searchAddressField, TIME_TO_WAIT);
        clickButton(searchAddressField);
        DriverManager.getDriver().getKeyboard().sendKeys(address + Keys.ENTER);
        DriverManager.getDriver().hideKeyboard();
        waitElements(locations, TIME_TO_WAIT);
        clickButton(locations.get(0));
        waitElement(attentionOKButton, TIME_TO_WAIT);
        clickButton(attentionOKButton);
        waitElement(selectAddressButton, TIME_TO_WAIT);
        clickButton(selectAddressButton);
        inputAddressData();
    }

    private static void inputAddressData() {
        waitElements(addressDetailsForms, TIME_TO_WAIT);
        addressDetailsForms.forEach(f -> {
            clickButton(f);
            DriverManager.getDriver().getKeyboard().sendKeys("1");
            DriverManager.getDriver().hideKeyboard();
        });
        waitElement(saveAddressButton, TIME_TO_WAIT);
        clickButton(saveAddressButton);
        new WebDriverWait(DriverManager.getDriver(), TIME_ATTENTION_BUTTON);
        DriverManager.getDriver().navigate().back();
    }

    public static void selectOdds(String odds) {
        waitElement(oddsInputForm, TIME_TO_WAIT);
        clickButton(oddsInputForm);
        DriverManager.getDriver().getKeyboard().sendKeys(odds);
        DriverManager.getDriver().hideKeyboard();
    }

    public static boolean isSendOrderButtonDisplayed() {
        return waitElement(sendOrderButton, TIME_TO_WAIT);
    }

    public static void clickSendOrderButton() {
        clickButton(sendOrderButton);
        waitElement(attentionOKButton, TIME_TO_WAIT);
        clickButton(attentionOKButton);
    }
}