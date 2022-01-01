package com.nure.kuib19.pages;

import com.nure.kuib19.driver.DriverManager;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.lang.String.format;

public class HomePage extends BasePage {

    @AndroidFindBy(id = "//android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.TextView[1]")
    private static MobileElement deliveryPageButton;

    @AndroidFindBy(id = "pd_button_pickup")
    private static MobileElement pickupPageButton;

    @AndroidFindBy(id = "item_location_name")
    private static List<MobileElement> pickupAddresses;

    public static boolean isBannersDisplayed() {
        return waitElements("//android.widget.FrameLayout/android.widget.ImageView", TIME_TO_WAIT);
    }

    public static boolean isDeliveryPageButtonDisplayed() {
        return waitElement("//android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.TextView[1]",
                TIME_TO_WAIT);
    }

    public static void clickDeliveryPageButton() {
        deliveryPageButton.click();
    }

    public static boolean isPickupPageButtonDisplayed() {
        return waitElement("//android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.TextView[2]",
                TIME_TO_WAIT);
    }

    public static void clickPickupPageButton() {
        pickupPageButton.click();
    }

    public static boolean isPickupAddressesDisplayed() {
        return waitElements("//item_location_name", TIME_TO_WAIT);
    }

    public static void selectPickupAddress(String address) {
        try {
            pickupAddresses.stream().filter(a -> a.getText().contains(address)).findAny().orElseThrow().click();
        } catch (NoSuchElementException e) {
            logger.warn(format("There is no '%s' address", address));
        }
    }
}