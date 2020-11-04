package ru.job4j.iterator;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 4, 5));
        ListUtils.addAfter(input, 2, 2);
        assertThat(Arrays.asList(1, 3, 4, 2, 5), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenRemove() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 4, 1, 5));
        ListUtils.removeIf(input, e -> e < 4);
        assertThat(Arrays.asList(4, 5), Is.is(input));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 4, 1, 5));
        ListUtils.replaceIf(input, e -> e < 4, 12);
        assertThat(Arrays.asList(12, 12, 4, 12, 5), Is.is(input));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 4, 1, 5));
        List<Integer> elements = new ArrayList<>(Arrays.asList(0, 1, 4));
        ListUtils.removeAll(input, elements);
        assertThat(Arrays.asList(3, 5), Is.is(input));
    }
}