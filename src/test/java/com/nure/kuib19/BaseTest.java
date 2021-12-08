package com.nure.kuib19;

import com.nure.kuib19.driver.DriverManager;
import com.nure.kuib19.pages.BasePage;
import com.nure.kuib19.pages.HomePage;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class BaseTest {
    private static final String cityName = "Николаев";
    private static final String attentionText = "Зміни в умовах доставки! Вартість доставки складає 15грн в межах 1 км, за кожний наступний км+10грн. Кінцеву вартість доставки уточнюйте у оператора";


    SoftAssert softAssert = new SoftAssert();
    protected final BasePage basePage = new BasePage();
    protected final HomePage homePage = new HomePage();

    @BeforeClass
    public void createSession() {
        DriverManager.getDriver();
        basePage.clickAttentionButton();
        softAssert.assertTrue(homePage.checkCityButton());
        homePage.clickCity(cityName);
        softAssert.assertTrue(homePage.checkAttentionContinueButton());
        homePage.clickAttentionContinueButton();
        softAssert.assertTrue(homePage.checkAttentionText(attentionText));
        homePage.clickAttentionOkButton();
        softAssert.assertAll();
    }

    /**
     * This resets app after each test
     */

    @AfterMethod
    public void resetApp() {
        DriverManager.getDriver().resetApp();
    }

    @AfterClass
    public void closeSession() {
        DriverManager.closeDriver();
        DriverManager.closeAppium();
        DriverManager.closeEmulator();
    }
}