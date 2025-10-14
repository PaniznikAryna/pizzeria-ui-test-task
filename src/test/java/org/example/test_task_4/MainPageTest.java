package org.example.test_task_4;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.pages.MainPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Epic("Главная страница")
@Feature("Главная страница с интерактивными элементами")
@DisplayName("Тесты для главной страницы сайта")
public class MainPageTest {

    WebDriver driver;
    MainPage mainPage;

    @BeforeEach
   void setUp(){
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://pizzeria.skillbox.cc/");
        mainPage = new MainPage(driver);
    }

    @Test
    @Story("Слайдер пицц")
    @DisplayName("Переключение пицц в слайдере вправо")
    @Description("Проверяет, что при нажатии на стрелку вправо слайдер переключается на следующий элемент")
    void testRightNavigationSliderButton(){

            String before = mainPage.getActiveSlideIndex();

            mainPage.clickRightSlider();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(driver -> {
                String current = mainPage.getActiveSlideIndex();
                return !current.equals(before);
            });

            String after = mainPage.getActiveSlideIndex();
            Assertions.assertNotEquals(before, after, "Слайдер не переключился");
    }

    @Test
    @Story("Слайдер пицц")
    @DisplayName("Переключение пицц в слайдере влево")
    @Description("Проверяет, что при нажатии на стрелку влево слайдер переключается на предыдущий элемент")
    void testLeftNavigationSliderButton(){

        String before = mainPage.getActiveSlideIndex();

        mainPage.clickLeftSlider();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(driver -> {
            String current = mainPage.getActiveSlideIndex();
            return !current.equals(before);
        });

        String after = mainPage.getActiveSlideIndex();
        Assertions.assertNotEquals(before, after, "Слайдер не переключился");
    }

    @Test
    @Story("Наведение на товар")
    @DisplayName("Отображение кнопки 'В корзину' при наведении на напиток")
    @Description("Проверяет, что при наведении на изображение напитка появляется кнопка 'В корзину'")
    void testDisplayingLinkToTheBasket() {
        mainPage.hoverDrinkSlider();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(mainPage.getButtonInBasketElement()));

        Assertions.assertTrue(mainPage.displayButtonInBasket(), "Кнопка для перехода 'В корзину' не появилась при наведении на напиток");
    }



    @Test
    @Story("Переход по изображению десерта")
    @DisplayName("Переход на страницу десерта при клике по изображению")
    @Description("Проверяет, что при клике на изображение десерта происходит переход на соответствующую страницу")
    void testGoToTheDessertPage(){

        String expectedUrl = mainPage.getDesertLink().replace("https://", "").replace("http://", "");
        mainPage.clickDessertImg();
        String actualUrl = driver.getCurrentUrl().replace("https://", "").replace("http://", "");
        Assertions.assertEquals(expectedUrl, actualUrl, "URL страницы не соответствует ожидаемому");
    }

    @Test
    @Story("Скроллинг страницы")
    @DisplayName("Отображение стрелки 'Наверх' при скроллинге")
    @Description("Проверяет, что при прокрутке страницы вниз появляется стрелка 'Наверх' в правом нижнем углу")
    void testDisplayingTheUpArrowLink(){
        mainPage.scrollPage();
        Assertions.assertTrue(mainPage.displayUpArrowLink(), "Отсутствует отображение ссылки-стрелочки «Наверх»");

        Point location = mainPage.getUpArrowLocation();
        int arrowX = location.getX();
        int arrowY = location.getY();

        Dimension windowSize = driver.manage().window().getSize();
        int windowWidth = windowSize.getWidth();
        int windowHeight = windowSize.getHeight();

        Assertions.assertTrue(arrowX >= windowWidth - 150 && arrowY >= windowHeight - 150, "Стрелка 'Наверх' не находится в правом нижнем углу");
    }

    @Test
    @Story("Социальные сети")
    @DisplayName("Открытие ссылок на соцсети в новой вкладке")
    @Description("Проверяет, что при клике на иконку соцсети происходит переход в новую вкладку на соответствующую страницу")
    void testOpeningSocialMediaLinksInNewTab() {
        String expectedUrl = mainPage.getSocialMediaLinks();
        mainPage.clickSocialMediaLinks();

        String originalWindow = driver.getWindowHandle();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> driver.getWindowHandles().size() > 1);

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        String actualUrl = driver.getCurrentUrl();
        Assertions.assertEquals(expectedUrl, actualUrl, "Страница перехода не соответствует ожидаемой");

        driver.close();
        driver.switchTo().window(originalWindow);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
