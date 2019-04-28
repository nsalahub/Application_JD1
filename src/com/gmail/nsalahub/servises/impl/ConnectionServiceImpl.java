package com.gmail.nsalahub.servises.impl;

import com.gmail.nsalahub.reposetory.config.ConfiguratorManager;
import com.gmail.nsalahub.servises.ConnectionService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionServiceImpl implements ConnectionService {

    private static ConnectionServiceImpl instance;

    private ConfiguratorManager configuratorManager;

    private ConnectionServiceImpl() {
        configuratorManager = ConfiguratorManager.getInstance();
        System.out.println("-------------MySQL connection testing--------------");
        try {
            Class.forName(configuratorManager.getProperty(ConfiguratorManager.DATABASE_DRIVER_NAME));
            System.out.println("------------MySQL JDBC Driver registered-----------");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver");
            e.printStackTrace();
        }
    }

    public static ConnectionServiceImpl getInstance() {
        if (instance == null) {
            instance = new ConnectionServiceImpl();
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        System.out.println("Creating connecting...");
        try {
            Properties properties = new Properties();
            properties.setProperty("user", configuratorManager.getProperty(ConfiguratorManager.DATABASE_USERNAME));
            properties.setProperty("password", configuratorManager.getProperty(ConfiguratorManager.DATABASE_PASSWORD));
            properties.setProperty("userUnicode", "true");
            properties.setProperty("characterEncoding", "cp1251");
            properties.setProperty("useJDBCompliantTimezoneShift", "true");
            properties.setProperty("useLegacyDatetimeCode", "false");
            properties.setProperty("serverTimezone", "UTC");
            Connection connection = DriverManager.getConnection(
                    configuratorManager.getProperty(ConfiguratorManager.DATABASE_URL),
                    properties);
            System.out.println("Connection was created");
            return connection;
        } catch (SQLException e) {
            System.out.println("Connection failed check output console");
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
