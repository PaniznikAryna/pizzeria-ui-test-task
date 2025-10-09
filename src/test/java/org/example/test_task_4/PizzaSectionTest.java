package org.example.test_task_4;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class PizzaSectionTest {
    //- Применение сортировки пицц +--
    //- Фильтрация по цене +--
    //- Добавление пиццы в корзину +--

    WebDriver driver;

    @BeforeEach
    void setUp(){
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://pizzeria.skillbox.cc/product-category/menu/pizza/");
    }

    @Test
    void testPizzaSorting(){

    }

    @Test
    void testFilteringByPrice(){

    }

    @Test
    void testAddingPizzaToTheBasket(){

    }

    @AfterEach
    void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }

}
