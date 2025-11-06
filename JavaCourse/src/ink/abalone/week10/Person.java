package ink.abalone.week10;

import javax.swing.*;

public class Person implements Cloneable{
    private String name;
    private int age;
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
    @Override
    public Person clone() throws CloneNotSupportedException {
        return (Person) super.clone();
    }
}
class Test{
    public static void main(String[] args) throws CloneNotSupportedException {
        Person p1 = new Person("p1",18);
        Dog d1 = new Dog("dog1",10,p1);
        Dog d2 = (Dog) d1.clone();
        System.out.println(d1);
        System.out.println(d2);
        System.out.println("changing d2...");
        d2.setAge(5);
        d2.setName("dog2");
        p1.setAge(1);
        p1.setName("aaa");
        System.out.println(d1);
        System.out.println(d2);
        d1.feedRequestByInnerClass();
        d1.feedRequestByLambda();
        JOptionPane.showMessageDialog(null,"waiting...");
        System.exit(0);
    }
}