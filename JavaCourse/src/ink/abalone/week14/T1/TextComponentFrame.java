package ink.abalone.week14.T1;

import javax.swing.*;
import java.awt.*;

public class TextComponentFrame extends JFrame {
    public static final int TEXTAREA_ROWS = 8;
    public static final int TEXTAREA_COLUMNS = 8;
    public TextComponentFrame(){
        var textField = new JTextField();
        var passwordField = new JPasswordField();
        var northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(2,2));
        northPanel.add(new JLabel("User name: ",SwingConstants.RIGHT));
        northPanel.add(textField);
        northPanel.add(new JLabel("Password: ",SwingConstants.RIGHT));
        northPanel.add(passwordField);
        add(northPanel, BorderLayout.NORTH);
        var textArea = new JTextArea(TEXTAREA_ROWS, TEXTAREA_COLUMNS);
        var scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
        var southPanel = new JPanel();
        var insertButton = new JButton("Insert");
        southPanel.add(insertButton);
        insertButton.addActionListener(e->{
            textArea.append("User name: "+textField.getText()+" Password: "+new String(passwordField.getPassword())+"\n");
        });
        add(southPanel, BorderLayout.SOUTH);
        pack();
    }

    public static void main(String[] args) {
        new TextComponentFrame().setVisible(true);
    }
}
