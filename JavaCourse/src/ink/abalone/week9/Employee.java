package ink.abalone.week9;

import java.util.Arrays;

public class Employee implements Comparable<Employee>{
    private String name;
    private double salary;
    public Employee(String name,double salary){
        this.name = name;
        this.salary = salary;
    }
    public String getName() {
        return name;
    }
    public double getSalary() {
        return salary;
    }


    public void raiseSalary(double byPercent){
        double raise = salary*byPercent/100;
        salary += raise;
    }
    @Override
    public int compareTo(Employee o) {
        return Double.compare(salary, o.salary);
    }
    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
class EmployeeSortTest{
    public static void main(String[] args) {
        var staff = new Employee[3];
        staff[0] = new Employee("John Doe", 60000);
        staff[1] = new Employee("Jane Doe", 50000);
        staff[2] = new Employee("Jack Doe", 70000);
        Arrays.sort(staff);
        for (Employee e : staff) {
            System.out.println(e);
        }
    }
}
