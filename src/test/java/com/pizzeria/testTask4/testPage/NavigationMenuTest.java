package com.pizzeria.testTask4.testPage;

import com.pizzeria.testTask4.BaseTest;
import com.pizzeria.testTask4.extensions.ScreenshotOnFailureExtension;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import com.pizzeria.section.NavigationMenu;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import io.qameta.allure.junit5.AllureJunit5;

import static com.pizzeria.utils.ConfigReader.get;

@ExtendWith(ScreenshotOnFailureExtension.class)
@ExtendWith({AllureJunit5.class, ScreenshotOnFailureExtension.class})
@Epic("Главная страница")
@Feature("Навигационное меню")
@DisplayName("Тесты навигации по разделам меню")
public class NavigationMenuTest extends BaseTest {

    NavigationMenu navigationMenu;

    @BeforeEach
    void openMainPage() {
        driver.get(get("url.base"));
        navigationMenu = new NavigationMenu(driver);
    }

    @Test
    @Story("Переход в секцию Пицца")
    @DisplayName("Переход из меню в секцию 'Пицца'")
    @Description("Проверяет, что при выборе пункта 'Пицца' из меню происходит переход на соответствующую страницу")
    void testSwitchingFromMenuToPizza() {
        String expectedUrl = navigationMenu.moveToMenuButton()
                .clickPizzaInMenuButton()
                .getPizzaMenuLink().replace("https://", "").replace("http://", "");

        String actualUrl = driver.getCurrentUrl().replace("https://", "").replace("http://", "");
        Assertions.assertEquals(expectedUrl, actualUrl, "URL страницы не соответствует ожидаемому");

    }

    @Test
    @Story("Переход в секцию Десерт")
    @DisplayName("Переход из меню в секцию 'Десерт'")
    @Description("Проверяет, что при выборе пункта 'Десерт' из меню происходит переход на соответствующую страницу")
    void testSwitchingFromMenuToDesserts() {
        String expectedUrl = navigationMenu.moveToMenuButton()
                .clickDesertInMenuButton()
                .getDesertMenuLink().replace("https://", "").replace("http://", "");

        String actualUrl = driver.getCurrentUrl().replace("https://", "").replace("http://", "");
        Assertions.assertEquals(expectedUrl, actualUrl, "URL страницы не соответствует ожидаемому");
    }

    @Test
    @Story("Переход в секцию Напитки")
    @DisplayName("Переход из меню в секцию 'Напитки'")
    @Description("Проверяет, что при выборе пункта 'Напитки' из меню происходит переход на соответствующую страницу")
    void testSwitchingFromMenuToDrinks() {
        String expectedUrl = navigationMenu.moveToMenuButton()
                .clickDrinkInMenuButton()
                .getDrinkMenuLink().replace("https://", "").replace("http://", "");

        String actualUrl = driver.getCurrentUrl().replace("https://", "").replace("http://", "");
        Assertions.assertEquals(expectedUrl, actualUrl, "URL страницы не соответствует ожидаемому");
    }
}
