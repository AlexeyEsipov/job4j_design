package ru.job4j.collection;

import java.util.*;

public class UserMap {

    public static void main(String[] args) {
        // Создать карту Map<User, Object>
        Map<User, Object> userMap = new HashMap<>();
        // Создать два объекта User, которые имеют одинаковые поля.
        User alex = new User("Alex", 0, new GregorianCalendar(2001, Calendar.JANUARY, 1));
        User alex1 = new User("Alex", 0, new GregorianCalendar(2001, Calendar.JANUARY, 1));
        System.out.println(alex);
        // Добавить два объекта
        userMap.put(alex, "first");
        userMap.put(alex1, "first");

        // Вывести карту на печать
        System.out.println("----------------");
        System.out.println(userMap.toString());
    }
}
