package ink.abalone.week6;/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */





import java.awt.GridLayout;
import java.io.Console;

import javax.swing.JFrame;

public class Hangman {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  JFrame frame = new JFrame ("hangman");
	      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	      frame.getContentPane().add (new HangmanPanel());
	      //frame.getContentPane().add (new HangmanDisplayPanel());
	     
	      frame.pack();
	      frame.setVisible(true);
	}

}




