package com.ttsoftware.utilities.messager;


import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Loginframe extends JFrame{//��¼������

	//*****************
	String sername;//��������

	int serport;//�˿�

	private Socket socket;

	private BufferedReader in;//������

	private PrintWriter out;//�����
	//***********//�������
	public static Vector<String> userlist= new Vector<String>();
	
	JPanel contentPane;
    JPanel jPanel1 = new JPanel();
    JLabel jLabel1 = new JLabel();

	JLabel jLabel2 = new JLabel();
    JTextField icqno = new JTextField();
    JLabel jLabel3 = new JLabel();
    JPasswordField userpwd = new JPasswordField();
    
    JLabel jLabel4 = new JLabel();
    JTextField servername = new JTextField();
    JLabel jLabel5 = new JLabel();
	JTextField serverport = new JTextField();

	JPanel jPanel2 = new JPanel();
    JButton login = new JButton();
    JButton newuser = new JButton();
    JButton quit = new JButton();

	
	public Loginframe() {
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);//��Ӧ����ѡ���¼�
		try {
			jbInit();//�򿪵�¼����
			sername = servername.getText().toString().trim();//�õ���������
			serport = Integer.parseInt(serverport.getText().trim());//�õ��˿ں�
			Socket socket = new Socket(InetAddress.getByName(sername), serport);//l�ӷ�����
			in = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));//���շ��������Ϣ
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream())), true);//���ͷ��������Ϣ
			//System.out.println("loging="+socket);
			new ServerListener();//����������¼�
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {//��¼�����
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}catch(Exception e){
				throw new RuntimeException(e);}
		Loginframe f = new Loginframe();
		f.setVisible(true);

	}
	
	private void jbInit() throws Exception {
		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(null);
		this.setSize(new Dimension(400, 300));//Dimension(int width,int height)
		this.setResizable(false);//�ָ�����Ҫ��ĳߴ�,���ڲ��õ���
		this.setTitle("��¼ICQ������");
		//contentPane.add(text, null);
		jPanel1.setBounds(new Rectangle(2, 3, 348, 110));//���þ���
		jPanel1.setLayout(null);
		jLabel1.setText("�������¼��Ϣ");
		jLabel1.setBounds(new Rectangle(5, 7, 103, 18));//���þ���

		jLabel2.setText("���icqno");
		jLabel2.setBounds(new Rectangle(7, 66, 60, 18));//���þ���
		icqno.setBounds(new Rectangle(68, 66, 97, 22));//���þ���
        jLabel3.setText("�������");
		jLabel3.setBounds(new Rectangle(173, 66, 60, 18));//���þ���
		userpwd.setBounds(new Rectangle(240, 66, 97, 22));//���þ���
		
		jLabel4.setText("������");
		jLabel4.setBounds(new Rectangle(7, 135, 41, 18));//���þ���
		servername.setBounds(new Rectangle(68, 135, 102, 22));//���þ���
		servername.setText("localhost");//Ĭ�Ϸ���������ΪLOCALHOST
		jLabel5.setText("��  ��");
		jLabel5.setBounds(new Rectangle(180, 135, 41, 18));//���þ���
		serverport.setBounds(new Rectangle(241, 135, 90, 22));//���þ���
		serverport.setText("6000");//Ĭ�϶˿ں���Ϊ6000

		jPanel2.setBounds(new Rectangle(8, 154, 347, 151));//���þ���
		jPanel2.setLayout(null);
		login.setText("��¼");
		login.setBounds(new Rectangle(5, 27, 79, 29));//���þ���
		login.addMouseListener(new java.awt.event.MouseAdapter() {//��������Ӧ�¼�
			public void mouseClicked(MouseEvent e) {//������½��ť
				login_mouseClicked(e);
			}
		});
		newuser.setText("ע��");
		newuser.setBounds(new Rectangle(118, 28, 79, 29));
		newuser.addMouseListener(new java.awt.event.MouseAdapter() {//��������Ӧ�¼�
			public void mouseClicked(MouseEvent e) {//�����ע�ᰴť
				newuser_mouseClicked(e);
			}
		});
		quit.setText("�˳�");
		quit.setBounds(new Rectangle(228, 26, 79, 29));
		quit.addMouseListener(new java.awt.event.MouseAdapter() {//��������Ӧ�¼�
			public void mouseClicked(MouseEvent e) {//������˳�ť
				quit_mouseClicked(e);
			}
		});

		

		contentPane.add(jPanel1, null);
		jPanel1.add(jLabel1, null);
		jPanel1.add(jLabel2, null);
		jPanel1.add(icqno, null);
		jPanel1.add(jLabel3, null);
		jPanel1.add(userpwd, null);
		contentPane.add(jPanel2, null);
		jPanel2.add(login, null);
		jPanel2.add(quit, null);
		jPanel2.add(newuser, null);
		contentPane.add(jLabel4, null);
		contentPane.add(servername, null);
		contentPane.add(jLabel5, null);
		contentPane.add(serverport, null);
	}

	protected void processWindowEvent(WindowEvent e) {//�����¼�����
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {//���ڹرգ��˳�ϵͳ
			System.exit(0);
		}
	}

	

	void login_mouseClicked(MouseEvent e) {//��¼��Ť

		try {
			
			out.println("login");//���߷�������Ҫ��¼
			out.println(icqno.getText());//��������ICQNO
			out.println(userpwd.getPassword());//������������

	        String str=" ";
		    str=in.readLine().trim();//�ӷ������ȡ��Ϣ
		    
//		    if(str.equals("login success")){//���ɹ��ʹ������
//		        this.dispose();//�رյ�½��ť
//		        
//		        int g=Integer.parseInt(icqno.getText());//�õ�ICQ���룬��4���Ͳ���
//		        clientframe f2=new clientframe(g,sername,serport);//�򿪿ͻ����������
//		        f2.setVisible(true);
//		                 }
//		    
//		      else if(str.equals("userpwd false")) //���������ʾ��������Ϣ
//		           JOptionPane.showMessageDialog(this,"�Բ�����������:-(","ok",JOptionPane.INFORMATION_MESSAGE);
//		      else if(str.equals("icqno false"))    //��¼�Ŵ�����ʾ��¼�ų����Ϣ
//		      JOptionPane.showMessageDialog(this,"�Բ���ICQNO�����:-(","ok",JOptionPane.INFORMATION_MESSAGE);
//		      
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	void newuser_mouseClicked(MouseEvent e) {//ע���û���Ŧ

		JDialog d = new Registerframe(sername, serport);//��ע�ᴰ��
		d.pack();//ע�ᴰ���ػ�
		d.setLocationRelativeTo(this);//����ѡ��̶��ڵ�ǰע�������
									 
		d.setSize(400, 400);
		d.show();
		this.dispose();//�ر�ע�ᰴť
	}

	void quit_mouseClicked(MouseEvent e) {//�رհ�Ť
		this.dispose();
		System.exit(0);//�˳��¼
	}
	
	public void showuserpwderror(){//��¼�����Ϣ
		JOptionPane.showMessageDialog(this, "�Բ�����������:-(", "ok",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void showusericqnoerror(){//��¼�����Ϣ
		JOptionPane.showMessageDialog(this, "�Բ���ICQ��������:-(", "ok",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void close() {//�رշ���
		this.dispose();
	}

	class ServerListener extends Thread {//�ͻ��˼���
	
    	public ServerListener() {
    		try {
    			
    			System.out.println("Listene to Server");//�ͻ��˼���ʼ
    			start();//run()
    		} catch (Exception e) {
    			try {
    				socket.close();
    			} catch (IOException e2) {
    				System.err.println("Socket not closed");
    			}
    		}
    	}
    	
    	public void run() {
    		try {
    			String label = "";//��ֺ��ֶ�
    			StringTokenizer st;//�ɲ���ֶ�
    			System.out.println("Thread from Server");
    			while (true) {
    				String fromserver = in.readLine().trim();
    				
    				System.out.println("receive data before "+fromserver);//���
    				
    				if (fromserver != null && !fromserver.equals("")) {//ȷ���ֶβ�Ϊ��
    				System.out.println("receive data "+fromserver);
    				
    				if (fromserver.equals("userpwd false")){//���������ʾ
    					   
    					System.out.println("Client: userpwdssss false");
    					
    					showuserpwderror();
    					
    				}
    				if (fromserver.equals("icqno false"))//ICQ���������ʾ
    				{   System.out.println("Client: icq number false");
    				    showusericqnoerror();
    				}  
    				
    				
    				st = new StringTokenizer(fromserver, ":");
    				if (st.hasMoreTokens())//��ֺ��Ƿ��������
						label = st.nextToken();//fromserverz�ֶ���"��"�ָ�����ֶ�label
    				
    				if (label.equals("login success")) {//���ɹ��ʹ������
    					System.out.println("Client: login success");
    					
    					
    					int g = Integer.parseInt(icqno.getText());//�õ�ICQ���룬��4���Ͳ���
    					Clientframe f1 = new Clientframe(g, sername, serport);
    					f1.setVisible(true);//�򿪿ͻ����������
    					
    					close();
    				}
    				
    			
    				
    				if (label.equals("user")) {//�յ�user��Ϣ
    				    System.out.println("request userlist");
    				    String userlist="";
    				    Clientframe.deflist.clear();//����û��б�
    				    while(st.hasMoreTokens()){
    				      userlist= st.nextToken();
    				      Loginframe.userlist.add(userlist);
    					  Clientframe.deflist.addElement(userlist);//����µ��û��б�
    						}
    				}

    				if(label.equals("exit")){//�յ�exit��Ϣ
    					System.out.println("one user is exit");
    					String user="";
    				    while(st.hasMoreTokens()){
    				    user=st.nextToken();
    				    
    					Clientframe.deflist.removeElement(user);//���һ�û�
    					
    					try{
    		    	    	in.close();
    		    	    	out.close();
    		    	    	socket.close();//�˳�
    		    	    }catch(Exception e){
    		    	    	
    		    	    }
    				    }
    				}
    				/*if (label.equals("welcome")) {//�յ�msg��Ϣ���ͻ������쿪ʼ
        				//System.out.println("message"+"\n");
        				String msg="";
    					if (st.hasMoreTokens()){	
        				msg = st.nextToken();
        				clientframe.textarea1.append(msg+"\n");
        				System.out.println("logint   ffffff");
        				}
        			}*/
    				
    				
    				
    				
    				
    			
    			}//if
    	
    		}//while
    	}catch (IOException e) {
    			System.err.println("IO Exception");
    		} 
    		finally {
				try {
					
					System.out.println("ClientSocket is Closed");
					socket.close();
				} catch (IOException e) {
					System.err.println("Socket not closed");
				}
			}//finally
    		
    	}//while
	}//run

}//server listen