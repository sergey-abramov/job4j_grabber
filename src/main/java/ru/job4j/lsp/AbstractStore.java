package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {

    private final List<Food> foods = new ArrayList<>();

    @Override
    public void add(Food food) {
        foods.add(food);
    }

    @Override
    public List<Food> getFoods() {
        return foods;
    }

}
