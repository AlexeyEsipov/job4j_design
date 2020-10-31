package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleLinkedListTest {

    @Test
    public void whenAddThenGet() {
        SimpleLinkedList<String> sll = new SimpleLinkedList<>();
        sll.add("first");
        sll.add("second");
        assertThat(sll.get(0), is("first"));
        assertThat(sll.get(1), is("second"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        SimpleLinkedList<String> sll = new SimpleLinkedList<>();
        sll.add("first");
        sll.get(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenEmptyGetThenOutBound() {
        SimpleLinkedList<String> sll = new SimpleLinkedList<>();
        sll.get(0);
    }

    @Test
    public void whenAddThenIt() {
        SimpleLinkedList<String> sll = new SimpleLinkedList<>();
        sll.add("first");
        String rsl = sll.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleLinkedList<String> sll = new SimpleLinkedList<>();
        sll.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleLinkedList<String> sll = new SimpleLinkedList<>();
        sll.add("first");
        Iterator<String> it = sll.iterator();
        sll.add("second");
        it.next();
    }
}