package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
    try (FileOutputStream out = new FileOutputStream("./data/result.txt")) {
        out.write("Hello, world!".getBytes());
    } catch (Exception e) {
        e.printStackTrace();
    }
    try (FileOutputStream out = new FileOutputStream("./data/input.txt")) {
        out.write("login=login".getBytes());
        out.write("password=password".getBytes());
        out.write("login=login".getBytes());
    } catch (Exception e) {
        e.printStackTrace();
    }
    try (FileOutputStream out = new FileOutputStream("./data/even.txt")) {
        out.write("1".getBytes());
        out.write("5".getBytes());
        out.write("15".getBytes());
        out.write("17".getBytes());
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}
