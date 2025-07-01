package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        // Создание таблицы пользователей
        userService.createUsersTable();

        // Добавление 4 пользователей
        userService.saveUser("Иван", "Иванов", (byte) 25);
        System.out.println("User с именем – Иван добавлен в базу данных");

        userService.saveUser("Петр", "Петров", (byte) 30);
        System.out.println("User с именем – Петр добавлен в базу данных");

        userService.saveUser("Сергей", "Сидоров", (byte) 35);
        System.out.println("User с именем – Сергей добавлен в базу данных");

        userService.saveUser("Анна", "Ахматова", (byte) 40);
        System.out.println("User с именем – Анна добавлен в базу данных");

        // Удаление одного пользователя с id = 2
        userService.removeUserById(2);
        System.out.println("User с id = 2 удален из базы данных");

        // Получение всех пользователей и вывод в консоль
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }

        // Очистка таблицы
        userService.cleanUsersTable();

        // Удаление таблицы
        userService.dropUsersTable();
    }
}
