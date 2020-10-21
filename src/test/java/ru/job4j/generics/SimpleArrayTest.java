package ru.job4j.generics;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Test
    public void whenAddElementToGetThisElement() {
        SimpleArray<String> sas = new SimpleArray<>(10);
        sas.add("first");
        sas.add("second");
        assertThat(sas.get(0), is("first"));
        assertThat(sas.get(1), is("second"));
    }

    @Test
    public void whenAddTwoNullElementThenFirstElementNotNull() {
        SimpleArray<String> sas = new SimpleArray<>(10);
        sas.add(null);
        sas.add(null);
        sas.add("third");
        assertThat(sas.get(0), is("third"));
        assertNull(sas.get(1));
        sas.add(null);
        sas.add(null);
        sas.add("four");
        assertThat(sas.get(0), is("third"));
        assertThat(sas.get(1), is("four"));
        assertNull(sas.get(2));
    }

    @Test
    public void whenSetElement() {
        SimpleArray<String> sas = new SimpleArray<>(10);
        sas.add("first");
        sas.add("second");
        assertThat(sas.get(0), is("first"));
        assertThat(sas.get(1), is("second"));
        sas.set(0, "new first");
        assertThat(sas.get(0), is("new first"));
        sas.set(1, "new second");
        assertThat(sas.get(1), is("new second"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetElementNotValidIndex() {
        SimpleArray<String> sas = new SimpleArray<>(10);
        sas.add("first");
        sas.set(1, "Error!!!");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetElementNotValidIndex() {
        SimpleArray<String> sas = new SimpleArray<>(10);
        sas.add("first");
        sas.get(1);
    }

    @Test
    public void whenRemoveElementThenGetNextElement() {
        SimpleArray<String> sas = new SimpleArray<>(10);
        sas.add("first");
        sas.add("second");
        sas.add("third");
        assertThat(sas.get(0), is("first"));
        assertThat(sas.get(1), is("second"));
        assertThat(sas.get(2), is("third"));
        sas.remove(1);
        assertThat(sas.get(1), is("third"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveElementThenIndexDecrease() {
        SimpleArray<String> sas = new SimpleArray<>(10);
        sas.add("first");
        sas.add("second");
        sas.add("third");
        sas.remove(1);
        sas.get(2);
    }

    @Test
    public void whenElementIsPresentThenIteratorValid() {
        SimpleArray<Integer> sas = new SimpleArray<>(10);
        sas.add(1);
        sas.add(2);
        sas.add(3);
        Iterator<Integer> it = sas.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenEmptyIteratorValid() {
        SimpleArray<Integer> sas = new SimpleArray<>(10);
        sas.add(1);
        sas.remove(0);
        Iterator<Integer> it = sas.iterator();
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyIteratorNextError() {
        SimpleArray<Integer> sas = new SimpleArray<>(10);
        sas.add(1);
        sas.remove(0);
        Iterator<Integer> it = sas.iterator();
        it.next();
    }
}