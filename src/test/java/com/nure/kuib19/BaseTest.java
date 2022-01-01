package com.nure.kuib19;

import com.nure.kuib19.driver.DriverManager;
import com.nure.kuib19.pages.*;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class BaseTest {
    SoftAssert softAssert = new SoftAssert();
    protected final BasePage basePage = new BasePage();

    @BeforeClass
    public void createSession() {
        DriverManager.getDriver();
        basePage.skipAllAttentions();
    }

    /**
     * This resets app after each test
     */

    @AfterMethod
    public void resetApp() {
//        DriverManager.getDriver().resetApp();
    }

    @AfterClass
    public void closeSession() {
        DriverManager.closeDriver();
        DriverManager.closeAppium();
//        DriverManager.closeEmulator();
    }
}