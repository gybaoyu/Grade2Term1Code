package ink.abalone.test;

import java.util.*;
public class Main {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int[] arr = new int[21];
        for(int j = 1;j<=20;j++) {
            arr[j]=0;
        }
        int n = scan.nextInt();
        for(int i = 1;i<=n;i++) {
            int p = scan.nextInt();
            for(int j = 1;j<=20;j++) {
                if((5-arr[j])>=p) {
                    for(int k = arr[j]+(j-1)*5+1;k<=arr[j]+(j-1)*5+p;k++) {
                        System.out.print(k+" ");
                    }
                    System.out.println();
                    arr[j]+=p;
                    break;
                }
            }
        }
        scan.close();
    }
}