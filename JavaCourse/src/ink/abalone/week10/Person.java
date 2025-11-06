package ink.abalone.week10;

import javax.swing.*;

public class Person {
    private String name;
    private int age;
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
class Test{
    public static void main(String[] args) throws CloneNotSupportedException {
        Person p1 = new Person("p1",18);
        Person p2 = new Person("p2",20);
        Dog d1 = new Dog("dog1",10,p1);
        Dog d2 = (Dog) d1.clone();
        System.out.println(d1);
        System.out.println(d2);
        System.out.println("changing d2...");
        d2.setAge(5);
        d2.setName("dog2");
        d2.setMaster(p2);
        System.out.println(d1);
        System.out.println(d2);

        d1.feedRequestByInnerClass();
        d1.feedRequestByLambda();

        JOptionPane.showMessageDialog(null,"waiting...");
        System.exit(0);
    }
}