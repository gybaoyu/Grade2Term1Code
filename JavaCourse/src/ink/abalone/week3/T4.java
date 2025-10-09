package ink.abalone.week3;

import java.util.Scanner;

public class T4 {
    private static boolean guessLetter(char c,StringBuilder wordDisplay,String word){
        boolean flag = false;
        for(int i=0;i<word.length();i++){
            if(word.charAt(i)==c) {
                wordDisplay.setCharAt(i,c);
                flag = true;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the word: ");
        String word = sc.nextLine().toLowerCase();
        StringBuilder wordDisplay = new StringBuilder("-".repeat(word.length()));
        for (int i = 0; i < 8; i++) {
            System.out.println(8-i+" times left");
            String guess = sc.next().toLowerCase();
            if (!guessLetter(guess.charAt(0),wordDisplay,word))
                System.out.println("There is no "+guess+" in the word.");
            System.out.println(wordDisplay);
            if (wordDisplay.indexOf("-") == -1){
                System.out.println("You win!");
                return;
            }
        }
        System.out.println("You lose!");
    }
}
