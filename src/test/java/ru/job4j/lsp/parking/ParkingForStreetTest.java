package ru.job4j.lsp.parking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ParkingForStreetTest {

    @Test
    void addValid() {
        Parking parking = new ParkingForStreet(10, 10, new ArrayList<>());
        assertThatThrownBy(() -> parking.add(new Truck(1)))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void addSedanValid() {
        Parking parking = new ParkingForStreet(6, 8, new ArrayList<>());
        assertThatThrownBy(() -> {
            for (int i = 0; i < 10; i++) {
                parking.add(new Sedan());
            }
        }).isInstanceOf(IllegalArgumentException.class);
    }
}