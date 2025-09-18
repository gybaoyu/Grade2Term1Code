package ink.abalone.week2;

import java.util.Scanner;

public class C3_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("How much will you contribute every year");
        double payment = sc.nextDouble();
        System.out.println("Interest rate in %:");
        double interestRate = sc.nextDouble();
        double balance = 0;
        int year = 0;
        String input;
        do {
            balance+=payment;
            double interest = balance*interestRate/100;
            balance+=interest;
            year++;
            System.out.printf("After year %d,your balance is %,.2f%n",year,balance);
            System.out.println("Ready to retire?(Y/N)");
            input = sc.next();
        }while (input.equals("N"));
    }
}
