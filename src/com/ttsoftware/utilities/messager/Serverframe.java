package com.ttsoftware.utilities.messager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.Border;



/*
 * Created on 2005-11-14
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author TANG
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Serverframe extends JFrame {
	public static Vector clients = new Vector();
	static JTextArea textarea1, textarea2;
	
	private JLabel time;
	
	public  static JTextArea timedisplay;
	
	public  static Calendar now;
	
	public  static String strtime;

	private JLabel label1;
	private JLabel label2;
	private JLabel label3;

	private JButton button1;
	
	private JButton button2;

	private JScrollPane sb1, sb2, sb3;

	private JList list1;

	static DefaultListModel deflist = new DefaultListModel();

	private Socket socket;

	private BufferedReader in;

	private PrintWriter out;

	public Serverframe() {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}catch(Exception e){
				throw new RuntimeException(e);}
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
	    this.setTitle("Server frame");
		this.setSize(new Dimension(580, 500));
		this.setVisible(true);
		//panel.setBackground(Color.gray);
		panel.setBounds(new Rectangle(2, 3, 580, 500));
		
		label1 = new JLabel("chat msg:");
		label1.setBounds(200, 5, 100, 35);
		panel.add(label1);
		textarea1 = new JTextArea(20, 35);//JTextArea(int rows, int columns)
		textarea1.setBounds(250, 400, 100, 50);
		Border brd = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black);
		sb1 = new JScrollPane(textarea1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sb1.setBounds(180, 30, 330, 240);
		textarea1.setLineWrap(true);
		// If set to true the lines will be wrapped if they are too long to fit within the allocated width. If set to false, the lines will always be unwrapped.
		panel.add(sb1);
		sb1.setBorder(brd);
		
		label2 = new JLabel("client list:");
		label2.setBounds(15, 5, 100, 35);
		panel.add(label2);
		list1 = new JList(deflist);
		list1.setBounds(50, 100, 100, 100);
		sb3 = new JScrollPane(list1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sb3.setBounds(15, 30, 130, 240);
		panel.add(sb3);
		
		label3 = new JLabel("System msg:");
		label3.setBounds(150, 275, 100, 35);
		panel.add(label3);
		textarea2 = new JTextArea(2, 25);
		textarea2.setBounds(150, 300, 100, 50);
		sb2 = new JScrollPane(textarea2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sb2.setBounds(150, 310, 230, 75);
		textarea2.setLineWrap(true);
		panel.add(sb2);
		
		button1 = new JButton("exit");
		button1.setBounds(400, 380, 80, 35);
		panel.add(button1);
		ButtonListener1 blistener1 = new ButtonListener1();//����
		button1.addActionListener(blistener1);
		
		
		button2 = new JButton("send");
		button2.setBounds(400, 300, 80, 35);
		panel.add(button2);
		ButtonListener2 blistener2 = new ButtonListener2();//����
		button2.addActionListener(blistener2);
		
		time = new JLabel("current system time");
		time.setBounds(45,290,120,35);
		
		timedisplay = new JTextArea();
		timedisplay.setBounds(45,330,130,35);
		
		
	}

	public static void main(String[] args) {
		
		new Serverframe();
		
	}
	
	private void close() 
	{this.dispose();}

	class ButtonListener2 implements ActionListener {
		String message = "";

		
		
		public void actionPerformed(ActionEvent ev) {
			JButton source = (JButton) ev.getSource();
			try {
				message = "System say " + textarea2.getText();
				textarea1.append(message + "\n");
				textarea2.setText("");
				String msg = "msg: " + message;
				ServerThread.SendServerMsg(msg);
				
				File file = new File("chatrecord.txt");
				FileWriter fr = new FileWriter(file, true);
				BufferedWriter b = new BufferedWriter(fr);
				b.write(message+"\n");
				b.flush();
			
			    } 
			catch (Exception e) {}
		 }

		
	}
	
	class ButtonListener1 implements ActionListener {
		
		
		public void actionPerformed(ActionEvent ev) {
			JButton source = (JButton) ev.getSource();
			close();
	
	}
		

}

}


