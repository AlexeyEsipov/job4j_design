package ru.job4j.io;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        List<String> lines = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean workTime = true;
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                if (workTime && (line.startsWith("400") || line.startsWith("500"))) {
                    workTime = false;
                    sb.append(line.split(" ")[1]);
                    sb.append(";");
                }
                if (!workTime && (line.startsWith("200") || line.startsWith("300"))) {
                    workTime = true;
                    sb.append(line.split(" ")[1]);
                    lines.add(sb.toString());
                    sb = new StringBuilder();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(
                     new FileOutputStream(target)))) {
            for (String line : lines) {
                out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/server.log", "./data/target.log");
    }
}
