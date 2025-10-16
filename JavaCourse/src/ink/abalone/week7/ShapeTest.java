package ink.abalone.week7;

public class ShapeTest{
    public static void main(String[] args) {
        var shapes = new Shape[3];
        shapes[0] = new Circle(3);
        shapes[1] = new Rectangle(2,3);
        shapes[2] = new Triangle(3,4);
        for (Shape shape : shapes) {
            System.out.println(shape.getClass().toString()+" "+shape.calculateArea());
        }
    }
}
abstract class Shape {
    public abstract double calculateArea();
}
class Circle extends Shape {
    private double radius;
    public Circle(double radius) {
        this.radius = radius;
    }
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    private double length;
    private double width;
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    @Override
    public double calculateArea() {
        return length * width;
    }
}

class Triangle extends Shape {
    private double height;
    private double width;
    public Triangle(double height, double width) {
        this.height = height;
        this.width = width;
    }
    @Override
    public double calculateArea() {
        return height * width/2;
    }
}