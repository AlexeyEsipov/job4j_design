package ru.job4j.io;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(target)))) {
            for (File source: sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgZip argZip = new ArgZip(args);
        if (!argZip.valid()) {
            throw new IllegalArgumentException("Не указаны параметры. "
                    + "Укажите параметры d, e, o в виде "
                    + "-d=c:\\project\\job4j\\ -e=class -o=project.zip");
        }
        File output = new File(argZip.output());
        List<Path> pathList;
        List<File> fileList = new ArrayList<>();
        Path dirBegin = Paths.get(argZip.directory());
        String ext = argZip.exclude();
        SearchFiles searcher = new SearchFiles(p -> !p.toFile().getName().endsWith(ext));
        Files.walkFileTree(dirBegin, searcher);
        pathList = searcher.getPaths();
        for (Path path: pathList) {
            fileList.add(path.toFile());
        }
        Zip zip = new Zip();
        zip.packFiles(fileList, output);
    }
}
