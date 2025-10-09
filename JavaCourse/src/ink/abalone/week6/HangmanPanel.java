package ink.abalone.week6;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//C:\ProgramData\Oracle\Java\javapath;

public class HangmanPanel extends JPanel {
	
	
	
	public HangmanPanel(){
		lexicon = new HangmanLexicon();
		
		initWord();
		initMessage();
		
		setPreferredSize(new Dimension(800,600));
		setLayout(new GridLayout(1,2));
		leftPanel = new InputPanel();
		displayPanel = new HangmanDisplayPanel();
		add (leftPanel);
		add (displayPanel);
		
		
		
		
	}
	private void initWord(){
		Random generator = new Random();
		int index = generator.nextInt(lexicon.getWordCount());
		wordDisplay = new StringBuffer("");
		try {
			word = lexicon.getWord(index);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i<word.length();i++){
			wordDisplay.append("—");
		}
		
		
		
		
	}
	private void initMessage(){
		guessNumLeft = 8;
		message = "Welcome to Hangman!\n";
		message += word+"\n";//for test;
		message += "The word now looks like this:";
		message += wordDisplay;
		message +="\n";
		message +="You have " + guessNumLeft + " guess left\n";
	}
	
	private class InputPanel extends JPanel{
		public InputPanel(){
			setPreferredSize(new Dimension(400,600));
			setBorder(BorderFactory.createLoweredBevelBorder());
			setLayout(new FlowLayout());
			
			messageTextArea = new JTextArea(message);
			//messageTextArea.setPreferredSize(new Dimension(380,560));
			
			messageTextArea.setEditable(false);
		    messageTextArea.setAutoscrolls(true);
		    
		    JScrollPane scroll = new JScrollPane(messageTextArea);
		    scroll.setVerticalScrollBarPolicy( 
		    		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		    scroll.setAutoscrolls(true);
		    scroll.setPreferredSize(new Dimension(380,560));
		    add(scroll);
			
			JLabel hint = new JLabel("Your guess:");
			input = new JTextField();
			confirm = new JButton("OK");
			restart = new JButton("Restart");
			confirm.addActionListener(new ConfirmListener());
			restart.addActionListener(new RestartListener());
			JPanel bottom = new JPanel();
			bottom.setLayout(new GridLayout(1,4));
			bottom.add(hint);
			bottom.add(input);
			bottom.add(confirm);
			bottom.add(restart);
			
			add(bottom);
		}

		/* (non-Javadoc)
		 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
		 */
		@Override
		protected void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			super.paintComponent(g);
			messageTextArea.setText(message);
			messageTextArea.setCaretPosition(message.length()); 
			input.setText("");
			input.requestFocus();
			
		}
		
		
	}
	
	private class ConfirmListener implements ActionListener{
		private static boolean guessLetter(char c,StringBuffer wordDisplay,String word){
			boolean flag = false;
			for(int i=0;i<word.length();i++){
				if(word.charAt(i)==c) {
					wordDisplay.setCharAt(i,c);
					flag = true;
				}
			}
			return flag;
		}
		@Override
		public void actionPerformed(ActionEvent event) {
			
			String guess = input.getText().toUpperCase();
			message += "You guess is "+guess+"\n";
			if (guess.length()==1){
				//判断输入的 guess是否在word中，需要完成：
				// 如果在，message += "The guess is correct\n";
				if (!guessLetter(guess.charAt(0),wordDisplay,word)){
					//guess不在word时的处理，已完成。
					message += "There are no "+guess+"'s in the word.\n";
					guessNumLeft--;
					displayPanel.noteIncorrectGuess(8-guessNumLeft);
					if (guessNumLeft == 0){
						message += "You are completely hung!\n";
						message += "The word are "+word+"\n";
						message += "You lose\n";
						input.setEnabled(false);
						confirm.setEnabled(false);
					}
				}
			}else{
				message += "The guess is illegal\n";
			}
			
			
			if (wordDisplay.indexOf("—")==-1){
				message += "You guess the word "+word+"\n";
				message += "You win";
				input.setEnabled(false);
				confirm.setEnabled(false);
			}else if (guessNumLeft != 0){
				message += "The word now looks like this:";
				message += wordDisplay;
				message +="\n";
				message +="You have " + guessNumLeft + " guess left\n";
			}
			
			repaint();
		}
		
	}
	

	private class RestartListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			initWord();
			initMessage();
			input.setEnabled(true);
			confirm.setEnabled(true);
			displayPanel.reset();
			repaint();
		}
		
	}
	
	private InputPanel leftPanel;
	private HangmanDisplayPanel displayPanel;
	
	private JTextArea messageTextArea;
	private JTextField input;
	private JButton confirm;
	private JButton restart;
	private String message;
	private int guessNumLeft;
	private HangmanLexicon lexicon;
	private String word;
	private StringBuffer wordDisplay;
	
}
