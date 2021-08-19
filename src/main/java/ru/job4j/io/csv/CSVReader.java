package ru.job4j.io.csv;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    private Map<String, String> argsMap = new HashMap<>();
    private List<String> filterHeader = new ArrayList<>();
    private List<Integer> listColumnHeader = new ArrayList<>();
    private List<String> result = new ArrayList<>();
    private Path destination;
    private String delimiter;
    private String source;

    public void validate(String[] args) {
        ValidateArgsScannerCSV.parseArgs(args);
        argsMap.putAll(ValidateArgsScannerCSV.createMapArgs());
    }

    public void init() {
        delimiter = argsMap.get("delimiter");
        String filterArgs = argsMap.get("filter");
        filterHeader.addAll(List.of(filterArgs.split(",")));
        source = argsMap.get("path");
        destination = Paths.get(argsMap.get("out"));
    }

    public void headerProcessing(BufferedReader reader) throws IOException {
        Scanner scanner = new Scanner(reader.readLine()).useDelimiter(delimiter);
        int i = 0;
        StringBuilder resultHeader = new StringBuilder();
        while (scanner.hasNext()) {
            String column = scanner.next();
            if (filterHeader.contains(column)) {
                listColumnHeader.add(i);
                resultHeader.append(column).append(delimiter);
            }
            i++;
        }
        result.add(resultHeader.toString());
    }

    public void bodyProcessing(BufferedReader reader) throws IOException {
        String line;
        int index = 0;
        while ((line = reader.readLine()) != null) {
            StringBuilder resultRow = new StringBuilder();
            Scanner scanner = new Scanner(line).useDelimiter(delimiter);
            while (scanner.hasNext()) {
                String data = scanner.next();
                if (listColumnHeader.contains(index)) {
                    resultRow.append(data).append(delimiter);
                }
                index++;
            }
            index = 0;
            result.add(resultRow.toString());
        }
    }

    public void outReport() throws IOException {
        if (Objects.equals(argsMap.get("out"), "stdout")) {
            System.out.print(result);
        } else {
            Files.write(destination, result, StandardCharsets.UTF_8);
        }
    }

    public static void main(String[] args) throws IOException {
        CSVReader csvReader = new CSVReader();
        csvReader.validate(args);
        csvReader.init();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvReader.source))) {
            csvReader.headerProcessing(reader);
            csvReader.bodyProcessing(reader);
        }
        csvReader.outReport();
    }
}
