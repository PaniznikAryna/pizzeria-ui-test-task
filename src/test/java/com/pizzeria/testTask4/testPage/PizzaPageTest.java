package com.pizzeria.testTask4.testPage;

import com.pizzeria.testTask4.BaseTest;
import com.pizzeria.testTask4.extensions.ScreenshotOnFailureExtension;
import com.pizzeria.utils.Constant;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import com.pizzeria.pages.PizzaPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import io.qameta.allure.junit5.AllureJunit5;

import static com.pizzeria.utils.ConfigReader.get;

import java.util.List;

@ExtendWith(ScreenshotOnFailureExtension.class)
@ExtendWith({AllureJunit5.class, ScreenshotOnFailureExtension.class})
@Epic("Секция пицц")
@Feature("Функциональность страницы пицц")
@DisplayName("Тесты для секции с пиццами")
public class PizzaPageTest extends BaseTest {

    PizzaPage pizzaPage;

    @BeforeEach
    void openPizzaPage() {
        driver.get(get("url.pizzaPage"));
        pizzaPage = new PizzaPage(driver);
    }


    @Test
    @Story("Сортировка пицц")
    @DisplayName("Применение сортировки пицц")
    @Description("Проверяет, что после сортировки по цене, список цен отображается в порядке возрастания")
    void testPizzaSorting() {
        pizzaPage.sortBy(Constant.SORT_BY_PRICE);
        List<Integer> prices = pizzaPage.getPizzaPrices();

        for (int i = 1; i < prices.size(); i++) {
            Assertions.assertTrue(prices.get(i) >= prices.get(i - 1), "Цены не отсортированы по возрастанию: " + prices);
        }
    }

    @Test
    @Story("Фильтрация по цене")
    @DisplayName("Проверка фильтрации пицц по диапазону цен")
    @Description("Проверяет, что после применения фильтра по цене, все отображаемые пиццы находятся в заданном диапазоне")
    void testFilteringByPrice() {
        pizzaPage.filterByPrice(30, -40);

        driver.navigate().refresh();
        List<Integer> prices = pizzaPage.getPizzaPrices();

        for (Integer price : prices) {
            Assertions.assertTrue(price >= 440 && price <= 510, "Цена вне диапазона: " + price);
        }
    }

    @Test
    @Story("Добавление пиццы в корзину")
    @DisplayName("Проверка добавления пиццы '4 в 1' в корзину")
    @Description("Проверяет, что при нажатии на кнопку 'В корзину' и переходе к деталям, отображается правильное название пиццы")
    void testAddingPizzaToTheBasket() {
        String currentPizzaName = pizzaPage.clickButtonInBasket()
                .clickButtonMoreDetails()
                .getItemName();

        Assertions.assertEquals("Пицца \"4 в 1\"", currentPizzaName, "Пицца в корзине не соответствует ожидаемой Пиццы \"4 в 1\"");
    }
}
