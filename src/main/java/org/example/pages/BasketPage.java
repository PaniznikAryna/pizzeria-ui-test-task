package org.example.pages;

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
    private WebDriver driver;

    public BasketPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//tr[@class='woocommerce-cart-form__cart-item cart_item']")
    private List<WebElement> cartItems;

    @Step("Получение количества товаров")
    public int getItemCount() {
        return cartItems.size();
    }

    @FindBy(xpath = "//a[@aria-label='Remove this item']")
    private List<WebElement> removeButtons;

    @Step("Удаление первого элемента в корзине")
    public void removeFirstItem() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(removeButtons.get(0)));
        removeButtons.get(0).click();
    }


    @FindBy(xpath = "//td[@data-title='Сумма']//bdi")
    private WebElement amountRaw;


    @Step("Получение общей стоимости корзины")
    public float getAmount() throws InterruptedException {
        Thread.sleep(1000);

        String raw = amountRaw.getText();

        String cleanRaw = raw.replaceAll("[^\\d,\\.]", "").replace(",", ".");

        return Float.parseFloat(cleanRaw);
    }

    @FindBy(xpath = "//tr[contains(@class,'cart_item')]//input[contains(@name,'[qty]')]")
    private WebElement countItem;

    @Step("Изменение количества продукта в корзине")
    public void changeCountItem() {
        countItem.clear();
        countItem.sendKeys("4");
    }

    @FindBy(xpath = ("//button[@name=\"update_cart\"]"))
    private WebElement buttonUpdateBasket;

    @Step("Нажатие на кнопку \"Обновить корзину\"")
    public void clickButtonUpdateBasket(){
        buttonUpdateBasket.click();
    }

    @Step("Нажатие на кнопку \"Перейти к оплате\"")
    public void clickButtonGoToPay() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='http://pizzeria.skillbox.cc/checkout/']")
        ));
        button.click();
    }

    @FindBy(xpath = "//button[@id=\"place_order\"]")
    private WebElement buttonPlaceOrder;

    @Step("Проверка появления кнопки \"Оформить заказ\"")
    public boolean isDisplayedButtonPlaceOrder(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(buttonPlaceOrder));
        return buttonPlaceOrder.isDisplayed();
    }

    @FindBy(xpath = "//a[@class=\"showlogin\"]")
    private WebElement linkAuthorizedUser;

    @Step("Проверка появления уведомления о необходимости авторизации")
    public boolean isDisplayedLinkAuthorizedUser(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(linkAuthorizedUser));
        return linkAuthorizedUser.isDisplayed();
    }

    @FindBy(xpath = "//input[@id=\"coupon_code\"]")
    private WebElement getCoupon;

    @FindBy(xpath = "//button[@value=\"Применить купон\"]")
    private WebElement buttonApplyCoupon;

    @Step("Применение купона")
    public void applyCoupon(String couponCode){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("coupon_code")));
        getCoupon.sendKeys(couponCode);
        buttonApplyCoupon.click();
    }

    @Step("Проверка появления уведомления о успешном применении купона")
    public boolean isCouponAppliedMessageVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.woocommerce-message[role='alert']")
        ));

        return message.getText().contains("Coupon code applied successfully");
    }
}
