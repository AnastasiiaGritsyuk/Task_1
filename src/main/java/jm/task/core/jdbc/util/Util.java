package jm.task.core.jdbc.util;

import java.sql.Connection; //add
import java.sql.DriverManager; //add
import java.sql.SQLException; //add
import org.hibernate.SessionFactory; //add
import org.hibernate.boot.registry.StandardServiceRegistryBuilder; //add
import org.hibernate.cfg.Configuration; // add
import org.hibernate.cfg.Environment; // add
import org.hibernate.service.ServiceRegistry; //add
import jm.task.core.jdbc.model.User; // add

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Util {  //реализация соединения
    // JDBC конфигурация
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "p@$$w0rd";

    // Hibernate конфигурация
    private static SessionFactory sessionFactory;

    static {
        // Отключаем логирование Hibernate
        Logger.getLogger("org.hibernate").setLevel(Level.OFF);
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения к базе данных", e);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = getConfiguration();

                // Добавление класса сущности
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();

        // Настройки Hibernate
        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "org.postgresql.Driver");
        settings.put(Environment.URL, URL);
        settings.put(Environment.USER, USERNAME);
        settings.put(Environment.PASS, PASSWORD);
        settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");

        // Отключаем показ SQL и другие ненужные настройки
        settings.put(Environment.SHOW_SQL, "false");
        settings.put(Environment.FORMAT_SQL, "false");
        settings.put(Environment.USE_SQL_COMMENTS, "false");
        settings.put(Environment.HBM2DDL_AUTO, "none");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

        // Настройки пула соединений
        settings.put(Environment.C3P0_MIN_SIZE, "5");
        settings.put(Environment.C3P0_MAX_SIZE, "20");
        settings.put(Environment.C3P0_TIMEOUT, "300");
        settings.put(Environment.C3P0_MAX_STATEMENTS, "50");
        settings.put(Environment.C3P0_IDLE_TEST_PERIOD, "3000");

        configuration.setProperties(settings);
        return configuration;
    }
}
