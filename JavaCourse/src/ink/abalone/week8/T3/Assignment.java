package ink.abalone.week8.T3;

import java.time.LocalDateTime;

public class Assignment extends GradeItem{
    public Assignment(String name, LocalDateTime date, String context, double score) {
        super(name, date, context,10,score);
    }
    @Override
    protected char gradeConverter() {
        double score = getScore();
        double maxGrade = getMaxGrade();
        if (score/maxGrade>=0.95)return 'A';
        else if (score/maxGrade>=0.85)return 'B';
        else if (score/maxGrade>=0.75)return 'C';
        else if (score/maxGrade>=0.7)return 'D';
        else return 'E';
    }
    @Override
    public char scoreToGrade() {
        return gradeConverter();
    }
}
