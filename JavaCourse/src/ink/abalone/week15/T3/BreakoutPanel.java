package ink.abalone.week15.T3;

import javax.swing.*;
import java.awt.*;

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
	//关于砖块要加入的内容
	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH =
			(WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;
	//砖块颜色数组，对应每一行砖块的颜色，实现砖块颜色的绘制。
	private Color[] brickColor={Color.red,Color.red,Color.orange,Color.orange,Color.yellow,Color.yellow,Color.green,Color.green,Color.cyan,Color.cyan};

	/** Constructor */
	public BreakoutPanel(){
        //paddle最开始时，横坐标位于窗口的中间位置，纵坐标离窗口底端PADDLE_Y_OFFSET。paddleX,paddleY分别代表paddle左上角坐标
		ballX = APPLICATION_WIDTH / 2 - BALL_RADIUS;
	   	ballY = APPLICATION_HEIGHT / 2 - BALL_RADIUS;
		//球最开始时，在窗口的中间位置，ballx,bally分别代表球所在正方形的左上角坐标
	   	paddleX = (APPLICATION_WIDTH - PADDLE_WIDTH) / 2;
		paddleY = APPLICATION_HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
	    setPreferredSize(new Dimension(APPLICATION_WIDTH, APPLICATION_HEIGHT));
	    setBackground(Color.white);
	}
	public void paintComponent(Graphics page){
		super.paintComponent(page);
		drawBall(page);
		drawPaddle(page);
		drawAllBricks(page);
	}
	//根据ballx,bally,BALL_RADIUS,画球
	private void drawBall(Graphics page){
		page.setColor(Color.RED);
		page.fillOval(ballX, ballY, BALL_RADIUS * 2, BALL_RADIUS * 2);
	}
	// 根据paddleX,paddleY,PADDLE_WIDTH,PADDLE_HEIGHT,画paddle
	private void drawPaddle(Graphics page){
		page.setColor(Color.BLUE);
		page.fillRect(paddleX, paddleY, PADDLE_WIDTH, PADDLE_HEIGHT);
	}

	private void drawAllBricks(Graphics page){
		int x = 0,y = BRICK_Y_OFFSET;//the (x,y) of each brick
		//利用两重循环绘制砖块
		for (int i = 0;i <NBRICK_ROWS ; i++) {
			for (int j=0;j < NBRICKS_PER_ROW ; j++) {
				page.fillRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
				x+=BRICK_WIDTH+BRICK_SEP;
			}
			x=0;
			y+=BRICK_HEIGHT+BRICK_SEP;
		}

	}
}
