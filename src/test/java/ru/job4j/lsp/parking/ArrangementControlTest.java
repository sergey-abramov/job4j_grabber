package ru.job4j.lsp.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArrangementControlTest {

    @Test
    void arrangementForSedanAndTruckFull() {
        Parking parking = new ParkingForStreet(4, 3, new ArrayList<>());
        ArrangementControl control = new ArrangementControl(parking);
        control.arrangement(new Sedan());
        control.arrangement(new Sedan());
        for (int i = 0; i < 4; i++) {
            control.arrangement(new Truck(2));
        }
        assertThat(parking.getCount()).isEqualTo(List.of(0, 0));
    }

    @Test
    void arrangementForSedanAndTruckNotFull() {
        Parking parking = new ParkingForStreet(4, 3, new ArrayList<>());
        ArrangementControl control = new ArrangementControl(parking);
        control.arrangement(new Sedan());
        for (int i = 0; i < 3; i++) {
            control.arrangement(new Truck(2));
        }
        assertThat(parking.getCount()).isEqualTo(List.of(3, 0));
    }

    @Test
    void arrangementForSedanAndTruckSize() {
        Parking parking = new ParkingForStreet(4, 3, new ArrayList<>());
        ArrangementControl control = new ArrangementControl(parking);
        for (int i = 0; i < 3; i++) {
            control.arrangement(new Truck(3));
        }
        control.arrangement(new Truck(3));
        assertThat(parking.getCount()).isEqualTo(List.of(1, 0));
    }

    @Test
    void exceptionArrangement() {
        Parking parking = new ParkingForStreet(4, 3, new ArrayList<>());
        ArrangementControl control = new ArrangementControl(parking);
        control.arrangement(new Sedan());
        control.arrangement(new Sedan());
        for (int i = 0; i < 4; i++) {
            control.arrangement(new Truck(2));
        }
        assertThatThrownBy(() -> control.arrangement(new Truck(3)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void exceptionArrangementForSedan() {
        Parking parking = new ParkingForStreet(2, 3, new ArrayList<>());
        ArrangementControl control = new ArrangementControl(parking);
        control.arrangement(new Sedan());
        control.arrangement(new Sedan());
        for (int i = 0; i < 2; i++) {
            control.arrangement(new Truck(3));
        }
        assertThatThrownBy(() -> control.arrangement(new Sedan()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}