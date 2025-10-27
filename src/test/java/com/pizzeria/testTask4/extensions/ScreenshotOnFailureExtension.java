package com.pizzeria.testTask4.extensions;

import com.pizzeria.testTask4.BaseTest;
import com.pizzeria.utils.ScreenshotUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

public class ScreenshotOnFailureExtension implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        Object testInstance = context.getRequiredTestInstance();
        if (testInstance instanceof BaseTest baseTest && baseTest.getDriver() != null) {
            ScreenshotUtils.attachScreenshot(baseTest.getDriver(), "Скриншот при падении");
            System.out.println("Скриншот прикреплён при ошибке: " + throwable.getMessage());
        }
        throw throwable;
    }
}

