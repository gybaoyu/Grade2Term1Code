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
    public CalculatorPanel() {
        setLayout(new BorderLayout());
        result = 0;
        lastCommand = "=";
        start = true;
        display = new JButton("0");
        display.setEnabled(false);
        add(display, BorderLayout.NORTH);
        var insert = new InsertAction();
        var command = new CommandAction();
        panel = new JPanel();
        panel.setLayout(new GridLayout(4,4));
        char[] c = {'7','8','9','/','4','5','6','*','1','2','3','-','0','.','=','+'};
        for (char c1 : c) {
            if (c1!='/'&&c1!='*'&&c1!='-'&&c1!='.'&&c1!='='&&c1!='+')addButton(c1+"",insert);
            else addButton(c1+"",command);
        }
        add(panel,BorderLayout.CENTER);
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
            if (start){
                display.setText(display.getText()+input);
                start = false;
            }
        }
    }
    private class CommandAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (start){
                if (command.equals("-")){
                    display.setText(command);
                    start = false;
                }else lastCommand = command;
            }else{
                calculate(Double.parseDouble(display.getText()));
                lastCommand = command;
                start = true;
            }
        }
    }

    public void calculate(double x){
        switch (lastCommand) {
            case "+" -> result = result + x;
            case "-" -> result = result - x;
            case "*" -> result = result * x;
            case "/" -> result = result / x;
            case "=" -> result = x;
        }
        display.setText(""+result);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.add(new CalculatorPanel());
        frame.setVisible(true);
    }
}
