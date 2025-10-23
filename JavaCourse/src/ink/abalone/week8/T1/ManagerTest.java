package ink.abalone.week8.T1;

public class ManagerTest {
    public static void printSalary(Employee e){
        System.out.println(e.getSalary());
    }
    public static void main(String[] args) {
        var boss = new Manager("Carl Cracker",80000,1987,12,15);
        boss.setBonus(5000);
        var staff = new Employee[3];
        staff[0]=boss;
        staff[1]=new Employee("Harry Hacker",50000,1989,10,1);
        staff[2]=new Employee("Tommy Tester",40000,1990,3,15);
        for (Employee employee : staff) {
            System.out.println(employee);
        }
        Employee e1 = new Manager("Car1",8000,1997,11,15);
        if (e1 instanceof  Manager){
            ((Manager) e1).setBonus(900);
        }
        System.out.println(e1.getSalary());
        printSalary(e1);
    }
}
