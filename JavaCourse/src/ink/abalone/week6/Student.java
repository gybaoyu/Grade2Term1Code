package ink.abalone.week6;

public class Student {
    private static String school = "SZU";
    private String num;

    public Student(String num) {
        this.num = num;
    }

    public Student() {
        num = "";
    }

    public void setNum(String num) {
        if (num.length() == 8) this.num = num;
    }

    public static String getSchool() {
        return school;
    }
}
