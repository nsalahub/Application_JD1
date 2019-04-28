package com.gmail.nsalahub.reposetory.config;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ConfiguratorManager {

    private static ConfiguratorManager instance;

    private ResourceBundle resourceBundle;

    private ConfiguratorManager() {
    }

    private static final String BUNDLE_NAME = "properties";

    public static final String DATABASE_DRIVER_NAME = "database.driver.name";
    public static final String DATABASE_URL = "database.url";
    public static final String DATABASE_USERNAME = "database.username";
    public static final String DATABASE_PASSWORD = "database.password";

    public static final String LOGIN_PAGE_PATH = "login.page.path";
    public static final String ITEM_PAGE_PATH = "item.page.path";
    public static final String REGISTRATION_PAGE_PATH = "registration.page.path";
    public static final String ADD_ITEM_PAGE_PATH = "add.item.page.path";
    public static final String UPDATE_ITEM_PAGE_PATH = "update.item.page.path";
    public static final String ITEM_SALE_PAGE_PATH = "item.sale.page.path";
    public static final String ORDER_SALE_PAGE_PATH = "order.sale.page.path";
    public static final String SUCCESS_PAGE_PATH = "success.page.path";
    public static final String XML_PAGE_PATH = "xml.page.path";

    public static ConfiguratorManager getInstance() {
        if (instance == null) {
            instance = new ConfiguratorManager();
            instance.resourceBundle = PropertyResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }

}
