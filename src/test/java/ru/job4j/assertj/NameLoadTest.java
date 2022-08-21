package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkMap() {
        NameLoad nameLoad = new NameLoad();
        String name = "Key=value";
        nameLoad.parse(name);
        assertThat(nameLoad.getMap()).containsEntry("Key", "value");
    }
}