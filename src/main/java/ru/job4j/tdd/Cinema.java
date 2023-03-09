package ru.job4j.tdd;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public interface Cinema {
    public Ticket3D buy(Account cinema, int row, int column, Calendar date);

    public void add(Session session);

    public List<Session> find(Predicate<Session> predicate);
}
