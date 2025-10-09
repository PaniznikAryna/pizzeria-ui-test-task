package org.example.test_task_4;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MainPageTest {
    //- Переключение пицц в слайдере (стрелки влево/вправо) +--
    //- Наведение на картинку напитка с проверкой отображения ссылки «В корзину» +--
    //- Переход на страницу десерта при клике по его картинке +--
    //- Отображение ссылки-стрелочки «Наверх» в правом нижнем углу сайта при скроллинге в самый низ сайта +--
    //- Открытие ссылок на социальные сети из футера страницы в новой вкладке +--

    WebDriver driver;

    @BeforeEach
   void setUp(){
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://pizzeria.skillbox.cc/");
    }

    @Test
    void testRightNavigationSliderButton(){

    }

    @Test
    void testLeftNavigationSliderButton(){

    }

    @Test
    void testDisplayingLinkToTheBasket(){

    }

    @Test
    void testGoToTheDessertPage(){

    }

    @Test
    void testDisplayingTheUpArrowLink(){

    }

    @Test
    void testOpeningSocialMediaLinksInNewTab(){

    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
