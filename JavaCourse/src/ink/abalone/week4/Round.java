package ink.abalone.week4;

public class Round {
    double r;
    double size;

    public Round(double r) {
        this.r = r;
    }
    public double getR() {
        return r;
    }
    public void setR(double r) {
        this.r = r;
    }
    public double getSize() {
        return size;
    }
    public void calculateSize() {
        this.size = this.r*this.r*3.14159;
    }

    public static void main(String[] args) {
        Round r1 = new Round(1.1);
        System.out.println(r1.getR());
        System.out.println(r1.getSize());
        r1.calculateSize();
        System.out.println(r1.getSize());
        r1.setR(2.2);
        r1.calculateSize();
        System.out.println(r1.getR());
        System.out.println(r1.getSize());
    }
}

