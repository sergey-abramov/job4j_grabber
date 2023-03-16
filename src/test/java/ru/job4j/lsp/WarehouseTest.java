package ru.job4j.lsp;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WarehouseTest {

    @Test
    void add() {
        Food apple = new Food("apple",
                LocalDate.of(2023, 3, 30),
                LocalDate.of(2023, 3, 15),
                35.0, 0.1);
        Food pizza = new Food("apple",
                LocalDate.of(2023, 8, 30),
                LocalDate.of(2023, 3, 15),
                35.0, 0.1);
        Food cola = new Food("apple",
                LocalDate.of(2023, 3, 20),
                LocalDate.of(2023, 3, 10),
                35.0, 0.1);
        Store warehouse = new Warehouse(new ArrayList<>());
        warehouse.add(apple);
        warehouse.add(cola);
        warehouse.add(pizza);
        assertThat(warehouse.getFoods()).isEqualTo(List.of(apple, pizza));
    }

}