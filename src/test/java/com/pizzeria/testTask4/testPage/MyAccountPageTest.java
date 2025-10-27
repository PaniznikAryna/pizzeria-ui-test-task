package com.pizzeria.testTask4.testPage;

import com.pizzeria.testTask4.BaseTest;
import com.pizzeria.testTask4.extensions.ScreenshotOnFailureExtension;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import com.pizzeria.pages.LoginPage;
import com.pizzeria.pages.MyAccountPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import io.qameta.allure.junit5.AllureJunit5;

import static com.pizzeria.utils.ConfigReader.get;

@ExtendWith(ScreenshotOnFailureExtension.class)
@ExtendWith({AllureJunit5.class, ScreenshotOnFailureExtension.class})
@Epic("Мой аккаунт")
@Feature("Загрузка изображения")
@DisplayName("Тест для страницы Мой аккаунт")
public class MyAccountPageTest extends BaseTest {

    LoginPage loginPage;
    MyAccountPage myAccountPage;

    @BeforeEach
    void openMyAccountPage() {
        driver.get(get("url.accountPage"));
        loginPage = new LoginPage(driver);
        myAccountPage = new MyAccountPage(driver);
    }

    @Test
    @Story("Загрузка файла")
    @DisplayName("Проверка загрузки файла в разделе редактирования аккаунта")
    @Description("Авторизация пользователя, переход в раздел редактирования и загрузка файла")
    void testUploadingFile() {
        loginPage.login(get("user.login"), get("user.password"));

        myAccountPage.clickButtonDataAccount();
        driver.get(get("url.editAccountPage"));

        myAccountPage.uploadFile();
    }
}
