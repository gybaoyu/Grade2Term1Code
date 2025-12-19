package ink.abalone.week15.T3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.random.RandomGenerator;

import static java.lang.Math.*;
/**
 *
 */

/**
 * @author bh
 */
public class BreakoutPanel extends JPanel {
    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Dimensions of game board (usually the same)
     */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;
    /**
     * Dimensions of the paddle
     */
    private static final int PADDLE_WIDTH = 80;
    private static final int PADDLE_HEIGHT = 10;
    /**
     * Offset of the paddle up from the bottom
     */
    private static final int PADDLE_Y_OFFSET = 30;
    /**
     * The coordinate of the ball
     */
    private int ballX, ballY;
    /**
     * Radius of the ball in pixels
     */
    private static final int BALL_RADIUS = 10;
    /**
     * The coordinate of the paddle
     */
    private int paddleX, paddleY;
    //关于砖块要加入的内容
    /**
     * Number of bricks per row
     */
    private static final int NBRICKS_PER_ROW = 10;

    /**
     * Number of rows of bricks
     */
    private static final int NBRICK_ROWS = 10;

    /**
     * Separation between bricks
     */
    private static final int BRICK_SEP = 4;

    /**
     * Width of a brick
     */
    public static final int BRICK_WIDTH =
            (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /**
     * Height of a brick
     */
    public static final int BRICK_HEIGHT = 8;

    /**
     * Offset of the top brick row from the top
     */
    private static final int BRICK_Y_OFFSET = 70;

    //(step 3) 球移动的定时器的定时间隔
    /**
     * Animation delay or pause time between ball moves
     */
    private static final int DELAY = 5;

    /**
     * The timer which control the movement of the ball
     */
    private Timer ballMoveTimer;

    //球移动x,y坐标变化的量
    /**
     * Amount Y velocity is increased each cycle as a result of gravity
     */
    private int vy = 3;
    /**
     * Amount x velocity
     */
    private int vx;

    private RandomGenerator rgen = RandomGenerator.getDefault();
    //砖块颜色数组，对应每一行砖块的颜色，实现砖块颜色的绘制。
    private Color[] brickColor = {Color.red, Color.red, Color.orange, Color.orange, Color.yellow, Color.yellow, Color.green, Color.green, Color.cyan, Color.cyan};

    private ArrayList<Brick> bricks;
    private boolean isStart = true;
    /**
     * Constructor
     */
    public BreakoutPanel() {

        //paddle最开始时，横坐标位于窗口的中间位置，纵坐标离窗口底端PADDLE_Y_OFFSET。paddleX,paddleY分别代表paddle左上角坐标
        ballX = APPLICATION_WIDTH / 2 - BALL_RADIUS;
        ballY = APPLICATION_HEIGHT / 2 - BALL_RADIUS;
        //球最开始时，在窗口的中间位置，ballx,bally分别代表球所在正方形的左上角坐标
        paddleX = (APPLICATION_WIDTH - PADDLE_WIDTH) / 2;
        paddleY = APPLICATION_HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
        setPreferredSize(new Dimension(APPLICATION_WIDTH, APPLICATION_HEIGHT));
        setBackground(Color.white);
        initBricks();
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int mouseX = e.getX();
                paddleX = max(0, min(mouseX - PADDLE_WIDTH / 2, APPLICATION_WIDTH - PADDLE_WIDTH));
            }
        });
        initializeBallVelocity();

        ballMoveTimer = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isStart){
                    checkCollisions();
                    moveBall();
                }
                repaint();
            }
        });
        ballMoveTimer.start();
    }

    private void initializeBallVelocity() {
        vx = rgen.nextInt(1, 3);
        if (rgen.nextBoolean()) {
            vx = -vx;
        }
    }

    private void moveBall() {
        ballX +=  vx;
        ballY +=  vy;
    }

    private void changeVx() {
        if (abs(vx)<10&&abs(vy)<10) {
            if (vx>0)vx++;
            else vx--;
            if (vy>0)vy++;
            else vy--;
        }
        vx = -vx;
    }

    private void changeVy() {
        if (abs(vx)<10&&abs(vy)<10) {
            if (vx>0)vx++;
            else vx--;
            if (vy>0)vy++;
            else vy--;
        }
        vy = -vy;
    }

    private void brickCollision(Brick brick, boolean isX) {
        if (isX) vx=-vx;
        else vy=-vy;
        brick.setVisible(false);
    }

    private void checkCollisions() {
        Point north = new Point(ballX + BALL_RADIUS, ballY);
        Point south = new Point(ballX + BALL_RADIUS, ballY + BALL_RADIUS * 2);
        Point east = new Point(ballX + BALL_RADIUS * 2, ballY + BALL_RADIUS);
        Point west = new Point(ballX, ballY + BALL_RADIUS);
        checkBrickCollisions(north, south, east, west);
        //Paddle
        if (abs(south.y-paddleY)<=abs(vy) &&  abs(south.x - (paddleX+PADDLE_WIDTH/2)) <= PADDLE_WIDTH/2) {
            changeVy();
        }
        //出界
        if (abs(south.y-APPLICATION_HEIGHT)<=abs(vy)){
            ballX = paddleX+PADDLE_WIDTH/2-BALL_RADIUS;
            ballY = paddleY-2*BALL_RADIUS-1;
            vx=0;
            vy=0;
            isStart=false;
            repaint();
            return;
        }
        //左右墙
        if (abs(west.x)<= abs(vx)) {
            changeVx();
        } else if (abs(east.x-APPLICATION_WIDTH)<=abs(vx)) {
            changeVx();
        }
        //上墙
        if (abs(north.y)<=abs(vy)) {
            changeVy();
        }
    }


    void checkBrickCollisions(Point north, Point south, Point east, Point west) {
        Point brickNorth, brickSouth, brickEast, brickWest;
        for (Brick brick : bricks) {
            if (!brick.getVisible()) continue;
            brickNorth = new Point(brick.getX() + BRICK_WIDTH / 2, brick.getY());
            brickSouth = new Point(brick.getX() + BRICK_WIDTH / 2, brick.getY() + BRICK_HEIGHT);
            brickEast = new Point(brick.getX() + BRICK_WIDTH, brick.getY() + BRICK_HEIGHT / 2);
            brickWest = new Point(brick.getX(), brick.getY() + BRICK_HEIGHT / 2);
            if (abs(north.y - brickSouth.y)<=abs(vy) && abs(brickSouth.x - north.x) <= (BRICK_WIDTH+BRICK_SEP) / 2)
                brickCollision(brick, false);
            else if (abs(south.y - brickNorth.y)<=abs(vy) && abs(brickNorth.x - south.x) <= (BRICK_WIDTH+BRICK_SEP) / 2)
                brickCollision(brick, false);
            else if (abs(east.x - brickWest.x)<=abs(vx) && abs(brickWest.y - east.y) <= (BRICK_HEIGHT+BRICK_SEP) / 2)
                brickCollision(brick, true);
            else if (abs(west.x - brickEast.x)<=abs(vx) && abs(brickEast.y - west.y) <= (BRICK_HEIGHT+BRICK_SEP) / 2)
                brickCollision(brick, true);
        }
    }

    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        if (!isStart)ballX = paddleX+PADDLE_WIDTH/2-BALL_RADIUS;

        drawAllBricks(page);
        drawBall(page);
        drawPaddle(page);
    }

    //根据ballx,bally,BALL_RADIUS,画球
    private void drawBall(Graphics page) {
        page.setColor(Color.RED);
        page.fillOval(ballX, ballY, BALL_RADIUS * 2, BALL_RADIUS * 2);
    }

    // 根据paddleX,paddleY,PADDLE_WIDTH,PADDLE_HEIGHT,画paddle
    private void drawPaddle(Graphics page) {
        page.setColor(Color.BLUE);
        page.fillRect(paddleX, paddleY, PADDLE_WIDTH, PADDLE_HEIGHT);
//		System.out.println(paddleX + " " + paddleY);
    }

    private void initBricks(){
        bricks = new ArrayList<>();
        int x = 0, y = BRICK_Y_OFFSET;//the (x,y) of each brick
        Color[] colors = {new Color(255, 0, 0), new Color(255, 153, 0), new Color(255, 204, 0), new Color(0, 255, 0), new Color(0, 255, 255)};
        for (int i = 0; i < NBRICK_ROWS; i++) {
            for (int j = 0; j < NBRICKS_PER_ROW; j++) {
                bricks.add(new Brick(x, y, colors[i / 2]));
                x += BRICK_WIDTH + BRICK_SEP;
            }
            x = 0;
            y += BRICK_HEIGHT + BRICK_SEP;
        }
    }

    private void drawAllBricks(Graphics page) {
        for (Brick brick : bricks) brick.draw(page);
    }
}

class Brick {
    private int x, y;
    private Color color;
    private boolean visible;

    public Brick(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        visible = true;
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void draw(Graphics page) {
        if (visible) {
            page.setColor(color);
            page.fillRect(x, y, BreakoutPanel.BRICK_WIDTH, BreakoutPanel.BRICK_HEIGHT);
        } else {
            page.setColor(Color.WHITE);
            page.fillRect(x, y, BreakoutPanel.BRICK_WIDTH, BreakoutPanel.BRICK_HEIGHT);
        }
    }
}