package ink.abalone.week2;

import java.math.BigInteger;
import java.util.Scanner;

public class C3_6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many numbers do you need to draw?");
        int k = sc.nextInt();
        System.out.println("What is the highest number you can draw?");
        int n = sc.nextInt();

        BigInteger lotteryOdds = BigInteger.valueOf(1);
        for (int i = 1; i <=k; i++) {
            lotteryOdds = lotteryOdds.multiply(BigInteger.valueOf(n-i+1)).divide(BigInteger.valueOf(i));
        }
        System.out.println("Your odds are 1 in "+lotteryOdds+". Good luck!");
    }
}
