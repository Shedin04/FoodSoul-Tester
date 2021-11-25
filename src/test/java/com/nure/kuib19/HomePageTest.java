package com.nure.kuib19;

import org.testng.annotations.Test;

public class HomePageTest extends BaseTest{

    @Test
    public void test(){
        String cityName = "Николаев";
        softAssert.assertTrue(homePage.checkCityButton());
        softAssert.assertNotNull(homePage.clickCity(cityName));
        homePage.checkAttentionContinueButton();
        homePage.clickAttentionContinueButton();
        softAssert.assertAll();
    }
}