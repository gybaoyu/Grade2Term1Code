package ink.abalone.week13.T3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

class MouseComponent extends JComponent {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;
    private static final int SIDELENGTH = 10;
    private ArrayList<RectangularShape> squares;
    private RectangularShape current;
    public boolean isRound = false;
    public MouseComponent() {
        squares = new ArrayList<>();
        current = null;
        addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseMotionHandler());
    }
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (RectangularShape r : squares) {
            g2d.draw(r);
        }
    }
    public RectangularShape find(Point2D p){
        for (RectangularShape r : squares) {
            if (r.contains(p)) return r;
        }
        return null;
    }
    public void add(Point2D p) {
        double x = p.getX();
        double y = p.getY();
        if (!isRound){
            current = new Rectangle2D.Double(x-SIDELENGTH/2.0,y-SIDELENGTH/2.0,SIDELENGTH,SIDELENGTH);
        }else{
            current = new Ellipse2D.Double(x-SIDELENGTH/2.0,y-SIDELENGTH/2.0,SIDELENGTH,SIDELENGTH);
        }
        squares.add(current);
        repaint();
    }
    public void remove(Rectangle2D s) {
        if (s == null)return;
        if (s == current)current=null;
        squares.remove(s);
        repaint();
    }
    private class MouseHandler extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            current = find(e.getPoint());
            if (current == null) add(e.getPoint());
        }
        public void mouseClicked(MouseEvent e) {
            current = find(e.getPoint());
            if (current != null && e.getClickCount()>=2)remove((Rectangle2D) current);
        }
    }
    private class MouseMotionHandler implements MouseMotionListener {
        @Override
        public void mouseDragged(MouseEvent e) {
            if (current != null) {
                int x = e.getX();
                int y = e.getY();
                current.setFrame(x-SIDELENGTH/2.0,y-SIDELENGTH/2.0,SIDELENGTH,SIDELENGTH);
                repaint();
            }
        }
        @Override
        public void mouseMoved(MouseEvent e) {
            if (find(e.getPoint())==null)setCursor(Cursor.getDefaultCursor());
            else setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }
    }
}

class ButtonPanel extends JPanel {
    public ButtonPanel(MouseComponent component) {
        var square = new JButton("方");
        var round = new JButton("圆");
        add(square);
        add(round);
        square.addActionListener(e-> component.isRound = false);
        round.addActionListener(e-> component.isRound = true);
    }
}

public class MouseTest extends JFrame {
    public MouseTest() {
        MouseComponent component = new MouseComponent();
        add(new ButtonPanel(component), BorderLayout.NORTH);
        add(component, BorderLayout.CENTER);
        pack();
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new MouseTest();
            frame.setTitle("MouseTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}