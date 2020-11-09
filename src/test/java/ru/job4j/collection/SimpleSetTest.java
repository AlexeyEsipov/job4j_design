package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddThenGet() {
        SimpleSet<String> sst = new SimpleSet<>();
        sst.add("first");
        Iterator<String> itSst = sst.iterator();
        assertThat(itSst.next(), is("first"));
    }

    @Test
    public void whenMultiAddThenGet() {
        SimpleSet<String> sst = new SimpleSet<>();
        sst.add("first");
        sst.add("second");
        Iterator<String> itSst = sst.iterator();
        assertThat(itSst.next(), is("first"));
        assertThat(itSst.next(), is("second"));
    }

    @Test
    public void whenDuplicateMultiAddThenGet() {
        SimpleSet<String> sst = new SimpleSet<>();
        sst.add("first");
        sst.add("second");
        sst.add("first");
        sst.add("second");
        sst.add("third");
        sst.add("first");
        Iterator<String> itSst = sst.iterator();
        assertThat(itSst.next(), is("first"));
        assertThat(itSst.next(), is("second"));
        assertThat(itSst.next(), is("third"));
    }
}