package com.nure.kuib19;

import com.nure.kuib19.driver.DriverManager;
import com.nure.kuib19.pages.BasePage;
import com.nure.kuib19.pages.HomePage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

public class BaseTest {
    SoftAssert softAssert = new SoftAssert();
    protected final BasePage basePage = new BasePage();
    protected final HomePage homePage = new HomePage();

    @BeforeClass
    public void createSession() {
        DriverManager.getDriver();
        basePage.clickAttentionButton();
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