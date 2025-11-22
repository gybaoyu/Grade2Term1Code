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
    int[] getYPoints(int from, int to, Function<Integer,Integer> func1,Function<Integer,Integer> func2){
        int n = to-from+1;
        int[] res =new int[n];
        for (int i=from,j=0;i<to;i++,j++){
            res[j] = func1.apply(j);
        }
        return res;
    }

    @Override
    public void paintComponent(Graphics g){
        var g2 = (Graphics2D) g;
        double R = 150;
        var circle = new Ellipse2D.Double();
        circle.setFrameFromCenter(DEFAULT_WIDTH/2.0,DEFAULT_HEIGHT/2.0,DEFAULT_WIDTH*scale/2.0+R,DEFAULT_HEIGHT*scale/2.0+R);
        g2.setPaint(new Color(231,203,72));
        g2.fill(circle);
        g2.draw(circle);
        int from = -100,to = 100,n = to-from+1;
//        g2.fillPolygon(getXPoints(-100,100),getYPoints(-100,100,x->{
//
//            return 0;
//        }),n);
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
