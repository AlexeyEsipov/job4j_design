package ru.job4j.serialization.json;

public class Dog {
    private final String nick;

    public Dog(String nick) {
        this.nick = nick;
    }

    @Override
    public String toString() {
        return "Dog{"
                + "nickname='" + nick + '\''
                + '}';
    }

    public String getNick() {
        return nick;
    }
}
