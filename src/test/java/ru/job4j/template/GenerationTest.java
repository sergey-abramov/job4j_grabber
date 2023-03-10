package ru.job4j.template;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class GenerationTest {

    @Test
    void produceThenName() {
        Map<String, String> map = new HashMap<>();
        Generation generation = new Generation();
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");
        assertThat("I am a Petr Arsentev, Who are you? ")
                .isEqualTo(generation.produce("I am a ${name}, Who are ${subject}? ", map));
    }

    @Test
    void produceThenInvalidName() {
        Map<String, String> map = new HashMap<>();
        Generation generation = new Generation();
        map.put("names", "Petr Arsentev");
        map.put("subject", "you");
        assertThatThrownBy(() -> generation.produce("I am a ${name}, Who are ${subject}? ", map))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void produceThenInvalidMap() {
        Map<String, String> map = new HashMap<>();
        Generation generation = new Generation();
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");
        map.put("subjects", "ee");
        assertThatThrownBy(() -> generation.produce("I am a ${name}, Who are ${subject}? ", map))
                .isInstanceOf(IllegalArgumentException.class);
    }

}