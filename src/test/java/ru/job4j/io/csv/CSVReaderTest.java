package ru.job4j.io.csv;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.*;

public class CSVReaderTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private File source;
    private File expected;
    private File target;
    private String sourcePath;
    private String targetPath;

    @Before
    public void setUp() throws IOException {
        source = folder.newFile("source.csv");
        sourcePath = "-path=" + source.getAbsolutePath();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("name;age;birthDate;education;children");
            out.println("name1;11;2010-01-08;high1;don1");
            out.println("name2;12;2010-02-08;high2;don2");
        }

        expected = folder.newFile("expected.txt");
        try (PrintWriter out = new PrintWriter(expected)) {
            out.println("name;age;");
            out.println("name1;11;");
            out.println("name2;12;");
        }
        target = folder.newFile("target.txt");
        targetPath = "-out=" + target.getAbsolutePath();
    }

    @Test
    public void headerProcessing() throws IOException {
        CSVReader csvReader = new CSVReader();
        csvReader.validate(new String[]{"java", "-jar", "csvReader.jar",
                sourcePath, "-delimiter=;", targetPath, "-filter=name,age"});
        csvReader.init();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            csvReader.headerProcessing(reader);
            csvReader.bodyProcessing(reader);
        }
        csvReader.outReport();
        List<String> expectedList = Files.readAllLines(Path.of(expected.getAbsolutePath()));
        List<String> actualList = Files.readAllLines(Path.of(target.getAbsolutePath()));
        assertArrayEquals(expectedList.toArray(), actualList.toArray());
    }
}