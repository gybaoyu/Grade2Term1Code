package ink.abalone.week15.T3;

import java.awt.*;

public class Brick{
    private final int x, y;
    private final Color color;
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
