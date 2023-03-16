package ru.job4j.lsp;

import java.util.List;

public class Shop extends AbstractStore {

    private final List<Food> foods;

    public Shop(List<Food> foods) {
        this.foods = foods;
    }

    @Override
    public void add(Food food) {
        if (distribution(food) >= 25) {
            if (distribution(food) > 75 && distribution(food) < 100) {
                food = new Food(food.getName(),
                        food.getExpiryDate(),
                        food.getCreateDate(),
                        food.getPrice() - food.getPrice() * food.getDiscount(),
                        food.getDiscount());
            }
            foods.add(food);
        }
    }

    @Override
    public List<Food> getFoods() {
        return foods;
    }
}
