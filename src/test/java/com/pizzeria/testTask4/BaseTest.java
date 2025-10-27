package com.pizzeria.testTask4;

import com.pizzeria.utils.DriverFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {
    protected static WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeAll
    static void globalSetUp() {
        driver = DriverFactory.createChromeDriver();
        
    }

    @AfterAll
    static void globalTearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterEach
    void resetBrowserState() {
        driver.manage().deleteAllCookies();

        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
        ((JavascriptExecutor) driver).executeScript("window.sessionStorage.clear();");

        driver.navigate().refresh();
        System.out.println("Состояние браузера сброшено после теста");
    }
}

