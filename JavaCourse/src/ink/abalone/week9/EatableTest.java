package ink.abalone.week9;

interface Eatable {
    public void eat();
}
class Fish implements Eatable {
    @Override
    public void eat() {
        System.out.println("Fish is eating");
    }
}
class Person implements Eatable {
    @Override
    public void eat() {
        System.out.println("Person is eating");
    }
}
class Cat implements Eatable {
    @Override
    public void eat() {
        System.out.println("Cat is eating");
    }
}

public class EatableTest {
    public static void feed(Eatable e){
        e.eat();
    }
    public static void main(String[] args) {
        Eatable[] e = new Eatable[3];
        e[0] = new Person();
        e[1] = new Cat();
        e[2] = new Fish();
        for (Eatable eatable : e) {
            eatable.eat();
        }
        feed(new Person());
    }
}