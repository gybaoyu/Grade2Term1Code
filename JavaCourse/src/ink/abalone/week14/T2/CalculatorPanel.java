package ink.abalone.week14.T2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorPanel extends JPanel {
    private JButton display;
    private JPanel panel;
    private double result;
    private String lastCommand;
    private boolean start;
    private String currentInput; // 当前输入的数字字符串
    public CalculatorPanel() {
        setLayout(new BorderLayout());
        result = 0;
        lastCommand = "=";
        start = true;
        currentInput = ""; // 初始化当前输入为空
        display = new JButton("0");
        display.setEnabled(false);
        add(display, BorderLayout.NORTH);
        var insert = new InsertAction();
        var command = new CommandAction();
        panel = new JPanel();
        panel.setLayout(new GridLayout(4,4));
        char[] c = {'7','8','9','/','4','5','6','*','1','2','3','-','0','.','=','+'};
        for (char c1 : c) {
            if (c1 != '/' && c1 != '*' && c1 != '-' && c1 != '.' && c1 != '=' && c1 != '+') addButton(c1 + "", insert);
            else addButton(c1+"", command);
        }
        add(panel, BorderLayout.CENTER);
    }
    private void addButton(String label, ActionListener listener) {
        var button = new JButton(label);
        button.addActionListener(listener);
        panel.add(button);
    }
    private class InsertAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = e.getActionCommand();
            if (start) {
                currentInput = input;
                start = false;
            } else currentInput += input;
            display.setText(currentInput);
        }
    }
    private class CommandAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (!start) {
                try {
                    double currentValue = Double.parseDouble(currentInput);
                    calculate(currentValue);
                } catch (NumberFormatException ex) {
                    display.setText("Error");
                    start = true;
                    return;
                }
                start = true;
            }
            lastCommand = command;
            if (command.equals("=")) {
                result = 0;
                lastCommand = "=";
            }
        }
    }

    public void calculate(double x) {
        switch (lastCommand) {
            case "+" -> result = result + x;
            case "-" -> result = result - x;
            case "*" -> result = result * x;
            case "/" -> {
                if (x == 0) {
                    // 处理除零错误
                    display.setText("Error: Div by 0");
                    result = 0;
                    lastCommand = "=";
                    start = true;
                    return;
                } else {
                    result = result / x;
                }
            }
            case "=" -> result = x;
        }
        display.setText(String.valueOf(result));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(new CalculatorPanel());
        frame.setVisible(true);
    }
}