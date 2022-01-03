package com.nure.kuib19.pages;

import com.nure.kuib19.configuration.StringConstants;
import com.nure.kuib19.driver.DriverManager;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

public class BasePage {
    protected static final Logger logger = LogManager.getRootLogger();
    protected static final int TIME_TO_WAIT = 10;
    protected static final int TIME_ATTENTION_BUTTON = 3;
    protected static final Actions actions = new Actions(DriverManager.getDriver());

    @AndroidFindBy(id = "toolbarTitle")
    private static MobileElement pageTitle;

    @AndroidFindBy(id = "toolbarBurgerIcon")
    private static MobileElement moreMenuButton;

    @AndroidFindBy(xpath = "title")
    private static List<MobileElement> topMenuButtons;

    public BasePage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()), this);
    }

    public void skipAllAttentions() {
        boolean flag = true;
        while (isAttentionPresent()) {
            try {
                waitAttentionButton();
                waitAttentionButton();
                DriverManager.getDriver()
                        .findElement(By.xpath(String.format("//android.widget.Button[normalize-space(@text) = '%s']", StringConstants.CONTINUE)))
                        .click();
                waitAttentionButton();
                DriverManager.getDriver()
                        .findElement(By.xpath(String.format("//android.widget.Button[normalize-space(@text) = '%s']", StringConstants.ACCEPT)))
                        .click();
                if (isAttentionPresent() && flag) {
                    DriverManager.getDriver().navigate().back();
                    waitAttentionButton();
                    if (isAttentionPresent()) {
                        DriverManager.getDriver().navigate().back();
                    } else {
                        flag = false;
                    }
                }
            } catch (Exception ignored) {
            }
        }
    }

    private void waitAttentionButton() {
        DriverManager.getDriver().manage().timeouts().implicitlyWait(TIME_ATTENTION_BUTTON, TimeUnit.SECONDS);
    }

    public static boolean isAttentionPresent() {
        DriverManager.getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        try {
            new WebDriverWait(DriverManager.getDriver(), TIME_TO_WAIT).until(ExpectedConditions
                    .visibilityOfElementLocated(By.xpath("//android.widget.TextView[normalize-space(@text) = 'Attention']")));
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
            logger.info("No more attention title");
            return false;
        }
    }

    public static void clickButton(MobileElement element) {
        try {
            element.click();
        } catch (Exception e) {
            logger.error(format("Operation with element '%s' failed", element.getTagName()));
            logger.info(format("Element '%s' was skipped", element.getTagName()));
        }
    }

    public static boolean isPageDisplayed() {
        return pageTitle.isDisplayed();
    }

    public static String getPageTitleText() {
        return pageTitle.getText();
    }

    public static boolean isMenuButtonDisplayed() {
        return moreMenuButton.isDisplayed();
    }

    public static void clickMenuButton() {
        moreMenuButton.click();
    }

    public static boolean isTopMenuButtonsDisplayed() {
        return waitElements("//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ImageView",
                TIME_TO_WAIT);
    }

    public static void selectMenuButton(String menuTittle) {
        try {
            topMenuButtons.stream().filter(t -> t.getText().contains(menuTittle)).findAny().orElseThrow().click();
        } catch (Exception e) {
            logger.warn(format("There is no '%s' in menu", menuTittle));
        }
    }

    public static boolean waitElement(String locator, int timeToWait) {
        try {
            new WebDriverWait(DriverManager.getDriver(), timeToWait)
                    .until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean waitElement(MobileElement element, int timeToWait) {
        try {
            new WebDriverWait(DriverManager.getDriver(), timeToWait)
                    .until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean waitElements(String locator, int timeToWait) {
        return !new WebDriverWait(DriverManager.getDriver(), timeToWait)
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(By
                        .xpath(locator), 0)).isEmpty();
    }

    public static boolean waitElements(List<MobileElement> elements, int timeToWait) {
        try {
            new WebDriverWait(DriverManager.getDriver(), timeToWait).until(ExpectedConditions.visibilityOf(pageTitle));
        } catch (Exception ignored) {
        }
        return !elements.isEmpty();
    }
}