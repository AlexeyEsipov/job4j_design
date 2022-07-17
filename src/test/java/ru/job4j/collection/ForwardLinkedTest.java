package ru.job4j.collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

class ForwardLinkedTest {
    private ForwardLinked<Integer> linked;

    @BeforeEach
    void init() {
        linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.add(3);
        linked.add(4);
    }

    @AfterEach
    void after() {
        linked = null;
    }

    @Test
    void whenDeleteFirst() {
        assertThat(linked.deleteFirst()).isEqualTo(1);
        assertThat(linked.deleteFirst()).isEqualTo(2);
        assertThat(linked.deleteFirst()).isEqualTo(3);
        assertThat(linked.deleteFirst()).isEqualTo(4);
        assertThatThrownBy(linked.iterator()::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenDeleteEmptyLinked() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        assertThatThrownBy(linked::deleteFirst)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenMultiDelete() {
        linked.deleteFirst();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next()).isEqualTo(2);
    }

    @Test
    void whenAddThenIterator() {
        linked.add(1);
        linked.add(2);
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next()).isEqualTo(1);
        assertThat(it.next()).isEqualTo(2);
    }

      @Test
    void whenDeleteLast() {
        linked.add(1);
        linked.add(2);
        linked.deleteLast();
        linked.deleteLast();
        assertThatThrownBy(linked.iterator()::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void whenSize0ThenReturnFalse() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        assertThat(linked.revert()).isFalse();
    }

    @Test
    void whenSize1ThenReturnFalse() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        assertThat(linked.revert()).isFalse();
    }

    @Test
    void whenAddAndRevertTrue() {
        assertThat(linked).hasSize(4).containsSequence(1, 2, 3, 4);
        assertThat(linked.revert()).isTrue();
        assertThat(linked).hasSize(4).containsSequence(4, 3, 2, 1);
    }
}