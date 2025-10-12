package org.example.test_task_4;

import org.example.pages.NavigationMenu;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class NavigationMenuTest {

    WebDriver driver;
    NavigationMenu navigationMenu;

    @BeforeEach
    void setUp(){
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://pizzeria.skillbox.cc/");
        navigationMenu = new NavigationMenu(driver);
    }

    @Test
    void testSwitchingFromMenuToPizza(){
        navigationMenu.moveToMenuButton();
        navigationMenu.clickPizzaInMenuButton();

        String expectedUrl = navigationMenu.getPizzaMenuLink().replace("https://", "").replace("http://", "");
        String actualUrl = driver.getCurrentUrl().replace("https://", "").replace("http://", "");
        Assertions.assertEquals(expectedUrl, actualUrl, "URL страницы не соответствует ожидаемому");

    }

    @Test
    void testSwitchingFromMenuToDesserts(){
        navigationMenu.moveToMenuButton();
        navigationMenu.clickDesertInMenuButton();

        String expectedUrl = navigationMenu.getDesertMenuLink().replace("https://", "").replace("http://", "");
        String actualUrl = driver.getCurrentUrl().replace("https://", "").replace("http://", "");
        Assertions.assertEquals(expectedUrl, actualUrl, "URL страницы не соответствует ожидаемому");
    }

    @Test
    void testSwitchingFromMenuToDrinks(){
        navigationMenu.moveToMenuButton();
        navigationMenu.clickDrinkInMenuButton();

        String expectedUrl = navigationMenu.getDrinkMenuLink().replace("https://", "").replace("http://", "");
        String actualUrl = driver.getCurrentUrl().replace("https://", "").replace("http://", "");
        Assertions.assertEquals(expectedUrl, actualUrl, "URL страницы не соответствует ожидаемому");

    }

    @AfterEach
    void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
}
