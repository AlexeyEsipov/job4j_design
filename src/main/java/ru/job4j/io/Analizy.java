package ru.job4j.io;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        List<String> lines = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean workTime = false;
        int i = 0;
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                if ((i++) == 0) {
                    if (line.startsWith("400") || line.startsWith("500")) {
                        sb.append(line.split(" ")[1]);
                        sb.append(";");
                    } else {
                        workTime = true;
                    }
                    continue;
                }
                if (workTime) {
                    if (line.startsWith("400") || line.startsWith("500")) {
                        workTime = false;
                        sb.append(line.split(" ")[1]);
                        sb.append(";");
                    }
                } else if (line.startsWith("200") || line.startsWith("300")) {
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
        analizy.unavailable("server.log", "target.log");
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:11:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
