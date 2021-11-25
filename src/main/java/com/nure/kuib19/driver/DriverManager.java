package com.nure.kuib19.driver;

import com.nure.kuib19.configuration.AddressConfigurator;
import com.nure.kuib19.configuration.CapabilitiesConfigurator;
import com.nure.kuib19.configuration.ConfigurationReader;
import com.nure.kuib19.configuration.EnvironmentType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.module.Configuration;
import java.util.Locale;
import java.util.Optional;

import static java.lang.String.format;

public class DriverManager {
    private static final Logger logger = LogManager.getRootLogger();
    private static final EnvironmentType ENVIRONMENT_TYPE = EnvironmentType
            .valueOf(ConfigurationReader.get().evn().toUpperCase(Locale.ROOT));
    private static AppiumDriver<MobileElement> driver;

    private DriverManager() {

    }

    /**
     * @return - This returns driver
     */

    public static AppiumDriver<MobileElement> getDriver() {
        if (driver == null) {
            driver = createDriver();
        }
        return driver;
    }

    /**
     * @return - This creates driver depending on evnType
     */

    private static AppiumDriver<MobileElement> createDriver() {
        if (ENVIRONMENT_TYPE == EnvironmentType.LOCAL) {
            driver = new AndroidDriver<>(
                    AddressConfigurator.getAppiumDriverLocalService(ConfigurationReader.get().appiumPort()),
                    CapabilitiesConfigurator.getLocalCapabilities());
        } else {
            logger.error(format("Unexpected env type value: %s", ENVIRONMENT_TYPE));
            throw new IllegalArgumentException();
        }
        logger.info("Driver was created");
        logger.info(format("Environment type is: %s", ENVIRONMENT_TYPE));
        return driver;
    }

    /**
     * This method closes driver
     */

    public static void closeDriver() {
        Optional.ofNullable(getDriver()).ifPresent(driverInstance -> {
            driverInstance.quit();
            driver = null;
            logger.info("Driver was closed");
        });
    }

    /**
     * This method closes the emulator
     */

    public static void closeEmulator() {
        try {
            Runtime.getRuntime().exec(format("adb -s %s emu kill", ConfigurationReader.get().udid()));
            logger.info("Emulator was closed");
        } catch (IOException e) {
            logger.warn("Emulator wasn't closed. Message: {}", e);
        }
    }

    public static void closeAppium() {
        AddressConfigurator.stopService();
    }
}