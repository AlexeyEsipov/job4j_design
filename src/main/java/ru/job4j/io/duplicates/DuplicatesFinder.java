package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Search folder is null. "
                    + "Usage java -jar DuplicatesFinder.jar SEARCH_FOLDER.");
        }
        File file = new File(args[0]);
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s",
                    file.getAbsoluteFile()));
        }
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of(args[0]), duplicatesVisitor);
        duplicatesVisitor.printDuplicateMap();
    }
}
