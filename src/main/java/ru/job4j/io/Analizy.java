package ru.job4j.io;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(
                             new FileOutputStream(target)))) {
            List<String> lines = new ArrayList<>();
            in.lines().forEach(lines::add);
            boolean workTime = false;
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if ((i == 0) && (line.startsWith("400") || line.startsWith("500"))) {
                    String[] w;
                    w = line.split(" ");
                    out.write(w[1] + ";");
                    workTime = false;
                    continue;
                }
                if ((i == 0) && (line.startsWith("200") || line.startsWith("300"))) {
                    workTime = true;
                    continue;
                }
                if (workTime && (line.startsWith("200") || line.startsWith("300"))) {
                    continue;
                }
                if (!workTime && (line.startsWith("400") || line.startsWith("500"))) {
                    continue;
                }
                if (workTime && line.startsWith("400") || line.startsWith("500")) {
                    workTime = false;
                    String[] w;
                    w = line.split(" ");
                    out.write(w[1] + ";");
                    continue;
                }
                if (!workTime && line.startsWith("200") || line.startsWith("300")) {
                    workTime = true;
                    String[] w;
                    w = line.split(" ");
                    out.write(w[1] + System.lineSeparator());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("server.log", "target.log");
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
