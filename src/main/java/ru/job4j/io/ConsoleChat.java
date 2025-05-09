package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {

    private static final String OUT;
    private static final String STOP;
    private static final String CONTINUE;

    static {
        OUT = "закончить";
        STOP = "стоп";
        CONTINUE = "продолжить";
    }

    private final String logPath;
    private final String botAnswersPath;
    private final List<String> botAnswersList = new ArrayList<>();
    private final List<String> logList = new ArrayList<>();
    private boolean isStop = false;
    private boolean isExit = false;
    private final Random rand = new Random();

    public ConsoleChat(String path, String botAnswers) {
        this.logPath = path;
        this.botAnswersPath = botAnswers;
    }

    private void checked(String str) {
        logList.add(str);
        if (str.contains(OUT)) {
            isExit = true;
        }
        if (str.contains(STOP)) {
            isStop = true;
        }
        if (str.contains(CONTINUE)) {
            isStop = false;
        }
    }

    private void loadAnswer() {
        try (BufferedReader reader = new BufferedReader(
                                 new FileReader(botAnswersPath, StandardCharsets.UTF_8))) {
            while (reader.ready()) {
                botAnswersList.add(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exit() {
        /*try (PrintWriter writer = new PrintWriter(
                              new OutputStreamWriter(
                              new FileOutputStream(logPath), StandardCharsets.UTF_8))) {*/
        try (PrintWriter writer = new PrintWriter(
                logPath, StandardCharsets.UTF_8)) {
            for (String s : logList) {
                writer.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        loadAnswer();
        var input = new Scanner(System.in);
        while (!isExit) {
            var question = input.nextLine();
            checked(question);
            if (!isStop && !isExit) {
                System.out.println(getLine());
            }
        }
    }

    private String getLine() {
        var s = botAnswersList.get(rand.nextInt(botAnswersList.size()));
        logList.add(s);
        return s;
    }

    public static void main(String[] args) {
        ConsoleChat chat = new ConsoleChat("./dat/botLog.txt", "botAnswer.txt");
        chat.run();
        chat.exit();
    }
}
