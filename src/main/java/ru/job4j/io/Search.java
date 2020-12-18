package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Не указаны параметры. Укажите папку для поиска и "
                    + "через пробел требуемое расширение файла, например, C:\\ .txt");
        }
        Path start = Paths.get(args[0]);
        String ex = args[1];
        System.out.println(start.toString());
        search(start, ex).forEach(System.out::println);
    }

    public static List<Path> search(Path root, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> p.toFile().getName().endsWith(ext));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}

