package ink.abalone.week14.T4;

import javax.swing.JFrame;

/**
 * 
 */

/**
 * @author bh
 *
 */
public class BreakoutMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  JFrame frame = new JFrame ("break out");
	      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	      frame.getContentPane().add (new BreakoutPanel());
	      frame.pack();
	      frame.setVisible(true);
	}

}
