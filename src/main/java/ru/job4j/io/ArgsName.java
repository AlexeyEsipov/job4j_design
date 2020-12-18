package ru.job4j.io;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String value = values.get(key);
        if (value == null) {
            throw new IllegalArgumentException();
        }
        return value;
    }

    private void parse(String[] args) {
        String s1;
        String sKey;
        String sValue;
        for (String s: args) {
            if (s.startsWith("-") && s.contains("=")) {
                s1 = s.substring(1);
                sKey = s1.split("=", 2)[0];
                sValue = s1.substring(sKey.length() + 1);
                values.put(sKey, sValue);
            }
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
