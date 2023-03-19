package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {

    private final List<Store> stories;

    public ControlQuality(List<Store> store) {
        this.stories = store;
    }

    public void distribute(Food food) {
        for (Store s : stories) {
            s.add(food);
        }
    }

    public void remove() {
        List<Food> list = new ArrayList<>();
        for (Store store : stories) {
            list.addAll(store.getFoods());
        }
        stories.clear();
        for (Food food : list) {
            distribute(food);
        }
    }
}
