package ink.abalone.week8.T1;

import java.time.LocalDate;
import java.util.Objects;

public class Employee extends Person {
    private double salary;
    private LocalDate hireDay;
    public Employee(String name, double salary, int year,int month,int day) {
        super(name);
        this.salary = salary;
        this.hireDay = LocalDate.of(year, month, day);
    }
    @Override
    public String getDescription() {
        return String.format("an employee with salary %.2f", salary);
    }

    public String getName() {
        return super.getName();
    }
    public double getSalary() {
        return salary;
    }
    public LocalDate getHireDay() {
        return hireDay;
    }
    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }
    @Override
    public String toString() {
        return "Employee{" +
                "name='" + super.getName() + '\'' +
                ", salary=" + salary +
                ", hireDay=" + hireDay +
                '}';
    }
    @Override
    public int hashCode() {
        return Objects.hash(getName(),salary,hireDay);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getName(),employee.getName()) &&
                Double.compare(salary, employee.salary) == 0 &&
                Objects.equals(hireDay, employee.hireDay);
    }
}

