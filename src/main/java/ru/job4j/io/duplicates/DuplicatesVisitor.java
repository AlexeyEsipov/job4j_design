package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Set<FileProperty> validateSet = new HashSet<>();
    private final Map<FileProperty, Path> duplicateMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        setValidateMap(file, attrs);
        return super.visitFile(file, attrs);
    }

    private void setValidateMap(Path file, BasicFileAttributes attrs) {
        String name = file.getFileName().toString();
        long size = attrs.size();
        FileProperty nameSize = new FileProperty(name, size);
        Path path = file.toAbsolutePath();
        if (!validateSet.add(nameSize)) {
            duplicateMap.put(nameSize, path);
        }
    }

    public void printDuplicateMap() {
        Set<Map.Entry<FileProperty, Path>> set = duplicateMap.entrySet();
        System.out.println("Duplicates:");
        for (Map.Entry<FileProperty, Path> item : set) {
            System.out.println("name: " + item.getKey().getName()
                            + "; size: " + item.getKey().getSize());
            System.out.println("path " + item.getValue().toString());
        }
        System.out.println(" ");
    }
}

