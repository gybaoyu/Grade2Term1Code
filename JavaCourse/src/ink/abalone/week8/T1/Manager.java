package ink.abalone.week8.T1;

import java.util.Objects;

public class Manager extends Employee {
    private double bonus;
    public Manager(String name, double salary, int year, int month, int day) {
        super(name, salary, year, month, day);
        bonus = 0;
    }

    @Override
    public double getSalary() {
        return super.getSalary()+bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public boolean equals(Object o) {
        if (super.equals(o))return false;
        var other = (Manager) o;
        return bonus == other.bonus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bonus);
    }

    public double getBonus() {
        return bonus;
    }
}
