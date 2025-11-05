package com.pizzeria.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class PizzaPage {
    public static final String SORT_DROPDOWN_XPATH = "//select[@name='orderby']";
    public static final String FILTER_BUTTON_XPATH = "//button[text()='Применить']";
    public static final String PIZZA_ITEMS_XPATH = "//ul[contains(@class,'products')]/li";
    public static final String BUTTON_IN_BASKET_XPATH = "//a[@data-product_id=\"425\"]";
    public static final String BUTTON_MORE_DETAILS_XPATH = "//a[@title=\"Подробнее\"]";
    public static final String ITEM_NAME_XPATH = "//td[@class='product-name']//a";
    public static final String PRODUCT_LIST_XPATH = "//ul[contains(@class,'products')]/li";
    public static final String PRICE_SLIDER_WRAPPER_XPATH = "//div[contains(@class,'price_slider_wrapper')]";
    public static final String LEFT_PRICE_HANDLE_XPATH = "(//span[contains(@class,'ui-slider-handle')])[1]";
    public static final String RIGHT_PRICE_HANDLE_XPATH = "(//span[contains(@class,'ui-slider-handle')])[2]";

    private WebDriver driver;
    private final Duration WAIT = Duration.ofSeconds(10);

    public PizzaPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = SORT_DROPDOWN_XPATH)
    private WebElement sortDropdown;

    @FindBy(xpath = FILTER_BUTTON_XPATH)
    private WebElement filterButton;

    @FindBy(xpath = PIZZA_ITEMS_XPATH)
    private List<WebElement> pizzaItems;

    @Step("Сортировка пицц")
    public PizzaPage sortBy(String value) {
        Select select = new Select(sortDropdown);
        select.selectByValue(value);

        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath(PRODUCT_LIST_XPATH)
        ));
        return this;
    }

    @Step("Фильтрация пицц по стоимости: сдвиг правого и левого ползунка")
    public PizzaPage filterByPrice(int leftOffset, int rightOffset) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(PRICE_SLIDER_WRAPPER_XPATH)));

        WebElement leftHandle = driver.findElement(By.xpath(LEFT_PRICE_HANDLE_XPATH));
        WebElement rightHandle = driver.findElement(By.xpath(RIGHT_PRICE_HANDLE_XPATH));

        wait.until(ExpectedConditions.visibilityOf(leftHandle));
        wait.until(ExpectedConditions.visibilityOf(rightHandle));

        Actions actions = new Actions(driver);
        actions.clickAndHold(leftHandle).moveByOffset(leftOffset, 0).release().perform();
        actions.clickAndHold(rightHandle).moveByOffset(rightOffset, 0).release().perform();

        wait.until(ExpectedConditions.elementToBeClickable(filterButton)).click();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath(PRODUCT_LIST_XPATH)
        ));
        return this;
    }

    @Step("Получение списка цен на отображаемые пиццы")
    public List<Integer> getPizzaPrices() {
        return pizzaItems.stream().map(item -> Integer.parseInt(item.findElement(By.xpath(".//bdi")).getText().replaceAll("[^\\d]", "")) / 100).collect(Collectors.toList());
    }

    @FindBy(xpath = BUTTON_IN_BASKET_XPATH)
    private WebElement buttonInBasket;

    @Step("Нажатие на кнопку \"В корзину\"")
    public PizzaPage clickButtonInBasket() {
        buttonInBasket.click();
        return this;
    }

    @FindBy(xpath = BUTTON_MORE_DETAILS_XPATH)
    private WebElement buttonMoreDetails;

    @Step("Нажатие на кнопку \"Подробнее\"")
    public PizzaPage clickButtonMoreDetails() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(buttonMoreDetails));
        buttonMoreDetails.click();
        return this;
    }

    @FindBy(xpath = ITEM_NAME_XPATH)
    private WebElement itemName;

    @Step("Получение названия товара в корзине")
    public String getItemName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(itemName));
        return itemName.getText();
    }
}
