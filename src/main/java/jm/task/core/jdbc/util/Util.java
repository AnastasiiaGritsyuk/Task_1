package jm.task.core.jdbc.util;
import java.sql.Connection; //add
import java.sql.DriverManager; //add
import java.sql.SQLException; //add

import jm.task.core.jdbc.model.User; // add

public class Util { //реализация соединения
    // JDBC конфигурация
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "p@$$w0rd";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения к базе данных", e);
        }
    }
}
