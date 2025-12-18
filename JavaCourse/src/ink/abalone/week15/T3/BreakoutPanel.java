package ink.abalone.week15.T3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.random.RandomGenerator;

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

	//(step 3) 球移动的定时器的定时间隔
	/** Animation delay or pause time between ball moves */
	private static final int DELAY = 20;

	/** The timer which control the movement of the ball */
	private Timer ballMoveTimer;

	//球移动x,y坐标变化的量
	/** Amount Y velocity is increased each cycle as a result of gravity */
	private  double vy = 3;
	/** Amount x velocity */
	private  double vx ;

	private RandomGenerator rgen = RandomGenerator.getDefault();
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

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int mouseX = e.getX();
				paddleX = Math.max(0, Math.min(mouseX - PADDLE_WIDTH / 2, APPLICATION_WIDTH - PADDLE_WIDTH));
				repaint();
			}
		});
		initializeBallVelocity();
		ballMoveTimer = new Timer(DELAY, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveBall();
				checkCollisions();
				repaint();
			}
		});
		ballMoveTimer.start();
//		//(step 3),增加对鼠标移动的监听器
//		addMouseMotionListener(__________);
//		//(step 3) 创建定时器，启动定时器
//		ballMoveTimer = new Timer(__________);
//		ballMoveTimer.start();

	}

	private void initializeBallVelocity() {
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean()) {
			vx = -vx;
		}
	}

	private void moveBall() {
		ballX += vx;
		ballY += vy;
	}

	private void checkCollisions() {
		checkWallCollisions();
		checkObjectCollisions();
	}

	private void checkWallCollisions() {
		// 左右墙壁碰撞
		if (ballX <= 0) {
			ballX = 0;
			vx = -vx;
		} else if (ballX >= APPLICATION_WIDTH - BALL_RADIUS * 2) {
			ballX = APPLICATION_WIDTH - BALL_RADIUS * 2;
			vx = -vx;
		}
		// 顶部墙壁碰撞
		if (ballY <= 0) {
			ballY = 0;
			vy = -vy;
		}
	}

	private void checkObjectCollisions() {
		// 检查四个角点
		Point[] corners = {
				new Point(ballX, ballY), // 左上角
				new Point(ballX + BALL_RADIUS * 2, ballY), // 右上角
				new Point(ballX, ballY + BALL_RADIUS * 2), // 左下角
				new Point(ballX + BALL_RADIUS * 2, ballY + BALL_RADIUS * 2) // 右下角
		};
		for (Point corner : corners) {
			Component collider = findComponentAt(corner.x, corner.y);
			if (collider != null && collider != this) {
				handleCollision(collider, corner);
				break;
			}
		}
	}

	private void handleCollision(Component collider, Point collisionPoint) {
		// 如果是挡板
		if (collisionPoint.y >= paddleY && collisionPoint.y <= paddleY + PADDLE_HEIGHT &&
				collisionPoint.x >= paddleX && collisionPoint.x <= paddleX + PADDLE_WIDTH) {
			vy = -Math.abs(vy); // 确保球向上弹
			System.out.println("test");
			return;
		}
		// 如果是砖块
		Rectangle bounds = collider.getBounds();
		if (bounds.y < paddleY) { // 确保是砖块而不是其他组件
			remove(collider);
			vy = -vy;
		}
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

	//(step 3),增加对鼠标移动相应的监听器类，要实现MouseMotionListener接口
	//(step 3)增加对定时器的监听器类，以实现球的移动。
}
