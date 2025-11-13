package ink.abalone.week8.T3;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

public class GradeItemTest {
    public static void main(String[] args) {
        ArrayList<GradeItem> list = new ArrayList<>();
        Assignment a1 = new Assignment("Assignment1",
                LocalDateTime.of(2025, Month.MAY,16,15,30,0),
                "Java", 8.7);
        Assignment a2 = new Assignment("Assignment2",
                LocalDateTime.of(2025, Month.JUNE,21,16,0,0),
                "Python", 9.6);
        Exam e1 = new Exam("Exam1",
                LocalDateTime.of(2025, Month.JULY,26,9,30,0),
                "JavaExam",91);
        Exam e2 = new Exam("Exam2",
                LocalDateTime.of(2025, Month.AUGUST,31,12,0,0),
                "PythonExam",86);
        Assignment theSameAsA1 = new Assignment("Assignment1",
                LocalDateTime.of(2025, Month.MAY,16,15,30,0),
                "Java", 8.7);
        Exam theSameAsE1 = new Exam("Exam1",
                LocalDateTime.of(2025, Month.JULY,26,9,30,0),
                "JavaExam",91);
        list.add(a1);
        list.add(a2);
        list.add(e1);
        list.add(e2);
        for (GradeItem gradeItem : list) {
            System.out.println(gradeItem);
            System.out.println("grade: "+gradeItem.scoreToGrade());
            System.out.println("hashCode: "+gradeItem.hashCode()+"\n");
        }

        System.out.println("theSameAsA1.hashCode(): "+theSameAsA1.hashCode());
        System.out.println("theSameAsE1.hashCode(): "+theSameAsE1.hashCode()+"\n");
        System.out.println("a1.equals(a2): "+a1.equals(a2));
        System.out.println("a1.equals(theSameAsA1): "+a1.equals(theSameAsA1));
        System.out.println("e1.equals(e2): "+e1.equals(e2));
        System.out.println("e1.equals(theSameAsE1): "+e1.equals(theSameAsE1));
    }
}
