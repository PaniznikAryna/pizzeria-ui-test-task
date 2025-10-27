package com.pizzeria.testTask4.testPage;

import com.pizzeria.testTask4.BaseTest;
import com.pizzeria.testTask4.extensions.ScreenshotOnFailureExtension;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import com.pizzeria.pages.BonusProgramPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import io.qameta.allure.junit5.AllureJunit5;

import static com.pizzeria.utils.ConfigReader.get;

@ExtendWith(ScreenshotOnFailureExtension.class)
@ExtendWith({AllureJunit5.class, ScreenshotOnFailureExtension.class})
@Epic("Бонусная программа")
@Feature("Оформление бонусной карты")
@DisplayName("Тест для страницы бонусной программы")
public class BonusProgramPageTest extends BaseTest {

    BonusProgramPage bonusProgramPage;

    @BeforeEach
    void openBonusProgramPage() {
        driver.get(get("url.bonusPage"));
        bonusProgramPage = new BonusProgramPage(driver);
    }

    @Test
    @Story("Оформление карты")
    @DisplayName("Успешное оформление бонусной карты")
    @Description("Проверяет, что пользователь может успешно оформить бонусную карту, заполнив имя и телефон")
    void testMakingCard() {
        boolean isVisible = bonusProgramPage.FillingUserName()
                .FillingPhone()
                .clickButtonIssueCard()
                .closeAlert()
                .displayH3WithCardHasBeenIssued();

        Assertions.assertTrue(bonusProgramPage.displayH3WithCardHasBeenIssued(), "Карта бонуса не была оформлена");
    }
}
