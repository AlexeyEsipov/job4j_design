package ru.job4j.iterator;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class BackwardArrayItTest {

    @Test
    public void whenMultiCallHasNextThenTrue() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {1, 2, 3}
        );
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
        assertSame(3, it.next());
    }

    @Test
    public void whenMultiCallHasNextThenNext() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {1, 2, 3}
        );
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
        assertSame(3, it.next());

    }

    @Test
    public void whenReadSequence() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {1, 2, 3}
        );
        assertSame(3, it.next());
        assertSame(2, it.next());
        assertSame(1, it.next());
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {}
        );
        it.next();
    }
}