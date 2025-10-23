package ink.abalone.week8.T3;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public abstract class GradeItem {
    private String name;
    private LocalDateTime date;
    private String context;
    private double maxGrade;
    private double score;
    public GradeItem(String name, LocalDateTime date, String context,double maxGrade,double score) {
        this.name = name;
        this.date = date;
        this.context = context;
        this.maxGrade = maxGrade;
        this.score = score;
    }
    public abstract char scoreToGrade();

    protected char gradeConverter(){
        if (score/maxGrade>=0.9)return 'A';
        else if (score/maxGrade>=0.8)return 'B';
        else if (score/maxGrade>=0.7)return 'C';
        else if (score/maxGrade>=0.6)return 'D';
        else return 'E';
    }

    public double getMaxGrade() {
        return maxGrade;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "GradeItem{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", context='" + context + '\'' +
                ", maxGrade=" + maxGrade +
                ", score=" + score +
                '}';
    }
}
