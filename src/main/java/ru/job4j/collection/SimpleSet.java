package ru.job4j.collection;
import java.util.Iterator;

public class SimpleSet<T> implements Iterable<T> {

    private final SimpleArray<T> container = new SimpleArray<>();

    public void add(T volume) {
        Iterator<T> itSimpleArray = container.iterator();
        if (!itSimpleArray.hasNext()) {
            container.add(volume);
            return;
        }
        for (T el: container) {
            if (el.equals(volume)) {
                return;
            }
        }
        container.add(volume);
    }

    @Override
    public Iterator<T> iterator() {
        return container.iterator();
    }
}
