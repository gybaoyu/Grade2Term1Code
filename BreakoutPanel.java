import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import java.util.Random;


import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * 
 */

/**
 * @author bh
 *
 */
public class BreakoutPanel extends JPanel {
	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

 

/** The coordinate of the ball*/
	private int ballX,ballY;
	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;
/** The coordinate of the paddle*/
	private int paddleX,paddleY;


	/** Constructor */
	public BreakoutPanel(){
		


        //paddle最开始时，横坐标位于窗口的中间位置，纵坐标离窗口底端PADDLE_Y_OFFSET。paddleX,paddleY分别代表paddle左上角坐标
	    
		ballX =_____________________;
	   	ballY =_____________________;
	    
	   
		////球最开始时，在窗口的中间位置，ballx,bally分别代表球所在正方形的左上角坐标
	   	paddleX = ______________________;
	    	paddleY = ______________________;
	    
	   
	    
	   
	    setPreferredSize(new Dimension(APPLICATION_WIDTH, APPLICATION_HEIGHT));
	    setBackground(Color.white);
	    
	   
	    
	    
	}
	public void paintComponent(Graphics page){
		super.paintComponent(page);
		
		
		drawBall(page);
		drawPaddle(page);
		
		
		
	}
	
	
	//根据ballx,bally,BALL_RADIUS,画球
	private void drawBall(Graphics page){
		
		
		
	}
	// 根据paddleX,paddleY,PADDLE_WIDTH,PADDLE_HEIGHT,画paddle
	private void drawPaddle(Graphics page){
		
	}

	

}
