package ru.job4j.io.searchkriteria.obr;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.List;
import java.util.function.Predicate;

public class Main {

    public static void handle(ArgsName argsName) {
        String lob = "%s%s".formatted("mask".equals(argsName.get("t"))
                ? "glob:"
                : "regex:", argsName.get("n"));
        PathMatcher pm = FileSystems.getDefault().getPathMatcher(lob);
        List<Path> list = search(Path.of(argsName.get("d")), p -> pm.matches(p.getFileName()));
        print(list, argsName);
    }

    private static List<Path> search(Path root, Predicate<Path> condition) {
        SearchFiles searcher = new SearchFiles(condition);
        try {
            Files.walkFileTree(root, searcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searcher.getPaths();
    }

    private static void print(List<Path> list, ArgsName argsName) {
        try (PrintWriter bw = new PrintWriter(argsName.get("o"))) {
            for (Path path : list) {
                bw.println(path.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void validateArgs(ArgsName an) {
        Path root = Path.of(an.get("d"));
        String fileName = an.get("n");
        String typeSearch = an.get("t");
        if (!fileName.contains(".")) {
            throw new IllegalArgumentException("Wrong file name. "
                    + "Usage java -jar fileSearch.jar"
                    + "-d=ROOT_FILE "
                    + "-n=FILE_NAME_TO_SEARCH (it can be name, mask or regular expression) "
                    + "-t=TYPE_OF_SEARCH (name, mask or regex) "
                    + "-o=FILE_NAME_FOR_RESULT");
        }
        if (!"name".equals(typeSearch)
                && !"mask".equals(typeSearch)
                && !"regex".equals(typeSearch)) {
            throw new IllegalArgumentException("Wrong type of search argument."
                    + "Usage java -jar fileSearch.jar"
                    + "-d=ROOT_FILE "
                    + "-n=FILE_NAME_TO_SEARCH (it can be name, mask or regular expression) "
                    + "-t=TYPE_OF_SEARCH (name, mask or regex) "
                    + "-o=FILE_NAME_FOR_RESULT");
        }
        if (!root.toFile().isDirectory()) {
            throw new IllegalArgumentException("Wrong root file name. "
                    + "Usage java -jar fileSearch.jar"
                    + "-d=ROOT_FILE "
                    + "-n=FILE_NAME_TO_SEARCH (it can be name, mask or regular expression) "
                    + "-t=TYPE_OF_SEARCH (name, mask or regex) "
                    + "-o=FILE_NAME_FOR_RESULT");
        }
        an.get("o");
    }

    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Wrong quantity of arguments. "
                    + "Usage java -jar fileSearch.jar"
                    + "-d=ROOT_FILE "
                    + "-n=FILE_NAME_TO_SEARCH (it can be name or mask) "
                    + "-t=TYPE_OF_SEARCH (name, mask) "
                    + "-o=FILE_NAME_FOR_RESULT");
        }
        ArgsName argsName = ArgsName.of(args);
        validateArgs(argsName);
        handle(argsName);
    }
}