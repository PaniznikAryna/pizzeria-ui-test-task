package org.example.test_task_4;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.pages.LoginPage;
import org.example.pages.MyAccount;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Epic("Мой аккаунт")
@Feature("Загрузка изображения")
@DisplayName("Тест для страницы Мой аккаунт")
public class MyAccountTest {

    WebDriver driver;
    LoginPage loginPage;
    MyAccount myAccount;

    @BeforeEach
    void setUp(){
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://pizzeria.skillbox.cc/my-account/");
        loginPage = new LoginPage(driver);
        myAccount = new MyAccount(driver);
    }

    @Test
    @Story("Загрузка файла")
    @DisplayName("Проверка загрузки файла в разделе редактирования аккаунта")
    @Description("Авторизация пользователя, переход в раздел редактирования и загрузка файла")
    void testUploadingFile(){
        loginPage.login("test1233314143", "testuser");

        myAccount.clickButtonDataAccount();
        driver.get("https://pizzeria.skillbox.cc/my-account/edit-account/");

        myAccount.uploadFile();

    }

    @AfterEach
    void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
}
