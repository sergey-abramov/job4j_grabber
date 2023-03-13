package ru.job4j.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.ocp.Employees;
import ru.job4j.ocp.ReportJSONEngine;
import ru.job4j.ocp.ReportXMLEngine;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ReportEngineTest {

    private static final MemStore STORE = new MemStore();
    private static final Calendar NOW = Calendar.getInstance();
    private final DateTimeParser<Calendar> parser = new ReportDateTimeParser();
    private final InMemoryCurrencyConverter converter = new InMemoryCurrencyConverter();

    @BeforeAll
    public static void add() {
        STORE.add(new Employee("Ivan", NOW, NOW, 100));
        STORE.add(new Employee("Vovan", NOW, NOW, 120));
        STORE.add(new Employee("Natali", NOW, NOW, 150));
        STORE.add(new Employee("Sergey", NOW, NOW, 230));
        STORE.add(new Employee("Alex", NOW, NOW, 180));
    }
    
    @Test
    public void whenOldGenerated() {
        Report engine = new ReportEngine(STORE, parser);
        StringBuilder expect = new StringBuilder().append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee worker : STORE.findBy(em -> true)) {
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
        Report engine = new ReportHREngine(STORE);
        List<Employee> sortList = STORE.findBy(e -> true);
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
        Report engine = new ReportBookersEngine(STORE, parser, converter);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee worker : STORE.findBy(e -> true)) {
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
        Report engine = new ReportDeveloperEngine(STORE, parser);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee worker : STORE.findBy(e -> true)) {
            expect.append(worker.getName()).append(" ")
                    .append(parser.parse(worker.getHired())).append(" ")
                    .append(parser.parse(worker.getFired())).append(" ")
                    .append(worker.getSalary())
                    .append(System.lineSeparator());
        }
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenJsonGenerated() {
        Gson gson = new GsonBuilder().create();
        ReportJSONEngine engine = new ReportJSONEngine(gson, STORE, parser);
        String expect = gson.toJson(STORE.findBy(e -> true));
        assertThat(engine.generate(em -> true)).isEqualTo(expect);
    }

    @Test
    public void whenXMLGenerated() throws Exception {
        JAXBContext context = JAXBContext.newInstance(Employees.class);
        ReportXMLEngine engine = new ReportXMLEngine(context, STORE, parser);
        String expected = "";
        try (StringWriter writer = new StringWriter()) {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(new Employees(STORE.findBy(e -> true)), writer);
            expected = writer.getBuffer().toString();
        }
        assertThat(engine.generate(em -> true)).isEqualTo(expected);
    }
}