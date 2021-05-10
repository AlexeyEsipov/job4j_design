package ru.job4j.io.searchkriteria;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchMain {
    private static Map<String, String> values = new HashMap<>();
    private static List<File> listFiles = new ArrayList<>();
    private static List<File> resultFiles = new ArrayList<>();

    private void parseArgs(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Некорректные аргументы. "
                    + "Программа должна собираться в jar и запускаться через "
                    + "java -jar SearchMain.jar -d=c:/projects -n=*.txt -t=mask -o=log.txt");
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
        validateArgs();
    }

    private void validateArgs() {
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
                    + "*.txt - имя файла (например, prog.js), расширение (например, *.txt), "
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
    }

    private void searchFile() throws IOException {
        Path p = Paths.get(values.get("d"));
        Files.walkFileTree(p, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                listFiles.add(file.toFile());
                return FileVisitResult.CONTINUE;
            }
        });
        String t = values.get("t");
        String  n = values.get("n");
        if (t.equals("ext")) {
            resultFiles.addAll(Search.findByExt(n, listFiles));
        }
        if (t.equals("name")) {
            resultFiles.addAll(Search.findByName(n, listFiles));
        }
        if (t.equals("regex")) {
            resultFiles.addAll(Search.findByMask(n, listFiles));
        }
    }

    private void outFile() {
        File file = new File(values.get("d"), values.get("o"));
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(
                                               new FileOutputStream(file)))) {
            for (File fileName : resultFiles) {
                out.write(fileName.toString() + System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        SearchMain sm = new SearchMain();
        sm.parseArgs(args);
        sm.searchFile();
        sm.outFile();
    }
}