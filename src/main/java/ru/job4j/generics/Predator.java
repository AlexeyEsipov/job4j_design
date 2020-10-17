package ru.job4j.generics;

public class Predator extends Animal {
    private String name;

    public Predator() {
        this.name = "1-й уровень";
    }

    @Override
    public String toString() {
        return "Predator потомок " + name;
    }
}
