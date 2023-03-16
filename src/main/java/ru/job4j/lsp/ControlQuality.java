package ru.job4j.lsp;

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
}
