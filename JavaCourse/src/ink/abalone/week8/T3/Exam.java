package ink.abalone.week8.T3;

import java.time.LocalDateTime;
import java.util.Date;

public class Exam extends GradeItem{
    public Exam(String name, LocalDateTime date, String context, double score) {
        super(name, date, context,100,score);
    }
    @Override
    public char scoreToGrade() {
        return gradeConverter();
    }
}
