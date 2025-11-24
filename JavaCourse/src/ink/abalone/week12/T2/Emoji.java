package ink.abalone.week12.T2;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.function.Function;

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
        add(new EmojiComponent(1));
        pack();
    }
}


class EmojiComponent extends JComponent{
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 400;
    public double scale;
    public EmojiComponent(double scale) {
        this.scale = scale;
    }

    void drawCurve(Point2D.Double p1,Point2D.Double p2, Point2D.Double p3,Graphics2D g2) {
        g2.setStroke(new BasicStroke(10.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        QuadCurve2D curve = new QuadCurve2D.Double(p1.x, p1.y, p2.x, p2.y, p3.x, p3.y);
        g2.draw(curve);
    }

    @Override
    public void paintComponent(Graphics g) {
        var g2 = (Graphics2D) g;
        double R = 150;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        var face = new Ellipse2D.Double();
        face.setFrameFromCenter(DEFAULT_WIDTH / 2.0, DEFAULT_HEIGHT / 2.0, DEFAULT_WIDTH * scale / 2.0 + R, DEFAULT_HEIGHT * scale / 2.0 + R);
        g2.setPaint(new Color(243, 79, 84));
        g2.fill(face);
        g2.setPaint(Color.WHITE);
        g2.fillOval(105,150,90,90);
        g2.fillOval(215,150,90,90);
        g2.setPaint(new Color(66,41,52));
        g2.fillOval(150,177,40,40);
        g2.fillOval(220,177,40,40);

        g2.setColor(new Color(78,57,82)); // 设置曲线颜色
        drawCurve(new Point2D.Double(155,270),new Point2D.Double(205,210),new Point2D.Double(250,270),g2);
        drawCurve(new Point2D.Double(145,130),new Point2D.Double(150,150),new Point2D.Double(190,160),g2);
        drawCurve(new Point2D.Double(220,160),new Point2D.Double(260,150),new Point2D.Double(265,130),g2);

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
