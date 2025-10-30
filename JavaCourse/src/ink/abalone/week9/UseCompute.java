package ink.abalone.week9;

public class UseCompute{
    public static double useCom(Compute c,double n,double m){
        return c.computer(n,m);
    }
    public static void main(String[] args) {
        System.out.println(useCom(new Plus(),5.7,2.3));
        System.out.println(useCom(new Minus(),5.7,2.3));
        System.out.println(useCom(new Multiply(),5.7,2.3));
        System.out.println(useCom(new Divide(),5.7,2.3));
    }
}

interface Compute {
    public double computer(double n,double m);
}
class Plus implements Compute {
    @Override
    public double computer(double n, double m) {
        return n+m;
    }
}
class Minus implements Compute {
    @Override
    public double computer(double n, double m) {
        return n-m;
    }
}
class Multiply implements Compute {
    @Override
    public double computer(double n, double m) {
        return n*m;
    }
}
class Divide implements Compute {
    @Override
    public double computer(double n, double m) {
        if (m!=0) return n/m;
        else throw new ArithmeticException("can not divide by zero");
    }
}
