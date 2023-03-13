package ru.job4j.ocp;

import com.google.gson.Gson;
import ru.job4j.srp.DateTimeParser;
import ru.job4j.srp.Employee;
import ru.job4j.srp.Report;
import ru.job4j.srp.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportJSONEngine implements Report {

    private final Gson gson;
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public ReportJSONEngine(Gson gson, Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.gson = gson;
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return gson.toJson(store.findBy(filter));
    }
}
