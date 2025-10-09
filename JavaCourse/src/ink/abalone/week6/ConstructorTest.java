package ink.abalone.week6;

import java.util.Random;

public class ConstructorTest {
    public static void main(String[] args) {
         var staff = new Employee[3];
         staff[0] = new Employee("Harry",40000);
         staff[1] = new Employee(60000);
         staff[2] = new Employee();
        for (Employee e : staff) {
            System.out.println(e);
        }
    }
}
class Employee{
    private static int nextId;
    private int id;
    private String name="";
    private double salary;
    static{
        var generator = new Random();
        nextId = generator.nextInt(10000);
    }
    {
        id = nextId;
        nextId++;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    public Employee(double salary) {
        this("Employee #"+nextId, salary);
    }
    public Employee() {
    }
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public double getSalary() {
        return salary;
    }
}