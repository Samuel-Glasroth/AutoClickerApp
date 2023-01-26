package com.Applemite;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.event.*;


public class autoClicker extends JFrame implements KeyListener {
	
	private final String NAME = "Auto Clicker";
	private final int WIDTH = 600;
	private final int HEIGHT = 400;
	//private final Image ICON = ;
	
	private JTextArea clickSpeedTxtBox;
	private JButton setKeyBtn;
	private JButton startStopBtn;
	
	private String clickStartStopKey;
	private final int DEFUALTCLICKSPEED = 120;
	private int clickSpeed;
	private int timeToNext = 0; 
	private boolean autoClickState = false;
	private int mouseX;
	private int mouseY;
	
	private int btnLockTime = 90;
	private int timeToBtnLock = btnLockTime;
	
	public static void main(String[] args) {
		autoClicker ac = new autoClicker();
	}
	
	public autoClicker(){
		this.setTitle(NAME);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		//this.setIconImage(ICON);
		
		displayElements();
		addKeyListener(this);
		
		this.setVisible(true);
		
		clickSpeed = DEFUALTCLICKSPEED;
		System.out.println(KeyEvent.VK_LEFT);
		
		while(true) {
			System.out.println(autoClickState);
			if(startStopBtn.getModel().isPressed() && timeToBtnLock >= btnLockTime) {
				changeClickState();
			}else if(timeToBtnLock <= btnLockTime) {
				timeToBtnLock++;
			}
			
			//System.out.println(timeToNext);
			if(autoClickState) {
				autoClick();
				System.out.println("click");
				
				/*if(timeToNext == clickSpeed) {
					autoClick();
					System.out.println("click");
					timeToNext++;
				}else if(timeToNext < clickSpeed) {
					timeToNext++;
				}else if(timeToNext > clickSpeed) {
					timeToNext = 0;
				}*/
			}	
		}
	}
	
	private void displayElements() {
		JPanel panel = new JPanel();
		
		clickSpeedTxtBox = new JTextArea(HEIGHT/40,WIDTH/12);
		clickSpeedTxtBox.setText(Integer.toString(DEFUALTCLICKSPEED));
		clickSpeedTxtBox.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				
			}
		});
		panel.add(clickSpeedTxtBox);
		
		setKeyBtn = new JButton();
		setKeyBtn.setText("Set Start/Stop Key");
		setKeyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		panel.add(setKeyBtn);
		
		startStopBtn = new JButton();
		startStopBtn.setText("Start/Stop Clicking \n" + clickStartStopKey);
		startStopBtn.setBounds((int)(WIDTH/2+WIDTH*0.2), (int)(HEIGHT-HEIGHT*0.2), (int)(WIDTH*0.2), (int)(HEIGHT*0.2));
		/*startStopBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				autoClickState = !autoClickState;
			}
		});*/
		panel.add(startStopBtn);
		
		this.add(panel);
	}
	
	void autoClick() {
		Robot bot = null;
		
		try {
			bot = new Robot();				
		} catch (AWTException e) {
			e.printStackTrace();
		}
		mouseX = (int)MouseInfo.getPointerInfo().getLocation().getX();
		mouseY = (int)MouseInfo.getPointerInfo().getLocation().getY();
		bot.mouseMove(mouseX, mouseY);
		bot.mousePress(InputEvent.BUTTON1_MASK);
		bot.mouseRelease(InputEvent.BUTTON1_MASK);
		
		bot.delay(clickSpeed);
	}
	
	void changeClickState() {
		autoClickState = !autoClickState;
		timeToBtnLock = 0;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 192 && timeToBtnLock >= btnLockTime)
			changeClickState();
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
