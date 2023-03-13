package ru.job4j.ocp;

import ru.job4j.srp.Employee;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employees {

    @XmlElementWrapper(name = "list")
    @XmlElement(name = "employee")
    private List<Employee> list;

    public Employees() {
    }

    public Employees(List<Employee> list) {
        this.list = list;
    }

    public List<Employee> getList() {
        return list;
    }

    public void setList(List<Employee> list) {
        this.list = list;
    }
}
