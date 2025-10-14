package org.example.test_task_4;

import org.example.pages.BasketPage;
import org.example.pages.LoginPage;
import org.example.pages.MakingAnOrder;
import org.example.pages.MenuPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;

public class MakingAnOrderTest {
    WebDriver driver;
    MakingAnOrder makingAnOrder;
    LoginPage loginPage;
    MenuPage menuPage;
    BasketPage basket;

    @BeforeEach
    void setUp(){
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://pizzeria.skillbox.cc/checkout/");
        makingAnOrder = new MakingAnOrder(driver);
        loginPage = new LoginPage(driver);
        menuPage = new MenuPage(driver);
        basket = new BasketPage(driver);
    }

    @Test
    void testSettingTheOrderDate() throws InterruptedException {
        driver.get("https://pizzeria.skillbox.cc/my-account/");
        loginPage.login("test1233314143", "testuser");

        loginPage.goToMenu();
        menuPage.addDrinkToBasket();
        Thread.sleep(1000);
        menuPage.goToBasket();
        basket = new BasketPage(driver);

        makingAnOrder.goToMakingAnOrder();
        makingAnOrder = new MakingAnOrder(driver);

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        String expectedDate = tomorrow.toString();

        makingAnOrder.setDate(expectedDate);

        String actualDate = makingAnOrder.getDate();
        Assertions.assertEquals(expectedDate, actualDate, "Дата в поле не соответствует ожидаемой");
    }


    @Test
    void testMakingTheOrderWithCashPayment() throws InterruptedException {
        driver.get("https://pizzeria.skillbox.cc/my-account/");
        loginPage.login("test1233314143", "testuser");

        loginPage.goToMenu();
        menuPage.addDrinkToBasket();
        Thread.sleep(1000);
        menuPage.goToBasket();
        basket = new BasketPage(driver);

        makingAnOrder.goToMakingAnOrder();
        makingAnOrder = new MakingAnOrder(driver);

        makingAnOrder.setForm();
        makingAnOrder.selectPaymentOnDelivery();
        makingAnOrder.acceptTermsAndConditions();
        makingAnOrder.clickButtonMakingAnOrder();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[contains(@class,'woocommerce-thankyou-order-received') and contains(text(),'Спасибо! Ваш заказ был получен.')]")
        ));

        WebElement paymentInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[contains(text(),'Оплата наличными при доставке заказа.')]")
        ));

        Assertions.assertTrue(successMessage.isDisplayed(), "Сообщение о получении заказа не найдено");
        Assertions.assertTrue(paymentInfo.isDisplayed(), "Информация об оплате наличными не найдена");

    }

    @AfterEach
    void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }

}
