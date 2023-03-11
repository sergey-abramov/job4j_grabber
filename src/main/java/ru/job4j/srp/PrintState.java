package ru.job4j.srp;

import java.util.List;

public interface PrintState {
    String generate(List<String> list);

    void print(String print);
}
