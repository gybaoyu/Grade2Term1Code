package ink.abalone.week6;

public class AccessTest {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.setNum("001");
        System.out.println(Student.getSchool());
    }
}
