package ink.abalone.week4;

class Test {
    int a, b;

    public Test(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "Test{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}

public class ParamTest {
    static void swap(Test A, Test B) {
        Test tmp = A;
        A = B;
        B = tmp;
    }
    static void swap(int a, int b) {
        int tmp = a;
        a = b;
        b = tmp;
    }
    static void change(Test t){
        t.a = 0;
    }
    public static void main(String[] args) {
        Test a = new Test(1, 2);
        Test b = new Test(3, 4);
        int c = 5;int d = 6;
        swap(a, b);
        swap(c,d);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        change(a);
        System.out.println(a);
    }
}
