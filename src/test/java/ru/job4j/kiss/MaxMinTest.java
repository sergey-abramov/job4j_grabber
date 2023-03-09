package ru.job4j.kiss;

import org.junit.jupiter.api.Test;


import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MaxMinTest {

    @Test
    void max() {
        List<Integer> list = List.of(2, 3, 1, 4, 5);
        Comparator<Integer> comparator = Comparator.naturalOrder();
        MaxMin maxMin = new MaxMin();
        assertThat(maxMin.max(list, comparator)).isEqualTo(5);
    }

    @Test
    void min() {
        List<Integer> list = List.of(2, 3, 1, 4, 5);
        Comparator<Integer> comparator = Comparator.naturalOrder();
        MaxMin maxMin = new MaxMin();
        assertThat(maxMin.min(list, comparator)).isEqualTo(1);
    }
}