package ru.job4j.collection;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {

    @Test
    public void whenInsertThenGet() {
        SimpleHashMap<String, String> shm = new SimpleHashMap<>();
        shm.insert("a", "abram");
        shm.insert("b", "barny");
        assertThat(shm.get("a"), is("abram"));
        assertThat(shm.get("b"), is("barny"));
    }

    @Test
    public void whenDeleteThenNull() {
        SimpleHashMap<String, String> shm = new SimpleHashMap<>();
        shm.insert("a", "abram");
        shm.insert("b", "barny");
        assertThat(shm.get("a"), is("abram"));
        assertThat(shm.get("b"), is("barny"));
        shm.delete("a");
        assertNull(shm.get("a"));
    }

    @Test
    public void whenInsertAndGrowThenGet() {
        SimpleHashMap<String, String> shm = new SimpleHashMap<>();
        shm.insert("a", "abram");
        shm.insert("b", "barny");
        shm.insert("c", "cat");
        shm.insert("d", "david");
        shm.insert("e", "eva");
        shm.insert("f", "ford");
        shm.insert("g", "garry");
        shm.insert("h", "helen");
        assertThat(shm.get("a"), is("abram"));
        assertThat(shm.get("h"), is("helen"));
    }

    @Test
    public void whenInsertDuplicateThenDeleteOne() {
        SimpleHashMap<String, String> shm = new SimpleHashMap<>();
        shm.insert("a", "abram");
        shm.insert("a", "barny");
        assertThat(shm.get("a"), is("abram"));
        shm.delete("a");
        assertNull(shm.get("a"));
    }

    @Test
    public void whenInsertDuplicateThenKeepFirst() {
        SimpleHashMap<String, String> shm = new SimpleHashMap<>();
        shm.insert("a", "abram");
        shm.insert("a", "barny");
        assertThat(shm.get("a"), is("abram"));
    }
}