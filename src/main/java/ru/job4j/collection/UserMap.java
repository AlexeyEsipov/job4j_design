package ru.job4j.collection;

import java.util.*;

public class UserMap {

    public static void main(String[] args) {
        Map<User, Object> userMap = new HashMap<>();
        User alex = new User("Alex", 0, new GregorianCalendar(2001, Calendar.JANUARY, 1));
        User alex1 = new User("Alex", 0, new GregorianCalendar(2001, Calendar.JANUARY, 1));
        System.out.println(alex);
        userMap.put(alex, "first");
        userMap.put(alex1, "first");
        System.out.println(userMap);
    }
}
