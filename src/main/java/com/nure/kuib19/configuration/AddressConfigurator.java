package com.nure.kuib19.configuration;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Optional;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOG_LEVEL;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.SESSION_OVERRIDE;
import static java.lang.String.format;

public class AddressConfigurator {
    private static final Logger logger = LogManager.getRootLogger();
    private static final String ERROR_LOG_LEVEL = "error";
    public static final String TASKKILL_CMD = "taskkill /F /IM node.exe";
    private static AppiumDriverLocalService appiumDriverLocalService;

    private AddressConfigurator() {
    }

    public static AppiumDriverLocalService getAppiumDriverLocalService(int port) {
        if (appiumDriverLocalService == null) {
            startService(port);
        }
        return appiumDriverLocalService;
    }

    public static void startService(int port) {
        makePortFree(port);
        appiumDriverLocalService = new AppiumServiceBuilder()
                .withIPAddress(ConfigurationReader.get().appiumAddress())
                .usingPort(port)
                .withArgument(SESSION_OVERRIDE)
                .withArgument(LOG_LEVEL, ERROR_LOG_LEVEL)
                .build();
        appiumDriverLocalService.start();
        logger.info(format("Appium server was started on %s port", port));
    }

    public static void stopService() {
        Optional.ofNullable(appiumDriverLocalService).ifPresent(service -> {
            service.stop();
            logger.info("Server was stopped");
        });
    }

    private static void makePortFree(int port) {
        if (!isPortFree(port)) {
            try {
                Runtime.getRuntime().exec(TASKKILL_CMD);
            } catch (IOException e) {
                logger.error("Couldn't execute runtime command, message: {}", e);
            }
        }
    }

    private static boolean isPortFree(int port) {
        boolean isFree = true;
        try (ServerSocket ignored = new ServerSocket(port)) {
            logger.info(format("Port '%s' if free", port));
        } catch (Exception e) {
            isFree = false;
            logger.warn(format("This port '%s' is used and will be closed", port));
        }
        return isFree;
    }
}
