package ru.job4j.lsp;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class TrashTest {

    @Test
    void add() {
        Food apple = new Food("apple",
                LocalDate.of(2023, 3, 30),
                LocalDate.of(2023, 3, 15),
                35.0, 0.1);
        Food pizza = new Food("pizza",
                LocalDate.of(2023, 2, 25),
                LocalDate.of(2023, 1, 1),
                35.0, 0.1);
        Food cola = new Food("cola",
                LocalDate.of(2023, 3, 15),
                LocalDate.of(2023, 3, 10),
                35.0, 0.1);
        Store trash = new Trash(new ArrayList<>());
        trash.add(apple);
        trash.add(cola);
        trash.add(pizza);
        assertThat(trash.getFoods()).isEqualTo(List.of(cola, pizza));
    }
}