package ru.job4j.collection;

import static org.junit.Assert.assertSame;
import org.junit.Before;
import org.junit.Test;
import java.util.NoSuchElementException;

public class SimpleQueueTest {
    private SimpleQueue<Integer> queue;

    @Before
    public void setUp() {
        queue = new SimpleQueue<>();
    }

    @Test
    public void whenPushPoll() {
        queue.push(1);
        assertSame(1, queue.poll());
    }

    @Test
    public void when2PushPoll() {
        queue.push(1);
        queue.push(2);
        assertSame(1, queue.poll());
    }

    @Test
    public void when2PushPollPushPoll() {
        queue.push(1);
        queue.poll();
        queue.push(2);
        assertSame(2, queue.poll());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyPoll() {
        queue.poll();
    }

    @Test
    public void whenPushPushPollAndPush() {
        queue.push(1);
        queue.push(2);
        queue.poll();
        queue.push(3);
        assertSame(2, queue.poll());
    }
}