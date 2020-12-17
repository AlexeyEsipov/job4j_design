package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import static java.nio.file.FileVisitResult.CONTINUE;

public class Search {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        System.out.println(start.toString());
        search(start, "txt").forEach(System.out::println);
    }

    public static List<Path> search(Path root, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> p.toFile().getName().endsWith(ext));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static class SearchFiles implements FileVisitor<Path> {
        private List<Path> pathList = new ArrayList<>();
        private Path path;
        private Predicate<Path> predicate;

        public SearchFiles(Predicate<Path> predicate) {
            this.predicate = predicate;

        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (predicate.test(file)) {
                pathList.add(file);
            }
            return CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                throws IOException {
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return null;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            return null;
        }

        public List<Path> getPaths() {
            return pathList;
        }
    }
}

