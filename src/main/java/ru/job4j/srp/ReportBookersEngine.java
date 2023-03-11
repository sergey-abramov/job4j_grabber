package ru.job4j.srp;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportBookersEngine implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final InMemoryCurrencyConverter converter;

    public ReportBookersEngine(Store store, DateTimeParser<Calendar> dateTimeParser,
                               InMemoryCurrencyConverter converter) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.converter = converter;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(" ")
                    .append(dateTimeParser.parse(employee.getHired())).append(" ")
                    .append(dateTimeParser.parse(employee.getFired())).append(" ")
                    .append(converter.convert(Currency.EUR, employee.getSalary(), Currency.RUB))
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
