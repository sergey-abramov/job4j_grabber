package ru.job4j.lsp;

import java.time.LocalDate;
import java.util.Objects;

public class Food {
    private final String name;
    private final LocalDate expiryDate;
    private final LocalDate createDate;
    private final double price;
    private final double discount;

    public Food(String name, LocalDate expiryDate,
                LocalDate createDate, double price, double discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return Double.compare(food.price, price)
                == 0 && Double.compare(food.discount, discount)
                == 0 && Objects.equals(name, food.name)
                && Objects.equals(expiryDate, food.expiryDate)
                && Objects.equals(createDate, food.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expiryDate, createDate, price, discount);
    }
}
