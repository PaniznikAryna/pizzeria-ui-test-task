package com.pizzeria.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Файл config.properties не найден в resources.");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при загрузке config.properties", e);
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Ключ '" + key + "' не найден в config.properties");
        }

        while (value.contains("${")) {
            int start = value.indexOf("${") + 2;
            int end = value.indexOf("}", start);
            if (end == -1) break;

            String nestedKey = value.substring(start, end);
            String nestedValue = properties.getProperty(nestedKey);
            if (nestedValue == null) {
                throw new RuntimeException("Вложенный ключ '" + nestedKey + "' не найден в config.properties");
            }

            value = value.replace("${" + nestedKey + "}", nestedValue);
        }

        return value;
    }
}
