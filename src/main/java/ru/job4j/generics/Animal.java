package ru.job4j.generics;

public class Animal {
    private String name;

    public Animal() {
        this.name = "Предок";
    }

    @Override
    public String toString() {
        return "Animal общий " + name;
    }
}
