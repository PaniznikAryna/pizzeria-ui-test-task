package com.pizzeria.section;

import com.pizzeria.utils.Constant;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationMenu {
    private final WebDriver driver;

    public NavigationMenu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = Constant.MENU_BUTTON_XPATH)
    private WebElement menuButton;

    @Step("Наведение на раздел \"Меню\" в панели навигации")
    public NavigationMenu moveToMenuButton() {
        new Actions(driver).moveToElement(menuButton).perform();
        return this;
    }

    @FindBy(xpath = Constant.PIZZA_IN_MENU_BUTTON_XPATH)
    private WebElement pizzaInMenuButton;

    @Step("Нажатие на кнопку \"Пиццы\" в разделе Меню")
    public NavigationMenu clickPizzaInMenuButton() {
        pizzaInMenuButton.click();
        return this;
    }

    @Step("Получение ссылки на страницу-секцию с пиццами")
    public String getPizzaMenuLink() {
        WebElement link = pizzaInMenuButton.findElement(By.tagName("a"));
        return link.getAttribute("href");
    }

    @FindBy(xpath = Constant.DESERT_IN_MENU_BUTTON_XPATH)
    private WebElement desertInMenuButton;

    @Step("Нажатие на кнопку \"Десерт\" в разделе Меню")
    public NavigationMenu clickDesertInMenuButton() {
        desertInMenuButton.click();
        return this;
    }

    @Step("Получение ссылки на страницу-секцию с десертами")
    public String getDesertMenuLink() {
        WebElement link = desertInMenuButton.findElement(By.tagName("a"));
        return link.getAttribute("href");
    }

    @FindBy(xpath = Constant.DRINK_IN_MENU_BUTTON_XPATH)
    private WebElement drinkInMenuButton;

    @Step("Нажатие на кнопку \"Напитки\" в разделе Меню")
    public NavigationMenu clickDrinkInMenuButton() {
        drinkInMenuButton.click();
        return this;
    }

    @Step("Получение ссылки на страницу-секцию с напитками")
    public String getDrinkMenuLink() {
        WebElement link = drinkInMenuButton.findElement(By.tagName("a"));
        return link.getAttribute("href");
    }
}
