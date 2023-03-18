package ru.job4j.lsp;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class ControlQualityTest {

    @Test
    void distribution() {
        Food apple = new Food("apple",
                LocalDate.of(2023, 3, 30),
                LocalDate.of(2023, 3, 15),
                35.0, 0.1);
        Store warehouse = new Warehouse(new ArrayList<>());
        Store shop = new Shop(new ArrayList<>());
        ControlQuality cont = new ControlQuality(List.of(warehouse, shop));
        cont.distribute(apple);
        assertThat(warehouse.getFoods()).isEqualTo(List.of(apple));

    }

    @Test
    void distributionForShop() {
        Food apple = new Food("apple",
                LocalDate.of(2023, 3, 30),
                LocalDate.of(2023, 3, 1),
                35.0, 0.1);
        Store warehouse = new Warehouse(new ArrayList<>());
        Store shop = new Shop(new ArrayList<>());
        ControlQuality cont = new ControlQuality(List.of(warehouse, shop));
        cont.distribute(apple);
        assertThat(warehouse.getFoods()).isEqualTo(List.of());
        assertThat(shop.getFoods()).isEqualTo(List.of(apple));
    }

    @Test
    void distributionForShopDiscount() {
        Food apple = new Food("apple",
                LocalDate.of(2023, 3, 19),
                LocalDate.of(2023, 3, 1),
                35.0, 0.1);
        Store warehouse = new Warehouse(new ArrayList<>());
        Store shop = new Shop(new ArrayList<>());
        ControlQuality cont = new ControlQuality(List.of(warehouse, shop));
        cont.distribute(apple);
        assertThat(warehouse.getFoods()).isEqualTo(List.of());
        assertThat(shop.getFoods().get(0)).isEqualTo(new Food("apple",
                LocalDate.of(2023, 3, 19),
                LocalDate.of(2023, 3, 1),
                31.50, 0.1));

    }

    @Test
    void distributionForShopAndWarehouse() {
        Food apple = new Food("apple",
                LocalDate.of(2023, 3, 30),
                LocalDate.of(2023, 3, 1),
                35.0, 0.1);
        Food milk = new Food("milk",
                LocalDate.of(2023, 3, 25),
                LocalDate.of(2023, 3, 10),
                35.0, 0.1);
        Food pizza = new Food("pizza",
                LocalDate.of(2023, 7, 30),
                LocalDate.of(2023, 3, 1),
                35.0, 0.1);
        Store warehouse = new Warehouse(new ArrayList<>());
        Store shop = new Shop(new ArrayList<>());
        ControlQuality cont = new ControlQuality(List.of(warehouse, shop));
        cont.distribute(apple);
        cont.distribute(milk);
        cont.distribute(pizza);
        assertThat(warehouse.getFoods()).isEqualTo(List.of(pizza));
        assertThat(shop.getFoods()).isEqualTo(List.of(apple, milk));
    }

    @Test
    void distributionForTrash() {
        Food apple = new Food("apple",
                LocalDate.of(2023, 3, 10),
                LocalDate.of(2023, 3, 1),
                35.0, 0.1);
        Store warehouse = new Warehouse(new ArrayList<>());
        Trash trash = new Trash(new ArrayList<>());
        ControlQuality cont = new ControlQuality(List.of(warehouse, trash));
        cont.distribute(apple);
        assertThat(warehouse.getFoods()).isEqualTo(List.of());
        assertThat(trash.getFoods()).isEqualTo(List.of(apple));
    }
}