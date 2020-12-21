package ru.job4j.io;

import java.io.*;
import java.util.*;

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
                                 new FileReader(botAnswersPath))) {
            while (reader.ready()) {
                botAnswersList.add(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exit() {
        try (PrintWriter writer = new PrintWriter(
                              new OutputStreamWriter(
                              new FileOutputStream(logPath), "cp1251"))) {
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
        var s = botAnswersList.get(new Random().nextInt(botAnswersList.size()));
        logList.add(s);
        return s;
    }

    public static void main(String[] args) throws IOException {
        ConsoleChat chat = new ConsoleChat("./data/logChat.txt", "./data/server.log");
        chat.run();
        chat.exit();
    }
}
