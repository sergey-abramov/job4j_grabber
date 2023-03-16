package ru.job4j.lsp;

import java.util.List;

public class Trash extends AbstractStore {
    private final List<Food> list;

    public Trash(List<Food> list) {
        this.list = list;
    }

    @Override
    public void add(Food food) {
        if (distribution(food) >= 100) {
            list.add(food);
        }
    }

    @Override
    public List<Food> getFoods() {
        return list;
    }

}
