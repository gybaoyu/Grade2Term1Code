package ink.abalone.week6;

import static ink.abalone.week6.ComplexNumberTool.*;

public class ComplexNum {
    double real;
    double imag;
    public ComplexNum(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }
    public ComplexNum(double real) {
        this.real = real;
        this.imag = 0;
    }

    @Override
    public String toString() {
        if (imag==0) return ""+real;
        else if (imag>0)return real+"+"+imag+"i";
        else return real+""+imag+"i";
    }

    public static void main(String[] args) {
        ComplexNum c1 = new ComplexNum(3.0, 2.0);
        ComplexNum c2 = new ComplexNum(4);
        ComplexNum c3 = new ComplexNum(2.0, 4.0);
        System.out.println(plus(c1,c2));
        System.out.println(minus(c1,c2));
        System.out.println(multiply(c1,c2));
        System.out.println(divide(c1,c2));
        System.out.println();
        System.out.println(plus(c1,c3));
        System.out.println(minus(c1,c3));
        System.out.println(multiply(c1,c3));
        System.out.println(divide(c1,c3));
        System.out.println();
        System.out.println(plus(c1,1));
        System.out.println(minus(c1,2));
        System.out.println(multiply(c1,3));
        System.out.println(divide(c1,4));
    }
}
class ComplexNumberTool{
    public static ComplexNum plus(ComplexNum a, ComplexNum b){
        return new ComplexNum(a.real + b.real, a.imag + b.imag);
    }
    public static ComplexNum minus(ComplexNum a, ComplexNum b){
        return new ComplexNum(a.real - b.real, a.imag - b.imag);
    }
    public static ComplexNum multiply(ComplexNum a, ComplexNum b){
        return new ComplexNum(a.real * b.real - a.imag * b.imag, a.real*b.imag + b.real*a.imag);
    }
    public static ComplexNum divide(ComplexNum a, ComplexNum b){
        return new ComplexNum((a.real*b.real+a.imag*b.imag)/(b.real*b.real+b.imag*b.imag),
                (a.imag*b.real-a.real*b.imag)/(b.real*b.real+b.imag*b.imag));
    }
    public static ComplexNum plus(ComplexNum a,double b){
        return new ComplexNum(a.real+b,a.imag);
    }
    public static ComplexNum minus(ComplexNum a,double b){
        return new ComplexNum(a.real-b,a.imag);
    }
    public static ComplexNum multiply(ComplexNum a,double b){
        return new ComplexNum(a.real*b,a.imag*b);
    }
    public static ComplexNum divide(ComplexNum a,double b){
        return new ComplexNum(a.real/b,a.imag/b);
    }
}
