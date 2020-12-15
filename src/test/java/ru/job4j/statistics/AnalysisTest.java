package ru.job4j.statistics;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalysisTest {
    @Test
    public void whenAddDelChangeThenAddDelChange() {
        Analysis analysis = new Analysis();
        List<Analysis.User> previous = new ArrayList<>(List.of(
                new Analysis.User(1, "a"),
                new Analysis.User(2, "b"),
                new Analysis.User(3, "c"),
                new Analysis.User(4, "d")));
        List<Analysis.User> current = new ArrayList<>(List.of(
                new Analysis.User(3, "c1"),
                new Analysis.User(4, "d"),
                new Analysis.User(5, "e")));
        Analysis.Info info = analysis.diff(previous, current);
        assertThat(info.getAdded(), is(1)); // 5-e
        assertThat(info.getDeleted(), is(2)); // 1-a ; 2-b
        assertThat(info.getChanged(), is(1)); // 3-c -> 3-c1
    }
}