package ru.job4j.tdd;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class Cinema3D implements Cinema {
    @Override
    public Ticket3D buy(Account cinema, int one, int count, Calendar date) {
        return null;
    }

    @Override
    public void add(Session session) {

    }

    @Override
    public List<Session> find(Predicate<Session> predicate) {
        return null;
    }
}
