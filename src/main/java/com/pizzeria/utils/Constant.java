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

    public static final String ORDER_SUCCESS_MESSAGE_XPATH = "//p[contains(@class,'woocommerce-thankyou-order-received') and contains(text(),'Спасибо! Ваш заказ был получен.')]";
    public static final String CASH_PAYMENT_INFO_XPATH = "//p[contains(text(),'Оплата наличными при доставке заказа.')]";
    public static final String SORT_BY_PRICE = "price";
}
