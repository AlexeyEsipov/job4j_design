package ru.job4j.io.searchkriteria;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Search {
    private static List<File> findBy(Predicate<File> predicate, List<File> src) {
        List<File> rsl = new ArrayList<>();
        for (File file : src) {
            if (predicate.test(file)) {
                rsl.add(file);
            }
        }
        return rsl;
    }

    static List<File> findByMask(String mask, List<File> src) {
        return findBy(file -> Pattern.matches(mask, file.getName()), src);
    }

    static List<File> findByName(String name, List<File> src) {
        return findBy(file -> file.getName().equals(name), src);
    }

    static List<File> findByExt(String ext, List<File> src) {
        return findBy(file -> file.getName().endsWith(ext), src);
    }
}
