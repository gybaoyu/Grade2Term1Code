package ink.abalone.week3;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class C4_1 {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2025, 8, 13);
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        date = date.minusDays(day - 1);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int value = dayOfWeek.getValue();
        System.out.println("Mon Tue Wed Thu Fri Sat Sun");
        for (int i = 1; i < value; i++) System.out.print("    ");
        while (date.getMonthValue() == month) {
            System.out.printf("%3d", date.getDayOfMonth());
            if (date.getDayOfMonth() == day)
                System.out.print("*");
            else
                System.out.print(" ");
            date = date.plusDays(1);
            if (date.getDayOfWeek().getValue() == 1) System.out.println();
        }
        if (date.getDayOfWeek().getValue() != 1) System.out.println();
    }
}
