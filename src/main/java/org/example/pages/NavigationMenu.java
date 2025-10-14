package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationMenu {
    private WebDriver driver;

    public NavigationMenu(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//li[@id=\"menu-item-389\"]")
    private WebElement menuButton;

    @Step("Наведение на раздел \"Меню\" в панели навигации")
    public void moveToMenuButton(){
        Actions action = new Actions(driver);
        action.moveToElement(menuButton).perform();
    }

    @FindBy(xpath = "//li[@id=\"menu-item-390\"]")
    private WebElement pizzaInMenuButton;

    @Step("Нажатие на кнопку \"Пиццы\" в разделе Меню")
    public void clickPizzaInMenuButton(){
        pizzaInMenuButton.click();
    }

    @Step("Получение ссылки на страницу-секцию с пиццами")
    public String getPizzaMenuLink() {
        WebElement link = pizzaInMenuButton.findElement(By.tagName("a"));
        return link.getAttribute("href");
    }

    @FindBy(xpath = "//li[@id=\"menu-item-391\"]")
    private WebElement desertInMenuButton;

    @Step("Нажатие на кнопку \"Десерт\" в разделе Меню")
    public void clickDesertInMenuButton(){
        desertInMenuButton.click();
    }

    @Step("Получение ссылки на страницу-секцию с десертами")
    public String getDesertMenuLink() {
        WebElement link = desertInMenuButton.findElement(By.tagName("a"));
        return link.getAttribute("href");
    }

    @FindBy(xpath = "//li[@id=\"menu-item-393\"]")
    private WebElement drinkInMenuButton;

    @Step("Нажатие на кнопку \"Напитки\" в разделе Меню")
    public void clickDrinkInMenuButton(){
        drinkInMenuButton.click();
    }

    @Step("Получение ссылки на страницу-секцию с напитками")
    public String getDrinkMenuLink() {
        WebElement link = drinkInMenuButton.findElement(By.tagName("a"));
        return link.getAttribute("href");
    }

}
