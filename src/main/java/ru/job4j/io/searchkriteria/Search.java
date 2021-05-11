package ru.job4j.io.searchkriteria;
import java.nio.file.Path;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Search {

    static Predicate<Path> searchCondition(Map<String, String> values) {
        Predicate<Path> result;
        String t = values.get("t");
        String  n = values.get("n");
        if (t.equals("ext")) {
            result = path -> path.toFile().getName().endsWith(n);
        } else if (t.equals("name")) {
            result = path -> path.toFile().getName().equals(n);
        } else {
            result = path -> Pattern.matches(n, path.toFile().getName());
        }
        return result;
    }
}
