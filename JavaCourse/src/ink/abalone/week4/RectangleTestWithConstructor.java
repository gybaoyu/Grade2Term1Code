package ink.abalone.week4;
class Rectangle{
    private double width;
    private double height;
    public Rectangle(double w,double h) {
        width = w;
        height = h;
    }
    public Rectangle() {
        width = 0.0;
        height = 0.0;
    }
    public void setWidth(double w) {
        width = w;
    }
    public void setHeight(double h) {
        height = h;
    }
    public double getArea() {
        return width*height;
    }
}
public class RectangleTestWithConstructor{
    public static void main(String[] args) {
        Rectangle r1=new Rectangle(100,220.2);
        r1.setWidth(10);
        //r1.setHeight(20.5);
        double r1Area = r1.getArea();
        System.out.println("area is "+r1Area);
        Rectangle r11=new Rectangle();
        //r11.setWidth(60);
        //r11.setHeight(206.5);
        double r11Area = r11.getArea();
        System.out.println("area is "+r11Area);
    }
}