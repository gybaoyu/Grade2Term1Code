package ink.abalone.week11.T1;

import java.util.Scanner;

public class ExceptionTest {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        double x,y,z;
        double result = 0;
        boolean retry = false;
        do {
            retry=false;
            System.out.println("input the length of three sides: ");
            x = s.nextDouble();
            y = s.nextDouble();
            z = s.nextDouble();
            try {
                Triangle t = new Triangle(x,y,z);
                result = t.area();
                System.out.println(result);
            } catch (Exception e){
                System.err.println(e.getMessage());
                System.out.println("input again");
                retry=true;
            }
        }while (retry);
    }
}
