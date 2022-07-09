package ru.job4j.collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ForwardLinkedTest {
    private ForwardLinked<Integer> linked;

    @Before
    public void init() {
        linked = new ForwardLinked<>();
    }

    @After
    public void after() {
        linked = null;
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFirst() {
        linked.addLast(1);
        linked.addLast(2);
        linked.deleteFirst();
        linked.deleteFirst();
        linked.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteLast() {
        linked.addLast(1);
        linked.addLast(2);
        linked.deleteLast();
        linked.deleteLast();
        linked.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteEmptyLinked() {
        linked.deleteFirst();
    }

    @Test
    public void whenSize0ThenReturnFalse() {
        assertFalse(linked.revert());
    }

    @Test
    public void whenMultiDelete() {
        linked.addLast(1);
        linked.addLast(2);
        linked.deleteFirst();
        Iterator<Integer> it = linked.iterator();
        assertSame(2, it.next());
    }

    @Test
    public void whenAddThenIterator() {
        linked.addLast(1);
        linked.addLast(2);
        Iterator<Integer> it = linked.iterator();
        assertSame(1, it.next());
        assertSame(2, it.next());
    }

    @Test
    public void whenAddAndRevertThenIterator() {
        linked.addLast(1);
        linked.addLast(2);
        linked.addLast(3);
        linked.addLast(4);
        linked.addLast(5);
        assertTrue(linked.revert());
        Iterator<Integer> it = linked.iterator();
        assertSame(5, it.next());
        assertSame(4, it.next());
        assertSame(3, it.next());
        assertSame(2, it.next());
        assertSame(1, it.next());
    }
}