package ink.abalone.week15.T3;

import javax.swing.*;

public class BreakoutMain {
	public static void main(String[] args) {
		  JFrame frame = new JFrame ("break out");
		  frame.setLocation(600,100);
	      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	      frame.getContentPane().add (new BreakoutPanel(frame));
	      frame.pack();
		  frame.setResizable(false);
	      frame.setVisible(true);
	}
}
