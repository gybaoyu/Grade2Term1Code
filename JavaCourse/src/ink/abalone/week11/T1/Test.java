package ink.abalone.week11.T1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("输入被除数: ");
            int num1 = sc.nextInt();
            System.out.println("输入除数: ");
            int num2 = sc.nextInt();
            System.out.printf("%d / %d = %d%n", num1, num2, num1/num2);
        }catch (InputMismatchException e){
            System.err.println("被除数和除数必须是整数");
        }catch (ArithmeticException e){
            System.err.println("除数不能为零");
        }catch (Exception e){
            System.err.println(e.getMessage());
        }finally {
            System.out.println("感谢使用");
        }
    }
}
