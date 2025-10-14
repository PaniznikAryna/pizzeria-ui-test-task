package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@aria-label=\"next\"]")
    private WebElement rightNavigationButton;

    @Step("Нажатие на правую кнопку слайдера")
    public void clickRightSlider() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", rightNavigationButton);
    }

    @FindBy(xpath = "//a[@aria-label=\"previous\"]")
    private WebElement leftNavigationButton;

    @Step("Нажатие на левую кнопку слайдера")
    public void clickLeftSlider(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", leftNavigationButton);
    }

    @FindBy(xpath = "//*[contains(@class, 'slick-slide') and contains(@class, 'slick-active')]")
    private WebElement activeSlide;

    @Step("Получение индекса активного элемента слайдера")
    public String getActiveSlideIndex() {
        return activeSlide.getAttribute("data-slick-index");
    }

    @FindBy(xpath = "//a[@data-product_id=\"431\"]")
    private WebElement hoverButtonInBasket;

    @Step("Проверка отображения кнопки \"В корзину\"")
    public boolean displayButtonInBasket(){
        return hoverButtonInBasket.isDisplayed();
    }

    @Step("Возвращаем элемент-кнопка \"В корзину\"")
    public WebElement getButtonInBasketElement() {
        return hoverButtonInBasket;
    }

    @FindBy(xpath = "//img[@src=\"http://pizzeria.skillbox.cc/wp-content/uploads/2021/10/pexels-chevanon-photography-312418-300x300.jpg\"]")
    private WebElement drinkSliderElement;

    @Step("Наведение курсора на элемент слайдера с напитками")
    public void hoverDrinkSlider() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", drinkSliderElement);
        Actions action = new Actions(driver);
        action.moveToElement(drinkSliderElement).perform();
    }

    @FindBy(xpath = "//img[@src=\"http://pizzeria.skillbox.cc/wp-content/uploads/2021/10/pexels-geraud-pfeiffer-6607296-300x300.jpg\"]")
    private WebElement desertSlideElement;

    @Step("Нажатие на элемент слайдера с десертами")
    public void clickDessertImg(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", desertSlideElement);
    }

    @FindBy(xpath = "//a[@title=\"Десерт «Булочка с корицей»\"]")
    private WebElement linkDesert;

    @Step("Получение ссылки на десерт")
    public String getDesertLink(){
      return linkDesert.getAttribute("href");
    }

    @FindBy(xpath = "//div[@id=\"ak-top\"]")
    private WebElement upArrowLink;

    @Step("Проверка отображения кнопки \"Наверх\"")
    public boolean displayUpArrowLink(){
        return upArrowLink.isDisplayed();
    }

    @Step("Скрол страницы в самый низ")
    public void scrollPage(){
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    @Step("Получение позиции кнопки  \"Наверх\"")
    public Point getUpArrowLocation() {
        return upArrowLink.getLocation();
    }

    @Step("Получение размеров экрана")
    public Dimension getWindowSize() {
        return driver.manage().window().getSize();
    }

    @FindBy(xpath = "//a[@rel=\"noopener noreferrer\"]")
    private WebElement socialMediaLinks;

    @Step("Нажатие на ссылку социальных сетей")
    public void clickSocialMediaLinks(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(socialMediaLinks));
        socialMediaLinks.click();
    }


    @Step("Получение адреса социальной сети")
    public String getSocialMediaLinks(){
        return socialMediaLinks.getAttribute("href");
    }

}
