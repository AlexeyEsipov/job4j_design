package ru.job4j.io.searchkriteria.obr;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("The argument is null. Usage -KEY=VALUE.");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("The argument is null. Usage -KEY=VALUE.");
        }
        for (String arg : args) {
            String[] checkedArgs = correctArgs(arg);
            StringBuilder sb = new StringBuilder(checkedArgs[0]);
            String name = sb.deleteCharAt(0).toString();
            values.put(name, checkedArgs[1]);
        }
    }

    private String[] correctArgs(String arg) {
        if (!arg.startsWith("-")) {
            throw new IllegalArgumentException("One of the arguments` names does not start with -."
                    + " Usage -KEY=VALUE.");
        }
        String[] argValues = arg.split("=", 2);
        if (argValues.length != 2 || "".equals(argValues[1])
                || argValues[1].startsWith(" ") || argValues[0].endsWith(" ")) {
            throw new IllegalArgumentException("One of the arguments is not legal."
                    + " Usage -KEY=VALUE.");
        }
        return argValues;
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }
}