package ru.job4j.io.serialization.json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class OwnerJson {
    private final boolean sex;
    private final int age;
    private final String name;
    private final Dog dog;
    private final String[] cars;

    public OwnerJson(boolean sex, int age, String name, Dog dog, String... cars) {
        this.sex = sex;
        this.age = age;
        this.name = name;
        this.dog = dog;
        this.cars = cars;
    }

    public boolean isSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Dog getDog() {
        return dog;
    }

    public String[] getCars() {
        return cars;
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
        /* JSONObject из json-строки строки */
        JSONObject jsonDog = new JSONObject("{\"nick\":\"Ralf\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Dodge");
        list.add("Ford");
        JSONArray jsonCars = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final OwnerJson ownerJson = new OwnerJson(false, 30, "Alex",
                                                  new Dog("Ralf"), "Dodge", "Ford");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", ownerJson.isSex());
        jsonObject.put("age", ownerJson.getAge());
        jsonObject.put("name", ownerJson.getName());
        jsonObject.put("dog", jsonDog);
        jsonObject.put("cars", jsonCars);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(ownerJson).toString());
    }
}