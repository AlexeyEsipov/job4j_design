package ru.job4j.generics;

public class Tiger extends Predator {
    private String name;

    public Tiger() {
        this.name = "2-й уровень";
    }

    @Override
    public String toString() {
        return "Tiger потомок " + name;
    }
}
