package ru.job4j.collection;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class SimpleArrayTest {

    private SimpleList<Integer> list;

    @Before
    public void initData() {
        list = new SimpleArray<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThenSizeIncrease() {
        assertEquals(3, list.size());
    }

    @Test
    public void whenAddAndGetByCorrectIndex() {
        assertEquals(Integer.valueOf(1), list.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetByIncorrectIndexThenGetException() {
        list.get(5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAndAndGetByIncorrectIndexThenGetException() {
        SimpleList<Integer> list = new SimpleArray<>(10);
        list.add(5);
        list.get(5);
    }

    @Test
    public void whenRemoveThenGetValueAndSizeDecrease() {
        assertEquals(3, list.size());
        assertEquals(Integer.valueOf(2), list.remove(1));
        assertEquals(2, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveByIncorrectIndexThenGetException() {
        list.remove(5);
    }

    @Test
    public void whenRemoveThenMustNotBeEmpty() {
        list.remove(1);
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(3), list.get(1));
    }

    @Test
    public void whenAddNullThenMustBeSameBehavior() {
        list = new SimpleArray<>(3);
        list.add(null);
        list.add(null);
        assertEquals(2, list.size());
        assertNull(list.get(0));
        assertNull(list.get(1));
    }

    @Test
    public void whenSetThenGetOldValueAndSizeNotChanged() {
        assertEquals(Integer.valueOf(2), list.set(1, 22));
        assertEquals(3, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetByIncorrectIndexThenGetException() {
        list.set(5, 22);
    }

    @Test
    public void whenGetIteratorFromEmptyListThenHasNextReturnFalse() {
        list = new SimpleArray<>(5);
        assertFalse(list.iterator().hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptyListThenNextThrowException() {
        list = new SimpleArray<>(5);
        list.iterator().next();
    }

    @Test
    public void whenGetIteratorTwiceThenStartAlwaysFromBeginning() {
        assertEquals(Integer.valueOf(1), list.iterator().next());
        assertEquals(Integer.valueOf(1), list.iterator().next());
    }

    @Test
    public void whenCheckIterator() {
        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(1), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(2), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(3), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void whenNoPlaceThenMustIncreaseCapacity() {
        assertSame(3, list.size());
        IntStream.range(3, 10).forEach(v -> list.add(v));
        assertSame(10, list.size());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        Iterator<Integer> iterator = list.iterator();
        list.add(4);
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenRemoveAfterGetIteratorThenMustBeException() {
        list.add(0);
        Iterator<Integer> iterator = list.iterator();
        list.remove(0);
        iterator.next();
    }

}