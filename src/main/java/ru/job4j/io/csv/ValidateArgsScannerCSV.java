package ru.job4j.io.csv;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ValidateArgsScannerCSV {
    private static Map<String, String> values = new HashMap<>();

    static void parseArgs(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Недостаточно аргументов. "
                    + "Программа должна собираться в jar и запускаться через "
                    + "java -jar csvReader.jar -path=file.csv -delimiter=; "
                    + " -out=stdout -filter=name,age");
        }
        String parameter;
        String sKey;
        String sValue;
        for (String s: args) {
            if (s.startsWith("-") && s.contains("=")) {
                parameter = s.substring(1);
                sKey = parameter.split("=", 2)[0];
                sValue = parameter.substring(sKey.length() + 1);
                values.put(sKey, sValue);
            }
        }
    }

    static Map<String, String> createMapArgs() {
        if (!values.containsKey("path")) {
            throw new IllegalArgumentException("Некорректные аргументы. "
                    + "Аргументы должны содержать -path=c:/file.csv, где  "
                    + "c:/*** - путь к файлу .csv");
        } else if (!new File(values.get("path")).isFile()) {
            throw new IllegalArgumentException("Некорректные аргументы. "
                    + values.get("path") + " не является файлом  ");
        } else if (!values.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException("Некорректные аргументы. "
                    + values.get("path") + " не является файлом .csv");
        }

        if (!values.containsKey("delimiter")) {
            throw new IllegalArgumentException("Некорректные аргументы. "
                    + "Аргументы должны содержать -delimiter=; , "
                    + "где ; - символ разделения полей");
        } else if (values.get("delimiter").isBlank()) {
            throw new IllegalArgumentException("Некорректные аргументы. "
                    + " Необходимо указать символ разделения полей. ");
        }

        if (!(values.containsKey("out") && !values.get("out").isBlank())) {
            throw new IllegalArgumentException("Некорректные аргументы. "
                    + "Аргументы должны содержать "
                    + "-out=stdout для вывода результата на консоль или "
                    + "-out=filename.txt для вывода результата в файл, "
                    + "где filename.txt - имя файла записи результата ");
        }

        if (!(values.containsKey("filter") && !values.get("filter").isBlank())) {
            throw new IllegalArgumentException("Некорректные аргументы. "
                    + "Аргументы должны содержать -filter=name,age где  "
                    + "name,age - имена тех столбцов, (если несколько - то указать через запятую) "
                    + "которые необходимо вывести на экран или в файл");
        }
        return values;
    }
}
