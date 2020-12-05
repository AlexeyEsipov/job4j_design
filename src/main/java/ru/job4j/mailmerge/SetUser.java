package ru.job4j.mailmerge;

import java.util.*;

public class SetUser  {

    private ArrayList<User> userSet = new ArrayList<>();

    public void addUser(User user) {
        userSet.add(user);
    }

    public int size() {
        return userSet.size();
    }

    public User getUser(int index) {
        return userSet.get(index);
    }
}
