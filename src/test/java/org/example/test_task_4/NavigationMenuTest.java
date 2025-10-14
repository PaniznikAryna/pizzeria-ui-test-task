package org.example.test_task_4;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.pages.NavigationMenu;
import org.example.utils.DriverFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Epic("Главная страница")
@Feature("Навигационное меню")
@DisplayName("Тесты навигации по разделам меню")
public class NavigationMenuTest {

    WebDriver driver;
    NavigationMenu navigationMenu;

    @BeforeEach
    void setUp(){
//        ChromeOptions chromeOptions = new ChromeOptions();
//        driver = new ChromeDriver(chromeOptions);
        driver = DriverFactory.createChromeDriver();
        driver.get("https://pizzeria.skillbox.cc/");
        navigationMenu = new NavigationMenu(driver);
    }

    @Test
    @Story("Переход в секцию Пицца")
    @DisplayName("Переход из меню в секцию 'Пицца'")
    @Description("Проверяет, что при выборе пункта 'Пицца' из меню происходит переход на соответствующую страницу")
    void testSwitchingFromMenuToPizza(){
        navigationMenu.moveToMenuButton();
        navigationMenu.clickPizzaInMenuButton();

        String expectedUrl = navigationMenu.getPizzaMenuLink().replace("https://", "").replace("http://", "");
        String actualUrl = driver.getCurrentUrl().replace("https://", "").replace("http://", "");
        Assertions.assertEquals(expectedUrl, actualUrl, "URL страницы не соответствует ожидаемому");

    }

    @Test
    @Story("Переход в секцию Десерт")
    @DisplayName("Переход из меню в секцию 'Десерт'")
    @Description("Проверяет, что при выборе пункта 'Десерт' из меню происходит переход на соответствующую страницу")
    void testSwitchingFromMenuToDesserts(){
        navigationMenu.moveToMenuButton();
        navigationMenu.clickDesertInMenuButton();

        String expectedUrl = navigationMenu.getDesertMenuLink().replace("https://", "").replace("http://", "");
        String actualUrl = driver.getCurrentUrl().replace("https://", "").replace("http://", "");
        Assertions.assertEquals(expectedUrl, actualUrl, "URL страницы не соответствует ожидаемому");
    }

    @Test
    @Story("Переход в секцию Напитки")
    @DisplayName("Переход из меню в секцию 'Напитки'")
    @Description("Проверяет, что при выборе пункта 'Напитки' из меню происходит переход на соответствующую страницу")
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
