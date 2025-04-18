package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 2, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemove() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 4, 1, 5));
        ListUtils.removeIf(input, e -> e < 4);
        assertThat(input).hasSize(2).containsSequence(4, 5);
    }

    @Test
    void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 4, 1, 5));
        ListUtils.replaceIf(input, e -> e < 4, 12);
        assertThat(input).hasSize(5).containsSequence(12, 12, 4, 12, 5);
    }

    @Test
    void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 4, 1, 5));
        List<Integer> elements = new ArrayList<>(Arrays.asList(0, 1, 4));
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(2).containsSequence(3, 5)
                .containsExactly(3, 5);
    }
}