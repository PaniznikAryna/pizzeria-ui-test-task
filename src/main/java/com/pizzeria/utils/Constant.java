package com.pizzeria.utils;

public class Constant {
    public static final String FIRST_NAME = "test";
    public static final String LAST_NAME = "test";
    public static final String ADDRESS = "test";
    public static final String CITY = "test";
    public static final String STATE = "test";
    public static final String POSTCODE = "213342";
    public static final String PHONE = "+79991231231";
    public static final String EMAIL = "test@exemple.com";
    public static final String COUNTRY = "Belarus";

    // Making An Order Page
    public static final String CHECKOUT_LINK_XPATH = "//a[contains(@href,'/checkout/')]";
    public static final String DATE_INPUT_XPATH = "//input[@type='date']";

    public static final String BILLING_FIRST_NAME_XPATH = "//input[@id='billing_first_name']";
    public static final String BILLING_LAST_NAME_XPATH = "//input[@id='billing_last_name']";
    public static final String BILLING_ADDRESS_1_XPATH = "//input[@id='billing_address_1']";
    public static final String BILLING_CITY_XPATH = "//input[@id='billing_city']";
    public static final String BILLING_STATE_XPATH = "//input[@id='billing_state']";
    public static final String BILLING_POSTCODE_XPATH = "//input[@id='billing_postcode']";
    public static final String BILLING_PHONE_XPATH = "//input[@id='billing_phone']";
    public static final String BILLING_EMAIL_XPATH = "//input[@id='billing_email']";

    public static final String COUNTRY_DROPDOWN_CSS = ".select2-selection--single";
    public static final String COUNTRY_SEARCH_INPUT_CSS = "input.select2-search__field";
    public static final String COUNTRY_RESULT_XPATH_TEMPLATE = "//li[contains(@class,'select2-results__option') and normalize-space(text())='%s']";

    public static final String PAYMENT_METHOD_CASH_XPATH = "//input[@id='payment_method_cod']";
    public static final String TERMS_CHECKBOX_XPATH = "//input[@id='terms']";
    public static final String PLACE_ORDER_BUTTON_XPATH = "//button[@id='place_order']";

    public static final String ORDER_SUCCESS_MESSAGE_XPATH = "//p[contains(@class,'woocommerce-thankyou-order-received') and contains(text(),'Спасибо! Ваш заказ был получен.')]";
    public static final String CASH_PAYMENT_INFO_XPATH = "//p[contains(text(),'Оплата наличными при доставке заказа.')]";

    public static final String MENU_LINK_XPATH_CONTAINS = "//a[contains(@href,'product-category/menu')]";

    // Basket Page
    public static final String CART_ITEMS_XPATH = "//tr[@class='woocommerce-cart-form__cart-item cart_item']";
    public static final String REMOVE_BUTTONS_XPATH = "//a[@aria-label='Remove this item']";
    public static final String AMOUNT_RAW_XPATH = "//td[@data-title='Сумма']//bdi";
    public static final String COUNT_ITEM_XPATH = "//tr[contains(@class,'cart_item')]//input[contains(@name,'[qty]')]";
    public static final String BUTTON_UPDATE_BASKET_XPATH = "//button[@name='update_cart']";
    public static final String CHECKOUT_BUTTON_XPATH = "//a[contains(@href,'/checkout/')]"; // used for "Перейти к оплате"
    public static final String PLACE_ORDER_BUTTON_XPATH_BASKET = "//button[@id='place_order']";
    public static final String LINK_SHOWLOGIN_XPATH = "//a[@class='showlogin']";
    public static final String COUPON_CODE_ID = "coupon_code";
    public static final String BUTTON_APPLY_COUPON_XPATH = "//button[@value='Применить купон']";
    public static final String COUPON_APPLIED_MESSAGE_CSS = "div.woocommerce-message[role='alert']";

    // Bonus Program Page
    public static final String BONUS_USER_NAME_XPATH = "//input[@id=\"bonus_username\"]";
    public static final String BONUS_PHONE_XPATH = "//input[@id=\"bonus_phone\"]";
    public static final String BUTTON_ISSUE_CARD_XPATH = "//button[@class=\"woocommerce-Button woocommerce-button button woocommerce-form-register__submit\"]";
    public static final String CARD_HAS_BEEN_ISSUED_XPATH = "//h3[starts-with(text(),'Ваша карта оформлена!')]";

    // Delivery And Payment Page
    public static final String IFRAME_DELIVERY_AND_PAYMENT_XPATH = "//iframe";
    public static final String MIN_ORDER_AMOUNT_XPATH = "//li[contains(.,'Минимальная сумма заказа')]";

    // Login Page
    public static final String LOGIN_USER_NAME_XPATH = "//input[@id=\"username\"]";
    public static final String LOGIN_PASSWORD_XPATH = "//input[@id=\"password\"]";
    public static final String BUTTON_LOGIN_XPATH = "//button[@name=\"login\"]";
    public static final String MENU_URL_PATH = "/product-category/menu";

    //Main Page
    public static final String RIGHT_NAVIGATION_BUTTON_XPATH = "//a[@aria-label=\"next\"]";
    public static final String LEFT_NAVIGATION_BUTTON_XPATH = "//a[@aria-label=\"previous\"]";
    public static final String ACTIVE_SLIDE_XPATH = "//*[contains(@class, 'slick-slide') and contains(@class, 'slick-active')]";
    public static final String HOVER_BUTTON_IN_BASKET_XPATH = "//a[@data-product_id=\"431\"]";
    public static final String DRINK_SLIDER_ELEMENT_XPATH = "//img[@src=\"http://pizzeria.skillbox.cc/wp-content/uploads/2021/10/pexels-chevanon-photography-312418-300x300.jpg\"]";
    public static final String DESERT_SLIDE_ELEMENT_XPATH = "//img[@src=\"http://pizzeria.skillbox.cc/wp-content/uploads/2021/10/pexels-geraud-pfeiffer-6607296-300x300.jpg\"]";
    public static final String LINK_DESERT_XPATH = "//a[@title=\"Десерт «Булочка с корицей»\"]";
    public static final String UP_ARROW_LINK_XPATH = "//div[@id=\"ak-top\"]";
    public static final String SOCIAL_MEDIA_LINKS_XPATH = "//a[@rel=\"noopener noreferrer\"]";

    // Promo Page
    public static final String COUPON_XPATH = "//strong";

    // Menu Page
    public static final String BUTTON_DRINK_XPATH = "//a[@data-product_id='427']";
    public static final String BUTTON_DESERT_XPATH = "//a[@data-product_id='437']";
    public static final String BASKET_LINK_XPATH = "//a[contains(@href,'/cart')]";

    // Pizza Page
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
    public static final String SORT_BY_PRICE = "price";

    // My Account Page
    public static final String BUTTON_DATA_ACCOUNT_XPATH = "//li[@class=\"woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--edit-account\"]";
    public static final String INPUT_UPLOAD_FILE_XPATH = "//input[@id=\"uploadFile\"]";

    // Navigation Menu
    public static final String MENU_BUTTON_XPATH = "//li[@id='menu-item-389']";
    public static final String PIZZA_IN_MENU_BUTTON_XPATH = "//li[@id='menu-item-390']";
    public static final String DESERT_IN_MENU_BUTTON_XPATH = "//li[@id='menu-item-391']";
    public static final String DRINK_IN_MENU_BUTTON_XPATH = "//li[@id='menu-item-393']";

}
