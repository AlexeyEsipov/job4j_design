package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    @Test
    public void whenAddThenReplaceIsRight() {
        UserStore userStore = new UserStore();
        User alex = new User("Alex");
        User ivan = new User("Ivan");
        userStore.add(alex);
        assertTrue(userStore.replace("Alex", ivan));
    }

    @Test
    public void whenDeleteThenReplaceIsFalse() {
        UserStore userStore = new UserStore();
        User alex = new User("Alex");
        User ivan = new User("Ivan");
        userStore.add(alex);
        userStore.delete("Alex");
        assertFalse(userStore.replace("Alex", ivan));
    }

    @Test
    public void whenDeleteThenIdNotFound() {
        UserStore userStore = new UserStore();
        User alex = new User("Alex");
        User ivan = new User("Ivan");
        userStore.add(alex);
        userStore.add(ivan);
        userStore.delete("Ivan");
        assertNull(userStore.findById("Ivan"));
    }

    @Test
    public void whenAddThenIdFound() {
        UserStore userStore = new UserStore();
        User alex = new User("Alex");
        userStore.add(null);
        userStore.add(alex);
        assertThat(userStore.findById("Alex"), is(alex));
    }

    @Test
    public void whenAddNullThenIdFound() {
        UserStore userStore = new UserStore();
        User alex = new User("Alex");
        userStore.add(null);
        userStore.add(alex);
        assertThat(userStore.findById("Alex"), is(alex));
    }
}