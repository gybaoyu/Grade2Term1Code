package ink.abalone.test;

public class Father {
    public int m=10;
    public Father(){
    }
    void change(){
        m=100;
    }
}
class Son extends Father{
}
class Test{
    public static void main(String[] args) {
        Father f = new Father();
        f.change();
        Son s = new Son();
        System.out.println(f.m);
        System.out.println(s.m);
    }
}
