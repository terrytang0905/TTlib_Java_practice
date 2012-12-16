package com.ttsoftware.utilities.messager;


import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;

public class Registerframe extends JDialog {//ע���û���
//���´����������
	JPanel panel1 = new JPanel();

	JLabel jLabel1 = new JLabel();

	JLabel jLabel2 = new JLabel();

	JTextField username = new JTextField();

	JLabel jLabel3 = new JLabel();

	JPasswordField userpwd = new JPasswordField();

	JLabel jLabel4 = new JLabel();

	JTextField email = new JTextField();

	JLabel jLabel5 = new JLabel();

	JTextField age = new JTextField();

	JLabel jLabel6 = new JLabel();

	JTextPane info = new JTextPane();

	JButton jButton1 = new JButton();

	JButton jButton2 = new JButton();

	JButton jButton3 = new JButton();

	JLabel jLabel7 = new JLabel();

	ButtonGroup group = new ButtonGroup();

	JRadioButton boy = new JRadioButton();

	JRadioButton girl = new JRadioButton();

	JLabel jLabel8 = new JLabel();

	JComboBox place = new JComboBox();

	//***************************

	String sername;//��������

	int serverport;//������˿�

	public Registerframe(String s, int port) {//���캯��
		sername = s;
		serverport = port;

		try {
			jbInit();//��������
			pack();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		//this(null, "", true);
	}

	void jbInit() throws Exception {//��������
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}catch(Exception e){
				throw new RuntimeException(e);}
		panel1.setLayout(null);
		this.getContentPane().setLayout(null);
		panel1.setMaximumSize(new Dimension(200, 200));
		panel1.setMinimumSize(new Dimension(200, 100));
		panel1.setBounds(new Rectangle(-2, 0, 419, 452));
		this.setTitle("register");

		jLabel1.setText("����д�������ݣ�*Ϊ������д��");
		jLabel1.setBounds(new Rectangle(9, 9, 250, 18));
		jLabel2.setText("*�ǳ�");
		jLabel2.setBounds(new Rectangle(9, 45, 41, 18));
		username.setBounds(new Rectangle(50, 44, 128, 22));
		jLabel3.setText("*����");
		jLabel3.setBounds(new Rectangle(200, 44, 41, 18));
		userpwd.setBounds(new Rectangle(247, 42, 100, 22));
		jLabel4.setText("�����ʼ�");
		jLabel4.setBounds(new Rectangle(2, 102, 58, 18));
		email.setBounds(new Rectangle(55, 96, 124, 22));
		email.setText("no email");
		jLabel5.setText("����");
		jLabel5.setBounds(new Rectangle(200, 102, 58, 18));
		age.setBounds(new Rectangle(250, 96, 60, 22));
		age.setText("����");
		jLabel6.setText("��������");
		jLabel6.setBounds(new Rectangle(6, 189, 87, 18));
		info.setBounds(new Rectangle(5, 208, 363, 103));
		jLabel7.setText("�Ա�");
		jLabel7.setBounds(new Rectangle(9, 156, 41, 18));
		
		boy.setText("��");
		boy.setBounds(new Rectangle(43, 152, 38, 26));
		girl.setText("Ů");
		girl.setBounds(new Rectangle(80, 152, 38, 26));
		group.add(boy);
		group.add(girl);
		boy.setSelected(true);
		
		jLabel8.setText("4��");
		jLabel8.setBounds(new Rectangle(147, 154, 41, 18));
		
		place.setToolTipText("");
		place.addItem("�Ϻ�");
		place.addItem("����");
		place.addItem("���");
		place.addItem("����");
		place.setBounds(new Rectangle(181, 153, 163, 22));

		jButton1.setText("ȷ��");
		jButton1.setBounds(new Rectangle(50, 330, 79, 29));
		jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				jButton1_mouseClicked(e);
			}
		});
		jButton2.setText("�˳�");
		jButton2.setBounds(new Rectangle(240, 329, 79, 29));
		jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				jButton2_mouseClicked(e);
			}
		});
		jButton3.setText("����");
		jButton3.setBounds(new Rectangle(140, 329, 79, 29));
		jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				jButton3_mouseClicked(e);
			}
		});

		this.getContentPane().add(jButton1, null);
		this.getContentPane().add(panel1, null);
		panel1.add(jLabel1, null);
		panel1.add(jLabel2, null);
		panel1.add(username, null);
		panel1.add(jLabel3, null);
		panel1.add(userpwd, null);
		panel1.add(jLabel4, null);
		panel1.add(email, null);
		panel1.add(jLabel5, null);
		panel1.add(age, null);
		panel1.add(jLabel6, null);
		panel1.add(info, null);
		panel1.add(jLabel7, null);
		panel1.add(boy, null);
		panel1.add(girl, null);
		panel1.add(jLabel8, null);
		panel1.add(place, null);
		panel1.add(jButton1, null);
		panel1.add(jButton2, null);
		panel1.add(jButton3, null);
	}

	void jButton1_mouseClicked(MouseEvent e) {
		try {
			System.out.println("sername=" + sername);
			System.out.println("serverport=" + serverport);
			Socket socket = new Socket(InetAddress.getByName(sername),
					serverport);//l�ӷ�����
			BufferedReader in = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())), true);

			if (username.getText().trim().equals("")
					|| userpwd.getPassword().equals("")) {
				JOptionPane.showMessageDialog(this, "�û�������붼����Ϊ��:-(", "warn",
						JOptionPane.INFORMATION_MESSAGE);
				jButton3_mouseClicked(e);
			}
			if (age.getText().trim().equals("")) {
				age.setText("0");
			}
			if (email.getText().trim().equals("")) {
				email.setText("null");
			}
			
			if (info.getText().trim().equals("")) {
				info.setText("null");
			}

			if (!(username.getText().trim().equals("") || userpwd.getPassword().equals(""))) {
				out.println("register");//�����½��û�����
				out.println(username.getText().trim());//�����سƵ���Ϣ
				out.println(userpwd.getPassword());
				if (boy.isSelected()) {
					out.println(boy.getText().trim());
				} else if (girl.isSelected()) {
					out.println(girl.getText().trim());
				}
				out.println(age.getText().trim());
				out.println(email.getText().trim());
				out.println(place.getSelectedItem());
				out.println(info.getText().trim());

				String str = " ";
				str = in.readLine().trim();//�ӷ������ȡ��Ϣ
				String label = " ";
				StringTokenizer st = new StringTokenizer(str, ":");
				if (st.hasMoreTokens())//��ֺ��Ƿ��������
					label = st.nextToken();
				if (label.equals("register success")) {//���ɹ��͸����û������
				    System.out.println("register success");
					while(st.hasMoreTokens()){
					String icqno = st.nextToken();
					int no = Integer.parseInt(icqno);
					System.out.print(no + "\n");
					JOptionPane.showMessageDialog(this, "your icqno is " + no,
							"ok", JOptionPane.INFORMATION_MESSAGE);
					 }
					Loginframe f2 = new Loginframe();
					f2.setVisible(true);
					this.dispose();
				}

				if (str.equals("register false")) { //�����
					System.out.println("register false");
					JOptionPane.showMessageDialog(this, "�Բ���ע������:-(", "warn",
							JOptionPane.INFORMATION_MESSAGE);
					jButton3_mouseClicked(e);
				}

			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	void jButton2_mouseClicked(MouseEvent e) {
		this.dispose();
		new Loginframe().setVisible(true);
	}

	void jButton3_mouseClicked(MouseEvent e) {
		this.username.setText("");
		this.email.setText("no email");
		this.userpwd.setText("");
		this.info.setText("");
		this.boy.setSelected(true);
		this.place.setSelectedIndex(0);
	}
}

