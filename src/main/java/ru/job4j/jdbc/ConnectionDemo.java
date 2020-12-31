package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDemo {
    private static final Properties properties = new Properties();

    private static void getProp() {
        try (InputStream in = Thread
                .currentThread()
                .getContextClassLoader()
                .getResourceAsStream("app.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        getProp();
        Class.forName("org.postgresql.Driver");

        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");

        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
