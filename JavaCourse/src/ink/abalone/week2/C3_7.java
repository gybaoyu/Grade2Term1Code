package ink.abalone.week2;

import java.util.Arrays;
import java.util.Scanner;

public class C3_7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many numbers do you need to draw?");
        int k = sc.nextInt();
        System.out.println("What is the highest number you can draw?");
        int n = sc.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i+1;
        }
        int[] result = new int[k];
        for (int i = 0; i < numbers.length; i++) {
            int r = (int) (Math.random() * n);
            result[i] = numbers[r];
            numbers[r] = numbers[n-1];
            n--;
        }
        Arrays.sort(result);
        System.out.println("Bet the following combinations. It'll make you rich!");
        for (int r : result) {
            System.out.println(r);
        }
    }
}
