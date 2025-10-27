package com.pizzeria.pages;

import com.pizzeria.utils.Constant;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasketPage {
    private final WebDriver driver;
    private final Duration WAIT = Duration.ofSeconds(30);

    public BasketPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = Constant.CART_ITEMS_XPATH)
    private List<WebElement> cartItems;

    @Step("Получение количества товаров")
    public int getItemCount() {
        return cartItems.size();
    }

    @FindBy(xpath = Constant.REMOVE_BUTTONS_XPATH)
    private List<WebElement> removeButtons;

    @Step("Удаление первого элемента в корзине")
    public BasketPage removeFirstItem() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(removeButtons.get(0)));
        removeButtons.get(0).click();
        return this;
    }

    @FindBy(xpath = Constant.AMOUNT_RAW_XPATH)
    private WebElement amountRaw;

    @Step("Получение общей стоимости корзины")
    public float getAmount() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.visibilityOf(amountRaw));

        String raw = amountRaw.getText();
        String cleanRaw = raw.replaceAll("[^\\d,\\.]", "").replace(",", ".");
        return Float.parseFloat(cleanRaw);
    }

    @FindBy(xpath = Constant.COUNT_ITEM_XPATH)
    private WebElement countItem;

    @Step("Изменение количества продукта в корзине")
    public BasketPage changeCountItem() {
        countItem.clear();
        countItem.sendKeys("4");
        return this;
    }

    @FindBy(xpath = Constant.BUTTON_UPDATE_BASKET_XPATH)
    private WebElement buttonUpdateBasket;

    @Step("Нажатие на кнопку \"Обновить корзину\"")
    public BasketPage clickButtonUpdateBasket() {
        buttonUpdateBasket.click();
        return this;
    }

    @Step("Нажатие на кнопку \"Перейти к оплате\"")
    public BasketPage clickButtonGoToPay() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constant.CHECKOUT_BUTTON_XPATH)));
        button.click();
        return this;
    }

    @FindBy(xpath = Constant.PLACE_ORDER_BUTTON_XPATH_BASKET)
    private WebElement buttonPlaceOrder;

    @Step("Проверка появления кнопки \"Оформить заказ\"")
    public boolean isDisplayedButtonPlaceOrder() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.visibilityOf(buttonPlaceOrder));
        return buttonPlaceOrder.isDisplayed();
    }

    @FindBy(xpath = Constant.LINK_SHOWLOGIN_XPATH)
    private WebElement linkAuthorizedUser;

    @Step("Проверка появления уведомления о необходимости авторизации")
    public boolean isDisplayedLinkAuthorizedUser() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.visibilityOf(linkAuthorizedUser));
        return linkAuthorizedUser.isDisplayed();
    }

    @FindBy(id = Constant.COUPON_CODE_ID)
    private WebElement getCoupon;

    @FindBy(xpath = Constant.BUTTON_APPLY_COUPON_XPATH)
    private WebElement buttonApplyCoupon;

    @Step("Применение купона")
    public BasketPage applyCoupon(String couponCode) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(Constant.COUPON_CODE_ID)));
        getCoupon.sendKeys(couponCode);
        buttonApplyCoupon.click();
        return this;
    }

    @Step("Проверка появления уведомления о успешном применении купона")
    public boolean isCouponAppliedMessageVisible() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Constant.COUPON_APPLIED_MESSAGE_CSS)));
        return message.getText().contains("Coupon code applied successfully");
    }
}
