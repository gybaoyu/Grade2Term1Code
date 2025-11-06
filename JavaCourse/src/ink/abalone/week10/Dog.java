package ink.abalone.week10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dog implements Cloneable{
    private String name;
    private int age;
    private Person master;

    Dog(String name, int age, Person master) {
        this.name = name;
        this.age = age;
        this.master = master;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Dog d = (Dog) super.clone();
        d.name = name;
        d.master = master;
        return d;
    }

    public void feedRequestByInnerClass(){
        Timer t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("内部类方法...");
                System.out.println(master.getName()+"请喂食");
                Toolkit.getDefaultToolkit().beep();
            }
        });
        t.start();
    }

    public void feedRequestByLambda(){
        Timer t = new Timer(1000,(e)-> {
            System.out.println("lambda方法...");
            System.out.println(master.getName()+"请喂食");
            Toolkit.getDefaultToolkit().beep();
        });
        t.start();
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setMaster(Person master) {
        this.master = master;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", master=" + master +
                '}';
    }
}
