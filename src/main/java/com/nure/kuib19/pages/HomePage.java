package com.nure.kuib19.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.List;

public class HomePage extends BasePage {

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView")
    private static List<MobileElement> cities;

    @AndroidFindBy(id = "custom_dialog_button")
    private static MobileElement attentionContinueButton;

    public boolean checkCityButton() {
        return checkButton(cities.get(0));
    }

    public HomePage clickCity(String cityName) {
        cities.stream().filter(city -> city.getText().equals(cityName)).findFirst().ifPresent(RemoteWebElement::click);
        return this;
    }

    public boolean checkAttentionContinueButton() {
        return checkButton(attentionContinueButton);
    }

    public HomePage clickAttentionContinueButton() {
        clickButton(attentionContinueButton);
        return this;
    }
}