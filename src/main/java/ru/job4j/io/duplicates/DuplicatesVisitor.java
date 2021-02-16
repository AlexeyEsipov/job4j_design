package ru.job4j.io.duplicates;


import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, Path> validateMap = new HashMap<>();
    private final Map<FileProperty, HashSet<Path>> duplicateMap = new HashMap<>();

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
        if (validateMap.putIfAbsent(nameSize, path) != null) {
            HashSet<Path> set = new HashSet<>();
            if (duplicateMap.containsKey(nameSize)) {
                set = duplicateMap.get(nameSize);
            } else {
                set.add(validateMap.get(nameSize));
            }
            set.add(path);
            duplicateMap.put(nameSize, set);
        }
    }

    public void printDuplicateMap() {
        Set<Map.Entry<FileProperty, HashSet<Path>>> set = duplicateMap.entrySet();
        System.out.println("Duplicates:");
        for (Map.Entry<FileProperty, HashSet<Path>> item : set) {
            System.out.println("name: " + item.getKey().getName()
                            + "; size: " + item.getKey().getSize());
            for (Path path : item.getValue()) {
                System.out.println("path " + path.toString());
            }
            System.out.println(" ");
        }
    }
}
