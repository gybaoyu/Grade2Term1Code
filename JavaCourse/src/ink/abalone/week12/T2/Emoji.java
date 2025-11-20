package ink.abalone.week12.T2;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

public class Emoji {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            EmojiFrame frame = new EmojiFrame();
            frame.setTitle("Emoji Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class EmojiFrame extends JFrame {
    public EmojiFrame() {
        add(new EmojiComponent());
        pack();
    }
}

interface Quadratic{
    double getStartY(double startX);
}

class EmojiComponent extends JComponent{
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 400;
    private void drawQuadraticCurve(Graphics2D g2d,Color color,int startX,int endX, double a,int dx,int dy,double scale) {
        g2d.setColor(color);
        GeneralPath path = new GeneralPath();
        double firstY = a*startX*startX;
        path.moveTo(startX * scale+dx, -firstY * scale+dy);
        for (double x = startX + 0.1; x <= endX; x += 0.1) {
            double y = a*x*x;
            path.lineTo(x * scale+dx, -y * scale+dy);
        }
        g2d.draw(path);
    }
    private void fillClosedRegion(Graphics2D g2d,Color fillColor, int xStart, int xEnd, Quadratic quadratic1,Quadratic quadratic2,double dx,double dy,double dy2,double scale) {
        g2d.setColor(fillColor);
        GeneralPath path = new GeneralPath();
        // 移动到第一个交点
        double startY1 = quadratic1.getStartY(xStart);
        path.moveTo(xStart * scale+dx, -startY1 * scale+dy); // 注意Y轴方向
        // 沿第一条曲线添加到第二个交点
        for (double x = xStart; x <= xEnd; x += 0.01) {
            double y = quadratic1.getStartY(x);
            path.lineTo(x * scale+dx, -y * scale+dy);
        }
        // 沿第二条曲线返回第一个交点
        for (double x = xEnd; x >= xStart; x -= 0.01) {
            double y = quadratic2.getStartY(x);
            path.lineTo(x * scale+dx, -y * scale+dy2);
        }
        path.closePath();
        g2d.fill(path);
    }

    @Override
    public void paintComponent(Graphics g){
        var g2 = (Graphics2D) g;
        double R = 150;
        var circle = new Ellipse2D.Double();
        circle.setFrameFromCenter(DEFAULT_WIDTH/2.0,DEFAULT_HEIGHT/2.0,DEFAULT_WIDTH/2.0+R,DEFAULT_HEIGHT/2.0+R);
        g2.setPaint(new Color(231,203,72));
        g2.fill(circle);
        g2.draw(circle);
        drawQuadraticCurve(g2,new Color(123,58,22),-75,75, 0.0044444444,200,280,1);
        drawQuadraticCurve(g2,new Color(123,58,22), -75,75,0.008, new Color(123,58,22),200,300,1);
        fillClosedRegion(g2,new Color(123,58,22),-75,75,x1-> 0.008*x1*x1, x2-> 0.0044444444*x2*x2,200,300,280,1);

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
/*
y = ax^2
150 25 a = 0.0044444444
150 45 a = 0.008
*/
