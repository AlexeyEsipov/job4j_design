package ru.job4j.mailmerge;

import java.util.LinkedHashSet;

public class User {
    private String name;
    private LinkedHashSet<String> mailUser;

    public User(String name, LinkedHashSet<String> mailUser) {
        this.name = name;
        this.mailUser = mailUser;
    }

    public String getName() {
        return name;
    }

    public LinkedHashSet<String> getMailUser() {
        return mailUser;
    }
}
