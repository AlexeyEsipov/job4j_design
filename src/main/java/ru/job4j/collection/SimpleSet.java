package ru.job4j.collection;
import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Iterable<T> {

    private final SimpleArray<T> container = new SimpleArray<>();

    public void add(T volume) {
        if (!contains(volume)) {
            container.add(volume);
        }
    }

    private boolean contains(T volume) {
        Iterator<T> itSimpleArray = container.iterator();
        if (!itSimpleArray.hasNext()) {
            return false;
        }
        for (T el: container) {
            if (Objects.equals(el, volume)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return container.iterator();
    }
}
