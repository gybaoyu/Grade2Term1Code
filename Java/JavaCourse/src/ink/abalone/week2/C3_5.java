package ink.abalone.week2;

import java.util.Scanner;

public class C3_5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many numbers do you need to draw?");
        int k = sc.nextInt();
        System.out.println("What is the highest number you can draw?");
        int n = sc.nextInt();

        int lotteryOdds = 1;
        for (int i = 1; i <=k; i++) {
            lotteryOdds = lotteryOdds*(n-i+1)/i;
        }
        System.out.println("Your odds are 1 in "+lotteryOdds+". Good luck!");
    }
}
