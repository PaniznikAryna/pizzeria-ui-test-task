package com.pizzeria.pages;

import com.pizzeria.utils.Constant;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MenuPage {
    private WebDriver driver;
    private final Duration WAIT = Duration.ofSeconds(10);

    public MenuPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = Constant.BUTTON_DRINK_XPATH)
    private WebElement buttonDrink;

    @FindBy(xpath = Constant.BUTTON_DESERT_XPATH)
    private WebElement buttonDesert;

    @Step("Добавление напитка в корзину")
    public MenuPage addDrinkToBasket() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", buttonDrink);
        return this;
    }

    @Step("Добавление десерта в корзину")
    public MenuPage addDesertToBasket() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(buttonDesert));
        buttonDesert.click();
        return this;
    }

    @Step("Переход на страницу Корзина")
    public MenuPage goToBasket() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        By basketLocator = By.xpath(Constant.BASKET_LINK_XPATH);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(basketLocator)).click();
        } catch (StaleElementReferenceException e) {
            wait.until(ExpectedConditions.elementToBeClickable(basketLocator)).click();
        }
        return this;
    }

}
