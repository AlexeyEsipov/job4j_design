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
        boolean result;
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
            return false;
        }
        var d = values.get("d");
        var o = values.get("o");
        result = (o.endsWith(".zip")) && (d.endsWith("\\") || d.endsWith("/"));
        return result;
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
