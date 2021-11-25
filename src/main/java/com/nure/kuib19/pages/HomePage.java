package com.nure.kuib19.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HomePage extends BasePage {

    @AndroidFindBy(id = "00000000-0000-0019-ffff-ffff00000092")
    private static MobileElement city;

    public static void clickCity() {
        waitElement(city, 15);
        city.click();
    }
}