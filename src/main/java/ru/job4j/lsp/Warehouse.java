package ru.job4j.lsp;

import java.util.List;

public class Warehouse extends AbstractStore {

    private final List<Food> foods;

    public Warehouse(List<Food> foods) {
        this.foods = foods;
    }

    @Override
    public void add(Food food) {
        if (distribution(food) < 25) {
           foods.add(food);
        }
    }

    @Override
    public List<Food> getFoods() {
        return foods;
    }

}
