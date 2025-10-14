package org.example.test_task_4;

import org.example.pages.PizzaSection;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class PizzaSectionTest {

    WebDriver driver;
    PizzaSection pizzaSection;

    @BeforeEach
    void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://pizzeria.skillbox.cc/product-category/menu/pizza/");
        pizzaSection = new PizzaSection(driver);
    }

    @Test
    void testPizzaSorting() {
        pizzaSection.sortBy("price");
        List<Integer> prices = pizzaSection.getPizzaPrices();

        for (int i = 1; i < prices.size(); i++) {
            Assertions.assertTrue(prices.get(i) >= prices.get(i - 1),
                    "Цены не отсортированы по возрастанию: " + prices);
        }
    }

    @Test
    void testFilteringByPrice(){
        pizzaSection.filterByPrice(30, -40);

        List<Integer> prices = pizzaSection.getPizzaPrices();

        for (Integer price : prices) {
            Assertions.assertTrue(price >= 440 && price <= 510,
                    "Цена вне диапазона: " + price);
        }
    }

    @Test
    void testAddingPizzaToTheBasket() {
        pizzaSection.clickButtonInBasket();
        pizzaSection.clickButtonMoreDetails();

        String currentPizzaName = pizzaSection.getItemName();

        Assertions.assertEquals("Пицца \"4 в 1\"", currentPizzaName, "Пицца в корзине не соответствует ожидаемой Пиццы \"4 в 1\"");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
