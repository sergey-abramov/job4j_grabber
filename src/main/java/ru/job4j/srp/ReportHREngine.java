package ru.job4j.srp;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ReportHREngine implements Report {
    private final Store store;

    public ReportHREngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        List<Employee> sortList = store.findBy(filter);
        sortList.sort(Comparator.comparingDouble(Employee::getSalary));
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : sortList) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
