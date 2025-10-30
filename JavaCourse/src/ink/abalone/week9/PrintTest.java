package ink.abalone.week9;

public class PrintTest{
    public static void main(String[] args) {
        Rectangle r = new Rectangle(2.3,5.7);
        Square s = new Square(2);
        r.printMyWay();
        s.printMyWay();
    }
}

interface PrintX {
    void printMyWay();
}

class Rectangle implements PrintX {
    protected double width;
    protected double length;
    protected double calculateSize(){
        return width * length;
    }
    protected double calculatePerimeter(){
        return (width + length)*2;
    }
    public Rectangle(double width, double length){
        this.width = width;
        this.length = length;
    }

    @Override
    public void printMyWay() {
        System.out.println("Rectangle{" +
                "width=" + width +
                ", length=" + length +
                ", area=" + calculateSize() +
                ", perimeter=" + calculatePerimeter() +
                '}');
    }
}
class Square extends Rectangle implements PrintX {
    private double calculateDiagonal(){
        return width * Math.sqrt(2);
    }
    public Square(double x) {
        super(x, x);
    }

    @Override
    public void printMyWay() {
        System.out.println("Square{" +
                "width=" + width +
                ", length=" + length +
                ", area=" + calculateSize() +
                ", perimeter=" + calculatePerimeter() +
                ", diagonal=" + calculateDiagonal() +
                '}');
    }
}