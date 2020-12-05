package ru.job4j.mailmerge;

import org.junit.Test;


import java.util.Arrays;
import java.util.LinkedHashSet;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MergeSetUserTest {

    @Test
    public void whenDuplicatesThenWhiteUser() {
        SetUser sourceUser = new SetUser();
        sourceUser.addUser(new User("user1",
                new LinkedHashSet<>(Arrays.asList("em1", "em2", "em3"))));
        sourceUser.addUser(new User("user2",
                new LinkedHashSet<>(Arrays.asList("em4", "em5", "em6"))));
        sourceUser.addUser(new User("user3",
                new LinkedHashSet<>(Arrays.asList("em10", "em8", "em9"))));
        sourceUser.addUser(new User("user4",
                new LinkedHashSet<>(Arrays.asList("em10", "em3", "em12"))));
        sourceUser.addUser(new User("user5",
                new LinkedHashSet<>(Arrays.asList("em21", "em24", "em23"))));
        sourceUser.addUser(new User("user6",
                new LinkedHashSet<>(Arrays.asList("em24", "em5", "em26"))));
        sourceUser.addUser(new User("user7",
                new LinkedHashSet<>(Arrays.asList("em27", "em28", "em29"))));
        sourceUser.addUser(new User("user8",
                new LinkedHashSet<>(Arrays.asList("em30", "em27", "em32"))));
        sourceUser.addUser(new User("user9",
                new LinkedHashSet<>(Arrays.asList("em40", "em41", "em42"))));
        MergeSetUser mergeSetUser = new MergeSetUser();
        LinkedHashSet<String> expected = new LinkedHashSet<>(Arrays.asList("em1",
                                    "em2", "em3", "em10", "em12", "em8", "em9"));
        assertThat(mergeSetUser.clearDuplicates(sourceUser).get(0).getName(), is("user1"));
        assertEquals(mergeSetUser.clearDuplicates(sourceUser).get(0).getMailUser(), expected);
    }
}