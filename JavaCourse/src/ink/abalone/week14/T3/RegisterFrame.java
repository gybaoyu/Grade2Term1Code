package ink.abalone.week14.T3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame {
    private static final int TEXTAREA_ROWS = 8;
    private static final int TEXTAREA_COLUMNS = 30;
    public Dimension getPreferredSize() {
        return new Dimension(320,300);
    }

    private JTextField textField;
    private JPasswordField passwordField;
    private JRadioButton male;
    private JRadioButton female;
    private JComboBox<String> comboBox;
    private JTextArea textArea;

    private void accountInit(JPanel northPanel){
        textField = new JTextField();
        passwordField = new JPasswordField();
        northPanel.add(new JLabel("User name: "));
        northPanel.add(textField);
        northPanel.add(new JLabel());
        northPanel.add(new JLabel("Password: "));
        northPanel.add(passwordField);
        northPanel.add(new JLabel());
    }

    private void sexInit(JPanel northPanel){
        ButtonGroup group = new ButtonGroup();
        male = new JRadioButton("Male",true);
        female = new JRadioButton("Female",false);
        group.add(male);
        group.add(female);
        northPanel.add(male);
        northPanel.add(female);
        northPanel.add(new JLabel());
    }

    private void comboBoxInit(JPanel northPanel){
        northPanel.add(new JLabel("Country: "));
        String[] countries = {"China", "USA", "India","Japan","England"};
        comboBox = new JComboBox<>(countries);
        northPanel.add(comboBox);
    }

    private void showAreaInit(){
        textArea = new JTextArea(TEXTAREA_ROWS, TEXTAREA_COLUMNS);
        var scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
        textArea.setEditable(false);
    }

    private void submitInit(JPanel southPanel){
        JButton submit = new JButton("Submit");
        southPanel.add(submit);

        submit.addActionListener(e -> {
            String userName = textField.getText();
            String password = new String(passwordField.getPassword());
            String selectedSex = male.isSelected() ? "Male" : "Female";
            String selectedCountry = (String) comboBox.getSelectedItem();
            String userInfo = String.format(
                    "UserName: %s\nPassword: %s\nSex: %s\nCountry: %s",
                    userName, password, selectedSex, selectedCountry);
            textArea.setText(userInfo);
        });
    }

    public RegisterFrame(){
        setTitle("Register");
        var northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(4,3));
        accountInit(northPanel);
        sexInit(northPanel);
        comboBoxInit(northPanel);
        add(northPanel,BorderLayout.NORTH);
        showAreaInit();

        var southPanel = new JPanel();
        submitInit(southPanel);
        add(southPanel,BorderLayout.SOUTH);
        pack();
    }

    public static void main(String[] args) {
        JFrame frame = new RegisterFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
