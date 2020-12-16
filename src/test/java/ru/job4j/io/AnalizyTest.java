package ru.job4j.io;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizyTest {

    @Test
    public void unavailable()  {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(
                     new FileOutputStream("source1.log")))) {
            out.write("200 10:56:01" + System.lineSeparator());
            out.write("500 10:57:01" + System.lineSeparator());
            out.write("400 10:58:01" + System.lineSeparator());
            out.write("200 10:59:01" + System.lineSeparator());
            out.write("500 11:01:02" + System.lineSeparator());
            out.write("200 11:02:02" + System.lineSeparator());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizy analizy = new Analizy();
        analizy.unavailable("source1.log", "target.log");
        try (BufferedReader in = new BufferedReader(new FileReader("target.log"))) {
            List<String> lin = new ArrayList<>();
            in.lines().forEach(lin::add);
            assertThat(lin.get(0), is("10:57:01;10:59:01"));
            assertThat(lin.get(1), is("11:01:02;11:02:02"));
        } catch  (Exception e) {
            e.printStackTrace();
        }
    }
}