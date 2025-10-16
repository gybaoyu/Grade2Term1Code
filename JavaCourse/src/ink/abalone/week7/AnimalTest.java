package ink.abalone.week7;

public class AnimalTest {
    public static void main(String[] args) {
        Feeder feeder = new Feeder("Chen Guanyu");
        feeder.feedAnimal(new Lion());
        feeder.feedAnimal(new Monkey());
        feeder.feedAnimal(new Pigeon());
    }
}
abstract class Animal{
    public abstract void eat();
}
class Lion extends Animal{
    @Override
    public void eat() {
        System.out.println("Lion eat");
    }
}
class Monkey extends Animal{
    @Override
    public void eat() {
        System.out.println("Monkey eat");
    }
}
class Pigeon extends Animal{
    @Override
    public void eat() {
        System.out.println("Pigeon eat");
    }
}
class Feeder{
    private String name;
    public Feeder(String name) {
        this.name = name;
    }
    public void feedAnimal(Animal animal){
        System.out.println(name+" is feeding...");
        animal.eat();
    }

}