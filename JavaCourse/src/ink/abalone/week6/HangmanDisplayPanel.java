package ink.abalone.week6;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/*
 * File: HangmanPanel.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */


public class HangmanDisplayPanel extends JPanel {
	int bodyPartAppearNumber;
	
	
	public HangmanDisplayPanel() {
		super();
		this.setPreferredSize(new Dimension(400,600));
		bodyPartAppearNumber = 0;
		
	}

  

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Scaffold
		g.drawLine(getWidth() / 2 - BEAM_LENGTH,
				(getHeight() - SCAFFOLD_HEIGHT) / 2, 
				getWidth() / 2 - BEAM_LENGTH, 
				(getHeight() + SCAFFOLD_HEIGHT) / 2);
		
		//Beam
		g.drawLine(getWidth()/2 - BEAM_LENGTH,
				(getHeight() - SCAFFOLD_HEIGHT)/2,
				getWidth()/2,
				(getHeight() - SCAFFOLD_HEIGHT)/2);
		
		displayMan(g);
			
		
	}
	
	private void displayMan(Graphics g){
		switch (bodyPartAppearNumber){
		case 8:
			g.drawLine((getWidth() + HIP_WIDTH)/2,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH,
					(getWidth() + HIP_WIDTH)/2,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
			//Right Foot
			g.drawLine((getWidth() + HIP_WIDTH)/2,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH,
					(getWidth() + HIP_WIDTH)/2 + FOOT_LENGTH,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
		case 7:
			g.drawLine((getWidth() - HIP_WIDTH)/2,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH,
					(getWidth() - HIP_WIDTH)/2,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
			//Left Foot
			g.drawLine((getWidth() - HIP_WIDTH)/2,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH,
					(getWidth() - HIP_WIDTH)/2 - FOOT_LENGTH,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
		case 6:
			//hip
			g.drawLine((getWidth() - HIP_WIDTH)/2,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH,
					(getWidth() + HIP_WIDTH)/2,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH);
		case 5:
			//Right Arm
			//Upper Arm
			g.drawLine(getWidth()/2,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
					getWidth()/2 + UPPER_ARM_LENGTH,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
			//Lower Arm
			g.drawLine(getWidth()/2 + UPPER_ARM_LENGTH,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
					getWidth()/2 + UPPER_ARM_LENGTH,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		
		case 4:
			//Left Arm
			//Upper Arm
			g.drawLine(getWidth()/2 - UPPER_ARM_LENGTH,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
					getWidth()/2,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
			//Lower Arm
			g.drawLine(getWidth()/2 - UPPER_ARM_LENGTH,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
					getWidth()/2 - UPPER_ARM_LENGTH,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		case 3:
			//Body
			g.drawLine(getWidth()/2,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS,
					getWidth()/2,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH);
		case 2:
			g.drawOval((getWidth()/2 - HEAD_RADIUS),
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH,
					2 * HEAD_RADIUS,2 * HEAD_RADIUS);
		case 1:
			g.drawLine(getWidth()/2,
					(getHeight() - SCAFFOLD_HEIGHT)/2,
					getWidth()/2,
					(getHeight() - SCAFFOLD_HEIGHT)/2 + ROPE_LENGTH);
		}
		
		
	}
/** Resets the display so that only the scaffold appears */
	
	
	public void reset() {
		/* You fill this in */
		bodyPartAppearNumber = 0;
		repaint();
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		/* You fill this in */
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(int gussNumUsed) {
		/* You fill this in */
		bodyPartAppearNumber = gussNumUsed;
		repaint();
	
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}
