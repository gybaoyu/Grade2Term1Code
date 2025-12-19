package ink.abalone.week15.T3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.random.RandomGenerator;

import static java.lang.Math.*;

/**
 * @author CGY
 */
public class BreakoutPanel extends JPanel {
    //system
    public static final int APPLICATION_WIDTH = 400, APPLICATION_HEIGHT = 600;
    private static final int WIDTH = APPLICATION_WIDTH, HEIGHT = APPLICATION_HEIGHT;
    private static final int DELAY = 15;
    private final Timer ballMoveTimer;
    private final RandomGenerator rgen = RandomGenerator.getDefault();
    private boolean isStart;
    private int health;
    private final JFrame parentFrame;
    private final JLabel healthLabel;

    //ball
    private int ballX, ballY;
    private static final int BALL_RADIUS = 10;
    private int vy, vx;

    //paddle
    private static final int PADDLE_WIDTH = 100, PADDLE_HEIGHT = 10, PADDLE_Y_OFFSET = 30;
    private final int paddleY = APPLICATION_HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
    private int paddleX;

    //brick
    private static final int NBRICKS_PER_ROW = 10, NBRICK_ROWS = 10, BRICK_SEP = 4;
    public static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW, BRICK_HEIGHT = 8;
    private static final int BRICK_Y_OFFSET = 70;
    private int collisionCount;
    private ArrayList<Brick> bricks;

    public BreakoutPanel(JFrame parentFrame) {
        setPreferredSize(new Dimension(APPLICATION_WIDTH, APPLICATION_HEIGHT));
        setBackground(Color.WHITE);
        healthLabel = new JLabel();
        add(healthLabel);
        healthLabel.setHorizontalAlignment(SwingConstants.LEFT);
        init();
        MouseAdapter mouseAdapter = new BreakoutMouseAdapter();
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
        ballMoveTimer = new Timer(DELAY, e -> {
            if (isStart) {
                checkCollisions();
                moveBall();
            }
            repaint();
            if (collisionCount==bricks.size()){
                GameOverDialog dialog = new GameOverDialog(parentFrame,"You Win!");
                if (dialog.isContinue) init();
                else System.exit(0);
            }
        });
        ballMoveTimer.start();
        this.parentFrame = parentFrame;
    }

    private void init(){
        initBricks();
        initBall();
        health=5;
        healthLabel.setText("Health: " + health);
        isStart=true;
        paddleX = (APPLICATION_WIDTH - PADDLE_WIDTH) / 2;
    }
    private void initBricks() {
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
        collisionCount=0;
    }
    private void initBall(){
        ballX = APPLICATION_WIDTH / 2 - BALL_RADIUS;
        ballY = APPLICATION_HEIGHT / 2 - BALL_RADIUS;
        vy=3;
        vx = rgen.nextInt(1, 3);
        if (rgen.nextBoolean()) vx = -vx;
    }

    private void moveBall() {
        ballX += vx;
        ballY += vy;
    }
    private void increaseV() {
        if (abs(vx) < 10 && abs(vy) < 10) {
            if (vx > 0) vx++;
            else vx--;
            if (vy > 0) vy++;
            else vy--;
        }
    }

    private void paddleCheck(Point south) {
        if (abs(south.y - paddleY) <= abs(vy) &&
                abs(south.x - (paddleX + PADDLE_WIDTH / 2)) <= PADDLE_WIDTH / 2) vy = -vy;
    }
    private void outCheck(Point south) {
        if (abs(south.y - APPLICATION_HEIGHT) <= abs(vy)) {
            ballX = paddleX + PADDLE_WIDTH / 2 - BALL_RADIUS;
            ballY = paddleY - 2 * BALL_RADIUS - 1;
            vx = 0;
            vy = 0;
            isStart = false;
            health--;
            healthLabel.setText("Health: " + health);
            if (health == 0) {
                GameOverDialog dialog = new GameOverDialog(parentFrame,"You Lose!");
                if (dialog.isContinue) init();
                else System.exit(0);
            }
            repaint();
        }
    }
    private void wallCheck(Point west, Point east, Point north) {
        if (abs(west.x) <= abs(vx)) vx = -vx;
        else if (abs(east.x - APPLICATION_WIDTH) <= abs(vx)) vx = -vx;
        if (abs(north.y) <= abs(vy)) vy = -vy;
    }
    private void brickCheck(Point north, Point south, Point east, Point west) {
        Point brickNorth, brickSouth, brickEast, brickWest;
        bricks.sort((b1, b2) -> {
            double dis1, dis2;
            dis1 = pow(b1.getX() - ballX, 2) + pow(b1.getY() - ballY, 2);
            dis2 = pow(b2.getX() - ballX, 2) + pow(b2.getY() - ballY, 2);
            return Double.compare(dis1, dis2);
        });
        for (Brick brick : bricks) {
            if (!brick.getVisible()) continue;
            brickNorth = new Point(brick.getX() + BRICK_WIDTH / 2, brick.getY());
            brickSouth = new Point(brick.getX() + BRICK_WIDTH / 2, brick.getY() + BRICK_HEIGHT);
            brickEast = new Point(brick.getX() + BRICK_WIDTH, brick.getY() + BRICK_HEIGHT / 2);
            brickWest = new Point(brick.getX(), brick.getY() + BRICK_HEIGHT / 2);
            if (abs(north.y - brickSouth.y) <= abs(vy) && abs(brickSouth.x - north.x) <= (BRICK_WIDTH + BRICK_SEP) / 2){
                brickCollision(brick, false);
                break;
            }
            else if (abs(south.y - brickNorth.y) <= abs(vy) && abs(brickNorth.x - south.x) <= (BRICK_WIDTH + BRICK_SEP) / 2){
                brickCollision(brick, false);
                break;
            }
            else if (abs(east.x - brickWest.x) <= abs(vx) && abs(brickWest.y - east.y) <= (BRICK_HEIGHT + BRICK_SEP) / 2){
                brickCollision(brick, true);
                break;
            }
            else if (abs(west.x - brickEast.x) <= abs(vx) && abs(brickEast.y - west.y) <= (BRICK_HEIGHT + BRICK_SEP) / 2){
                brickCollision(brick, true);
                break;
            }
        }
    }
    private void brickCollision(Brick brick, boolean isX) {
        if (isX) vx = -vx;
        else vy = -vy;
        brick.setVisible(false);
        collisionCount++;
        if (collisionCount%4==0)increaseV();
    }
    private void checkCollisions() {
        Point north = new Point(ballX + BALL_RADIUS, ballY);
        Point south = new Point(ballX + BALL_RADIUS, ballY + BALL_RADIUS * 2);
        Point east = new Point(ballX + BALL_RADIUS * 2, ballY + BALL_RADIUS);
        Point west = new Point(ballX, ballY + BALL_RADIUS);
        brickCheck(north, south, east, west);
        paddleCheck(south);
        outCheck(south);
        wallCheck(west, east, north);
    }

    private void drawBall(Graphics page) {
        page.setColor(Color.RED);
        page.fillOval(ballX, ballY, BALL_RADIUS * 2, BALL_RADIUS * 2);
    }
    private void drawPaddle(Graphics page) {
        page.setColor(Color.BLUE);
        page.fillRect(paddleX, paddleY, PADDLE_WIDTH, PADDLE_HEIGHT);
    }
    private void drawAllBricks(Graphics page) {
        for (Brick brick : bricks) brick.draw(page);
    }

    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        if (!isStart) ballX = paddleX + PADDLE_WIDTH / 2 - BALL_RADIUS;
        drawAllBricks(page);
        drawBall(page);
        drawPaddle(page);
    }

    private class BreakoutMouseAdapter extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (!isStart) {
                ballY-=5;
                vx = rgen.nextInt(1, 2);
                vy = rgen.nextInt(3,5);
                if (rgen.nextBoolean()) vx = -vx;
                vy=-vy;
                isStart = true;
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            mouseMoved(e);
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            int mouseX = e.getX();
            paddleX = max(0, min(mouseX - PADDLE_WIDTH / 2, APPLICATION_WIDTH - PADDLE_WIDTH));
        }
    }
    private static class GameOverDialog extends JDialog {
        private boolean isContinue = true;
        private final String text;
        public GameOverDialog(JFrame parentFrame,String text) {
            super(parentFrame, "Game Over",true);
            this.text = text;
            init();
            setVisible(true);
        }
        private void init() {
            setSize(250, 150);
            setLocationRelativeTo(getParent());
            setResizable(false);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            JPanel panel = new JPanel(new BorderLayout());
            JPanel buttonPanel = new JPanel(new FlowLayout());

            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            JLabel label = new JLabel(text, SwingConstants.CENTER);
            panel.add(label, BorderLayout.CENTER);

            JButton continueButton = new JButton("Continue");
            JButton exitButton = new JButton("Exit");
            continueButton.addActionListener(e -> {
                isContinue = true;
                dispose();
            });
            exitButton.addActionListener(e -> {
                isContinue = false;
                dispose();
            });

            buttonPanel.add(continueButton);
            buttonPanel.add(exitButton);
            panel.add(buttonPanel, BorderLayout.SOUTH);

            add(panel);
        }
    }
}