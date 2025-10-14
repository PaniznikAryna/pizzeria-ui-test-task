package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@aria-label=\"next\"]")
    private WebElement rightNavigationButton;

    public void clickRightSlider() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", rightNavigationButton);
    }


    public boolean isRightArrowVisible() {
        return rightNavigationButton.isDisplayed();
    }

    public WebElement getRightArrow() {
        return rightNavigationButton;
    }

    @FindBy(xpath = "//a[@aria-label=\"previous\"]")
    private WebElement leftNavigationButton;

    public void clickLeftSlider(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", leftNavigationButton);
    }


    public boolean isLeftArrowVisible() {
        return leftNavigationButton.isDisplayed();
    }

    public WebElement getLeftArrow() {
        return leftNavigationButton;
    }

    @FindBy(xpath = "//*[contains(@class, 'slick-slide') and contains(@class, 'slick-active')]")
    private WebElement activeSlide;

    public String getActiveSlideIndex() {
        return activeSlide.getAttribute("data-slick-index");
    }

    @FindBy(xpath = "//a[@data-product_id=\"431\"]")
    private WebElement hoverButtonInBasket;

    public boolean displayButtonInBasket(){
        return hoverButtonInBasket.isDisplayed();
    }

    public WebElement getButtonInBasketElement() {
        return hoverButtonInBasket;
    }


    @FindBy(xpath = "//img[@src=\"http://pizzeria.skillbox.cc/wp-content/uploads/2021/10/pexels-chevanon-photography-312418-300x300.jpg\"]")
    private WebElement drinkSliderElement;

    public void hoverDrinkSlider() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", drinkSliderElement);
        Actions action = new Actions(driver);
        action.moveToElement(drinkSliderElement).perform();
    }

    @FindBy(xpath = "//img[@src=\"http://pizzeria.skillbox.cc/wp-content/uploads/2021/10/pexels-geraud-pfeiffer-6607296-300x300.jpg\"]")
    private WebElement desertSlideElement;

    public void clickDessertImg(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", desertSlideElement);
    }

    @FindBy(xpath = "//a[@title=\"Десерт «Булочка с корицей»\"]")
    private WebElement linkDesert;

    public String getDesertLink(){
      return linkDesert.getAttribute("href");
    }

    @FindBy(xpath = "//div[@id=\"ak-top\"]")
    private WebElement upArrowLink;

    public boolean displayupArrowLink(){
        return upArrowLink.isDisplayed();
    }

    public void scrollPage(){
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public Point getUpArrowLocation() {
        return upArrowLink.getLocation();
    }

    public Dimension getWindowSize() {
        return driver.manage().window().getSize();
    }

    @FindBy(xpath = "//a[@rel=\"noopener noreferrer\"]")
    private WebElement socialMediaLinks;

    public void clickSocialMediaLinks(){
        socialMediaLinks.click();
    }

    public String getSocialMediaLinks(){
        return socialMediaLinks.getAttribute("href");
    }

}
