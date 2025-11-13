package ink.abalone.week11.T1;

public class Triangle {
    private double a;
    private double b;
    private double c;
    public Triangle(double a, double b, double c) throws NegativeException,NotATriangleException{
        if (a<0||b<0||c<0)throw new NegativeException();
        if (a+b<=c||a+c<=b||b+c<=a)throw new NotATriangleException();
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public double area(){
        double s = 0.5*(a+b+c);
        return Math.sqrt(s*(s-a)*(s-b)*(s-c));
    }
}
