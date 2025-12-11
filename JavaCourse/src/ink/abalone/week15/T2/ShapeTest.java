package ink.abalone.week15.T2;

public class ShapeTest{
    public static void main(String[] args) {
        Square s = new Square(5.0);
        Circle c = new Circle(5.0);
        Shape<Square> shape1 = new Shape<>(s);
        Shape<Circle> shape2 = new Shape<>(c);
        shape1.printArea();
        shape2.printArea();
    }
}

class Shape<T extends GetAreaAble>{
    T shape;
    public Shape(T shape) {
        this.shape = shape;
    }
    void printArea(){
        System.out.println("Type: "+shape.getClass()+" Area: " + shape.getArea());
    }
}

class Square implements GetAreaAble{
    private double x;
    public Square(double x){
        this.x = x;
    }
    @Override
    public double getArea() {
        return x*x;
    }
}

class Circle implements GetAreaAble{
    private double radius;
    public Circle(double radius){
        this.radius = radius;
    }
    @Override
    public double getArea() {
        return Math.PI*radius*radius;
    }
}

interface GetAreaAble{
    double getArea();
}
