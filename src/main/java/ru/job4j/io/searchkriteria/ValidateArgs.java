package ru.job4j.io.searchkriteria;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ValidateArgs {
    private static Map<String, String> values = new HashMap<>();

    static void parseArgs(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Некорректные аргументы. "
                    + "Программа должна собираться в jar и запускаться через "
                    + "java -jar SearchMain.jar -d=c:/projects -n=txt -t=ext -o=log.txt");
        }
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

    static Map<String, String> createMapArgs() {
        if (!values.containsKey("d")) {
            throw new IllegalArgumentException("Некорректные аргументы. "
                    + "Аргументы должны содержать -d=c:/***, где  "
                    + "c:/*** - путь к каталогу поиска");
        } else if (!Files.isDirectory(Path.of(values.get("d")))) {
            throw new IllegalArgumentException("Некорректные аргументы. "
                    + Path.of(values.get("d")).toString() + " не является каталогом  ");
        }

        if (!values.containsKey("o")) {
            throw new IllegalArgumentException("Некорректные аргументы. "
                    + "Аргументы должны содержать -o=log.txt, где  "
                    + "log.txt - файл записи результата");
        } else if (values.get("o").isBlank()) {
            throw new IllegalArgumentException("Некорректные аргументы. "
                    + " Необходимо указать имя файла записи результата. ");
        }

        if (!(values.containsKey("n") && !values.get("n").isBlank())) {
            throw new IllegalArgumentException("Некорректные аргументы. "
                    + "Аргументы должны содержать -n=*.txt, где  "
                    + "*.txt - имя файла (например, prog.js), расширение (например, txt), "
                    + "либо регулярное выражение, например для расширения pdf:"
                    + " .+([.][Pp][Dd][Ff]){1}   ");
        }

        if (!values.containsKey("t")) {
            throw new IllegalArgumentException("Некорректные аргументы. "
                    + "Аргументы должны содержать -t=***, где  "
                    + "*** - тип поиска: ext искать по расширению, "
                    + "name по полному совпадению имени, regex по регулярному выражению");
        } else {
            String value = values.get("t");
            if (!(value.equals("ext") || value.equals("name")
                    || value.equals("regex"))) {
                throw new IllegalArgumentException("Некорректные аргументы. "
                        + " Необходимо указать тип поиска: ext искать по расширению, "
                        + "name по полному совпадению имени, "
                        + "regex по регулярному выражению");
            }
        }
        return values;
    }
}
