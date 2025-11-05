package com.pizzeria.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    public static final String RIGHT_NAVIGATION_BUTTON_XPATH = "//a[@aria-label=\"next\"]";
    public static final String LEFT_NAVIGATION_BUTTON_XPATH = "//a[@aria-label=\"previous\"]";
    public static final String ACTIVE_SLIDE_XPATH = "//*[contains(@class, 'slick-slide') and contains(@class, 'slick-active')]";
    public static final String HOVER_BUTTON_IN_BASKET_XPATH = "//a[@data-product_id=\"431\"]";
    public static final String DRINK_SLIDER_ELEMENT_XPATH = "//img[@src=\"http://pizzeria.skillbox.cc/wp-content/uploads/2021/10/pexels-chevanon-photography-312418-300x300.jpg\"]";
    public static final String DESERT_SLIDE_ELEMENT_XPATH = "//img[@src=\"http://pizzeria.skillbox.cc/wp-content/uploads/2021/10/pexels-geraud-pfeiffer-6607296-300x300.jpg\"]";
    public static final String LINK_DESERT_XPATH = "//a[@title=\"Десерт «Булочка с корицей»\"]";
    public static final String UP_ARROW_LINK_XPATH = "//div[@id=\"ak-top\"]";
    public static final String SOCIAL_MEDIA_LINKS_XPATH = "//a[@rel=\"noopener noreferrer\"]";

    private WebDriver driver;
    private final Duration WAIT = Duration.ofSeconds(30);

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = RIGHT_NAVIGATION_BUTTON_XPATH)
    private WebElement rightNavigationButton;

    @Step("Нажатие на правую кнопку слайдера")
    public MainPage clickRightSlider() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", rightNavigationButton);
        return this;
    }

    @FindBy(xpath = LEFT_NAVIGATION_BUTTON_XPATH)
    private WebElement leftNavigationButton;

    @Step("Нажатие на левую кнопку слайдера")
    public MainPage clickLeftSlider() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", leftNavigationButton);
        return this;
    }

    @FindBy(xpath = ACTIVE_SLIDE_XPATH)
    private WebElement activeSlide;

    @Step("Получение индекса активного элемента слайдера")
    public String getActiveSlideIndex() {
        return activeSlide.getAttribute("data-slick-index");
    }

    @FindBy(xpath = HOVER_BUTTON_IN_BASKET_XPATH)
    private WebElement hoverButtonInBasket;

    @Step("Проверка отображения кнопки \"В корзину\"")
    public boolean displayButtonInBasket() {
        return hoverButtonInBasket.isDisplayed();
    }

    @Step("Возвращаем элемент-кнопка \"В корзину\"")
    public WebElement getButtonInBasketElement() {
        return hoverButtonInBasket;
    }

    @FindBy(xpath = DRINK_SLIDER_ELEMENT_XPATH)
    private WebElement drinkSliderElement;

    @Step("Наведение курсора на элемент слайдера с напитками")
    public MainPage hoverDrinkSlider() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", drinkSliderElement);
        Actions action = new Actions(driver);
        action.moveToElement(drinkSliderElement).perform();
        return this;
    }

    @Step("Ожидание появления кнопки 'В корзину' при наведении")
    public MainPage waitForBasketButtonVisible() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.visibilityOf(hoverButtonInBasket));
        return this;
    }

    @FindBy(xpath = DESERT_SLIDE_ELEMENT_XPATH)
    private WebElement desertSlideElement;

    @Step("Нажатие на элемент слайдера с десертами")
    public MainPage clickDessertImg() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", desertSlideElement);
        return this;
    }

    @FindBy(xpath = LINK_DESERT_XPATH)
    private WebElement linkDesert;

    @Step("Получение ссылки на десерт")
    public String getDesertLink() {
        return linkDesert.getAttribute("href");
    }

    @FindBy(xpath = UP_ARROW_LINK_XPATH)
    private WebElement upArrowLink;

    @Step("Проверка отображения кнопки \"Наверх\"")
    public boolean displayUpArrowLink() {
        return upArrowLink.isDisplayed();
    }

    @Step("Скрол страницы в самый низ")
    public MainPage scrollPage() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        return this;
    }

    @Step("Получение позиции кнопки  \"Наверх\"")
    public Point getUpArrowLocation() {
        return upArrowLink.getLocation();
    }

    @Step("Получение размеров экрана")
    public Dimension getWindowSize() {
        return driver.manage().window().getSize();
    }

    @FindBy(xpath = SOCIAL_MEDIA_LINKS_XPATH)
    private WebElement socialMediaLinks;

    @Step("Нажатие на ссылку социальных сетей")
    public MainPage clickSocialMediaLinks() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(socialMediaLinks));
        socialMediaLinks.click();
        return this;
    }

    @Step("Получение адреса социальной сети")
    public String getSocialMediaLinks() {
        return socialMediaLinks.getAttribute("href");
    }

}
