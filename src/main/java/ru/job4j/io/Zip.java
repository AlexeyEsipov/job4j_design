package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Zip {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public List<Path> findFiles(ArgZip argZip, Predicate<Path> predicate) throws IOException {
        Path dirStart = Paths.get(argZip.directory());
        SearchFiles searcher = new SearchFiles(predicate);
        Files.walkFileTree(dirStart, searcher);
        return searcher.getPaths();
    }

    public void packFiles(List<Path> sources, File target) throws IOException {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            ArgZip argZip = new ArgZip(args);
            argZip.valid();
            Zip zip = new Zip();
            File output = new File(argZip.output());
            String ext = argZip.exclude();
            Predicate<Path> predicate = p -> !p.toFile().getName().endsWith(ext);
            List<Path> pathList = zip.findFiles(argZip, predicate);
            zip.packFiles(pathList, output);
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("Exception in log: ", e);
        }
    }
}
