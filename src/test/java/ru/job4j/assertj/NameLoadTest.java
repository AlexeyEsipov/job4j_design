package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        String string = "no data";
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(String.format("%s", string));
    }

    @Test
    void checkMap() {
        NameLoad nameLoad = new NameLoad();
        String name = "Key=value";
        nameLoad.parse(name);
        assertThat(nameLoad.getMap()).containsEntry("Key", "value");
    }

    @Test
    void whenParseNameWithoutSymbol() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[]{"1Name"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("symbol")
                .hasMessageContaining("1Name");

    }

    @Test
    void checkIndexOf() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[]{"-Key="};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: -Key= does not contain a value");
    }
}