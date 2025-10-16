package ink.abalone.week7;

public class PetTest {
    public static void main(String[] args) {
        Master m = new Master();
        Dog d = new Dog();
        P p = new P();
        Rabbit r = new Rabbit();
        m.cure(d);
        m.cure(p);
        m.cure(r);
    }
}
class Pet{
    private double health = 40;
    public void toHospital(){

    }
    public double getHealth() {
        return health;
    }
    public void setHealth(double health) {
        this.health = health;
    }
}
class Dog extends Pet{
    @Override
    public void toHospital() {
        System.out.println("打针吃药");
        setHealth(60);
    }
}

class P extends Pet{
    @Override
    public void toHospital() {
        System.out.println("吃药疗养");
        setHealth(70);
    }
}
class Rabbit extends P{
    @Override
    public void toHospital() {
        System.out.println("打针吃药疗养");
        setHealth(80);
    }
}
class Master{
    public void cure(Pet p){
        p.toHospital();
    }
}