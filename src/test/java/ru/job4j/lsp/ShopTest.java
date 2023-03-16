package ru.job4j.lsp;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ShopTest {

    @Test
    void add() {
        Food apple = new Food("apple",
                LocalDate.of(2023, 3, 30),
                LocalDate.of(2023, 3, 15),
                35.0, 0.1);
        Food pizza = new Food("pizza",
                LocalDate.of(2023, 4, 30),
                LocalDate.of(2023, 1, 1),
                35.0, 0.1);
        Food cola = new Food("cola",
                LocalDate.of(2023, 3, 20),
                LocalDate.of(2023, 3, 10),
                35.0, 0.1);
        Store shop = new Shop(new ArrayList<>());
        shop.add(apple);
        shop.add(cola);
        shop.add(pizza);
        assertThat(shop.getFoods()).isEqualTo(List.of(cola, pizza));
    }

    @Test
    void addAndDiscount() {
        Food pizza = new Food("pizza",
                LocalDate.of(2023, 3, 20),
                LocalDate.of(2023, 1, 1),
                350.0, 0.1);
        Store shop = new Shop(new ArrayList<>());
        shop.add(pizza);
        assertThat(shop.getFoods()).isEqualTo(List.of(new Food("pizza",
                LocalDate.of(2023, 3, 20),
                LocalDate.of(2023, 1, 1),
                315.0, 0.1)));
    }
}