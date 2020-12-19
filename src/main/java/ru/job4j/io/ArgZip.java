package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgZip {
    private final String[] args;
    private Map<String, String> values = new HashMap<>();

    public ArgZip(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        String s1;
        String sKey;
        String sValue;
        for (String s: args) {
            if (s.startsWith("-") && s.contains("=") && !s.endsWith("=")) {
                s1 = s.substring(1);
                sKey = s1.split("=", 2)[0].toLowerCase();
                sValue = s1.substring(sKey.length() + 1);
                values.put(sKey, sValue);
            }
        }
        if (!(values.containsKey("d") && values.containsKey("e") && values.containsKey("o"))) {
            exNew();
        }
        var d = values.get("d");
        var o = values.get("o");
        if (!(o.endsWith(".zip")) && (d.endsWith("\\") || d.endsWith("/"))) {
            exNew();
        }
        return true;
    }

    private void exNew() {
        throw new IllegalArgumentException("Некорректно указаны параметры. "
                + "Укажите параметры d, e, o в виде "
                + "-d=c:\\project\\job4j\\ -e=class -o=project.zip");
    }

    public String directory() {
        return values.get("d");
    }

    public String exclude() {
        return values.get("e");
    }

    public String output() {
        return values.get("o");
    }
}
