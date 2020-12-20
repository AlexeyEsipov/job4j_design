package ru.job4j.serialization.json;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
public class Owner {
    private final boolean sex;
    private final int age;
    private final String name;
    private final Dog dog;
    private final String[] cars;

    public Owner(boolean sex, int age, String name, Dog dog, String... cars) {
        this.sex = sex;
        this.age = age;
        this.name = name;
        this.dog = dog;
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "Owner{"
                + "sex=" + sex
                + ", age=" + age
                + ", name=" + name
                + ", dog nickname=" + dog.getNick()
                + ", cars=" + Arrays.toString(cars)
                + '}';
    }

    public static void main(String[] args) {
        final Owner owner = new Owner(false, 30, "Alex", new Dog("Ralf"), "Dodge", "Ford");

        /* Преобразуем объект owner в json-строку. */
        final Gson gson = new GsonBuilder().create();
        String s = gson.toJson(owner);
        System.out.println(s);
        final Owner ownerAfter = gson.fromJson(s, Owner.class);
        System.out.println(owner);
        System.out.println(ownerAfter);
    }
}
