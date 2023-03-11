package ru.job4j.srp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ReportEngineTest {

    private static final MemStore store = new MemStore();
    private static final Calendar now = Calendar.getInstance();
    private final DateTimeParser<Calendar> parser = new ReportDateTimeParser();
    private final InMemoryCurrencyConverter converter = new InMemoryCurrencyConverter();

    @BeforeAll
    public static void add() {
        store.add(new Employee("Ivan", now, now, 100));
        store.add(new Employee("Vovan", now, now, 120));
        store.add(new Employee("Natali", now, now, 150));
        store.add(new Employee("Sergey", now, now, 230));
        store.add(new Employee("Alex", now, now, 180));
    }



    @Test
    public void whenOldGenerated() {
        Report engine = new ReportEngine(store, parser);
        StringBuilder expect = new StringBuilder().append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee worker : store.findBy(em -> true)) {
               expect.append(worker.getName()).append(" ")
                    .append(parser.parse(worker.getHired())).append(" ")
                    .append(parser.parse(worker.getFired())).append(" ")
                    .append(worker.getSalary())
                    .append(System.lineSeparator());
        }
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenHRGenerated() {
        Report engine = new ReportHREngine(store);
        List<Employee> sortList = store.findBy(e -> true);
        sortList.sort(Comparator.comparingDouble(Employee::getSalary));
        StringBuilder expect = new StringBuilder().append("Name; Salary;")
                .append(System.lineSeparator());
        for (Employee worker : sortList) {
            expect.append(worker.getName()).append(" ")
                    .append(worker.getSalary())
                    .append(System.lineSeparator());
        }
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenBookerGenerated() {
        Report engine = new ReportBookersEngine(store, parser, converter);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee worker : store.findBy(e -> true)) {
               expect.append(worker.getName()).append(" ")
                    .append(parser.parse(worker.getHired())).append(" ")
                    .append(parser.parse(worker.getFired())).append(" ")
                    .append(converter.convert(Currency.EUR, worker.getSalary(), Currency.RUB))
                    .append(System.lineSeparator());
        }
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenDeveloperGenerated() {
        Report engine = new ReportDeveloperEngine(store, parser);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee worker : store.findBy(e -> true)) {
            expect.append(worker.getName()).append(" ")
                    .append(parser.parse(worker.getHired())).append(" ")
                    .append(parser.parse(worker.getFired())).append(" ")
                    .append(worker.getSalary())
                    .append(System.lineSeparator());
        }
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}