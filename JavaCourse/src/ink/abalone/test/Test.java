package ink.abalone.test;

import java.util.ArrayList;
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
        ArrayList<Test> list = new ArrayList<>();
        list.add(t1);
        list.add(t2);
        for (Test t : list) {
            System.out.println(t.hashCode());
        }
    }
}
