package com.nure.kuib19.pages;

import com.nure.kuib19.driver.DriverManager;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.String.format;

public class BasePage {
    private static final Logger logger = LogManager.getRootLogger();
    protected static final int TIME_TO_WAIT = 10;

    @AndroidFindBy(xpath = "//android.widget.Button[2]")
    private static MobileElement attentionAcceptButton;

    @AndroidFindBy(id = "toolbarBackText")
    private static MobileElement backButton;

    public BasePage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()), this);
    }

    public static boolean waitElement(MobileElement element, long timeToWait) {
        try {
            new WebDriverWait(DriverManager.getDriver(), timeToWait).until(ExpectedConditions.elementToBeClickable(element));
        } catch (NullPointerException e) {
            logger.error(format("Element '%s' wasn't found", element.getTagName()));
            return false;
        }
        return true;
    }

    public void clickAttentionButton() {
        clickButton(attentionAcceptButton);
    }

    public void clickBackButton() {
        clickButton(backButton);
    }

    public static void clickButton(MobileElement element) {
        if (checkButton(element)) {
            element.click();
        } else {
            logger.error(format("Operation with element '%s' failed", element.getTagName()));
            logger.info(format("Element '%s' was skipped", element.getTagName()));
        }
    }

    public static boolean checkButton(MobileElement element) {
        return waitElement(element, TIME_TO_WAIT);
    }
}