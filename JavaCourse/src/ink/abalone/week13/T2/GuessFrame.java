package ink.abalone.week13.T2;

import ink.abalone.week13.T1.ButtonFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessFrame extends JFrame {
    private JPanel buttonPanel;
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;
    public GuessFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        int guess = new Random().nextInt(0,10);
        var large = new JButton("大");
        var small = new JButton("小");
        buttonPanel = new JPanel();
        add(buttonPanel);
        large.addActionListener(e -> {
            if (guess>=5) buttonPanel.add(new JLabel("猜对了,答案是:"+guess));
            else buttonPanel.add(new JLabel("猜错了,答案是:"+guess));
            buttonPanel.revalidate();
        });
        small.addActionListener(e -> {
            if (guess<5) buttonPanel.add(new JLabel("猜对了,答案是:"+guess));
            else buttonPanel.add(new JLabel("猜错了,答案是:"+guess));
            buttonPanel.revalidate();
        });
        buttonPanel.add(large);
        buttonPanel.add(small);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new GuessFrame();
            frame.setTitle("GuessTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
