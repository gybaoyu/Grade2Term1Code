package ink.abalone.test;

import java.util.Objects;

public class Test {
    private String name;
    public Test(String name) {
        this.name = name;
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public static void main(String[] args) {
        Test t1 = new Test("111");
        Test t2 = new Test("111");
        System.out.println(t1.hashCode());
        System.out.println(t2.hashCode());
    }
}
