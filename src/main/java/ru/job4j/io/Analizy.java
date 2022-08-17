package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Analizy {

    public void unavailable(String source, String target) {
        boolean isWork = true;
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(target)) {
            while (in.ready()) {
                String[] time = in.readLine().split(" ");
                if (isWork == Integer.parseInt(time[0]) > 300) {
                    out.append(time[1]).append(isWork ? ";" : System.lineSeparator());
                    isWork = !isWork;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/server.log", "./data/target.log");
    }
}
