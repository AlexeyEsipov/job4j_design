package ru.job4j.io;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(this.path))) {
            List<String> lines = new ArrayList<>();
            in.lines().forEach(lines::add);
            String[] words;
            for (String line : lines) {
                if (line.isEmpty()) {
                    continue;
                }
                if (line.startsWith("//") || line.startsWith("##")) {
                    continue;
                }
                if (line.startsWith("/*") || line.startsWith(" *")) {
                    continue;
                }
                if (line.contains("=")) {
                    words = line.split("=");
                    values.put(words[0], words[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config config = new Config("./chapter_006/data/app.properties");
        config.load();
    }
}
