package com.ttsoftware.utilities.messager;

import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;



/*
 * Created on 2005-11-21
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

public class Clientframe extends JFrame implements Runnable{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	JPanel contentPane;

   
	  public static Vector user = new Vector();
	 

          //�����ǳ������ı�
	  public static JTextArea textarea1, textarea2;

	  public static JList userList = null;

	  static DefaultListModel deflist = new DefaultListModel();

	  JLabel label1,label2,label3;

	  JList list = new JList(deflist);

	  JButton button1;
	  JButton button2;
	  JButton button3;
	  //JButton button4;
	  
	  private JScrollPane sb1, sb2, sb3;
	  
	  //private JPanel jContentPane = null;

	  private JTextArea msgArea = null;

	  //private JTextField msg = null;

	  //private JButton buttonSend = null;

	
	
          //������������ر�
	  Socket socket;
	  public BufferedReader in;
	  public PrintWriter out;
	  //���������Ķ�����
	  String server;
	  int serverport=6000;
	  byte array[]=new byte[512];
	  Thread thread;
	  int icqno;
	  String username;
	  String received;
    
	  
	  public  Clientframe (int g,String sername,int serport) {//������캯��
	  	    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
                
          try {
	  	       icqno=g;
	  	      
	  	       server=sername;
	  	       serverport=serport;
	  	       
	  	       ConnectServer(icqno);
	  	       
	  	       jbInit(icqno);
	  	       
	  	       thread=new Thread(this);
	  	       thread.start();
	  	     
	  	        }
            catch(Exception e) {
	  	      e.printStackTrace();
	  	     }
	  }
	 
	  public void  ConnectServer(int icqno){//l�ӷ�����
	    try{  socket=new Socket(InetAddress.getByName(server),serverport);
              in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
              out=new PrintWriter(new BufferedWriter(
                            new OutputStreamWriter(socket.getOutputStream())),true);
             
	    }catch(IOException e){}
	  }//connectto server
	  

       private void jbInit(int icq) throws Exception  {//��������
	    JPanel panel = new JPanel();
		getContentPane().add(panel);
		this.setTitle("icqno:"+icq+" -chat frame");
		panel.setLayout(null);
		setSize(550, 500);
		setVisible(true);
		//panel.setBackground(Color.GRAY);
		
		label1 = new JLabel("�����û��б�:");
		label1.setBounds(35,10,100,40);
		label2 = new JLabel("�����¼:");
		label2.setBounds(180,10,100,40);
		panel.add(label1);
		panel.add(label2);
		//list.setBounds(20,100,70,250);
		
		sb1 = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sb1.setBounds(35, 50, 110, 340);
		panel.add(sb1);
		
				
		textarea1 = new JTextArea(15, 12);
		//textarea1.setBounds(135,20,245,130);
		textarea1.setEditable(false);
		sb2 = new JScrollPane(textarea1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sb2.setBounds(180, 50, 300, 205);
		panel.add(sb2);
		
		label3 = new JLabel("��Ϣ�����");
		label3.setBounds(180,260,100,40);
		panel.add(label3);
		
		textarea2 = new JTextArea(8, 12);
		//textarea2.setBounds(80,170,150,75);
		sb3 = new JScrollPane(textarea2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sb3.setBounds(180, 290, 300, 100);
		panel.add(sb3);
		
		button1 = new JButton("Ⱥ��");
		button1.setBounds(180, 400,70,30);
		panel.add(button1);
		ButtonListener blistener = new ButtonListener();
		button1.addActionListener(blistener);

		button2 = new JButton("�˳�");
		button2.setBounds(410,400,70,30);
		panel.add(button2);
		ButtonListener1 blistener1 = new ButtonListener1();
		button2.addActionListener(blistener1);
		
		button3=new JButton("˽��");
		button3.setBounds(295,400,70,30);
		panel.add(button3);
		ButtonListener2 blistener2 = new ButtonListener2();
		button3.addActionListener(blistener2);
		
		//button4=new JButton("qq");
		//button4.setBounds(35,400,70,30);
		//panel.add(button4);
		//ButtonListener4 blistener4 = new ButtonListener4();
		//button4.addActionListener(blistener4);

	  } 
	 
	  
	 
        private JTextArea getMsgArea() {//���ն�����Ϣ
		if (msgArea == null) {
			msgArea = new JTextArea();
			msgArea.setBounds(new java.awt.Rectangle(14, 13, 367, 292));//(int x,int y,int width,int height)
		}
		return msgArea;
	    }

//	    private JList getUserList() {//�����б���Ϣ
//		if (userList == null) {
//			userList = new JList();
//			userList.setBounds(new java.awt.Rectangle(388, 16, 99, 287));
//		}
//		return userList;
//	    }
//
//	    private JTextField getMsg() {//���յ�����Ϣ
//		if (msg == null) {
//			msg = new JTextField();
//			msg.setBounds(new java.awt.Rectangle(16, 321, 337, 22));
//		}
//		return msg;
//	    }
//
//        private JPanel getJContentPane() {
//		if (jContentPane == null) {
//			jContentPane = new JPanel();
//			jContentPane.setLayout(null);
//			jContentPane.add(getMsgArea(), null);
//			jContentPane.add(getUserList(), null);
//			jContentPane.add(getMsg(), null);
//			jContentPane.add(getButtonSend(), null);
//		}
//		return jContentPane;
//	    }
//
//	
//        private JButton getButtonSend() {
//		if (buttonSend == null) {
//			buttonSend = new JButton();
//			buttonSend.setBounds(new java.awt.Rectangle(359, 320, 86, 23));
//			buttonSend.setText("����");
//			buttonSend.addActionListener(new java.awt.event.ActionListener() {
//				public void actionPerformed(java.awt.event.ActionEvent e) {
//					if (!getMsg().getText().equals("")
//							&& getMsg().getText() != null) {
//						out.println(getMsg().getText());
//					}
//				}
//			});
//		}
//		return buttonSend;
//	    }

	  	
      public  void  run(){//���º������޼���ϵͳ����Ϣ

	   try {
				while (true) {
					String fromserver = in.readLine();
					String label = "";//��ֺ��ֶ�
	    			StringTokenizer st;//�ɲ���ֶ�
					if (fromserver != null && !fromserver.equals("")) {
						//getMsgArea().append(in + "\n");
						st = new StringTokenizer(fromserver, ":");
						if (st.hasMoreTokens())//��ֺ��Ƿ��������
							label = st.nextToken();//fromserverz�ֶ���"��"�ָ�����ֶ�label
	    				
						if (label.equals("welcome")) {//�յ�msg��Ϣ���ͻ������쿪ʼ
	        				//System.out.println("message"+"\n");
	        				String msg="";
	    					if (st.hasMoreTokens()){	
	        				msg = st.nextToken();
	        				textarea1.append(msg+"\n");
	        				System.out.println("clientdd");
	        				}
						}
						if (label.equals("msg")) {//���ɹ��ʹ������
							 String info =(String)st.nextToken();
						     textarea1.append(info+"\n");
					}
				}
			}
	   }
	    catch (IOException e) {
				System.err.println("IO Exception");}
		finally {
				try {
					socket.close();
				} catch (IOException e) {
					System.err.println("Socket not closed");}
			}
		}//run end
	
      public static void main(String[] args) {
      	try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}catch(Exception e){
				throw new RuntimeException(e);}
   
	  }
	
      public void close() 
      {this.dispose();
	   }
      
      class ButtonListener implements ActionListener {
    	String message = "";

    	public void actionPerformed(ActionEvent ev) {
    		JButton source = (JButton) ev.getSource();
    		try {
    			
    			message = icqno + " Say " + textarea2.getText();
    			//textarea1.append(message + "\n");
    			textarea2.setText("");
    			message = "msg: " + message;
    			out.println(message);

    			File file = new File("chatrecord.txt");
    			FileWriter fr = new FileWriter(file, true);
    			BufferedWriter b = new BufferedWriter(fr);
    			b.write(message+"\n");
    			b.flush();
    			b.close();
    		} catch (Exception e) {}
    	}
    }	
      
      class ButtonListener1 implements ActionListener {

    	public void actionPerformed(ActionEvent ev) {
    		JButton source = (JButton) ev.getSource();
    		out.println("exit");
    		out.println(icqno);
    		
    		close();
    		System.exit(0);//�˳��¼
    	    
      }	
    	
      }
        
      public void showerror(){//��¼�����Ϣ
      	JOptionPane.showMessageDialog(this, "����ѡ��һ������ٽ���˽�ģ�", "ok",JOptionPane.INFORMATION_MESSAGE);
	}
      class ButtonListener2 implements ActionListener {

    	public void actionPerformed(ActionEvent ev) {
    		if(list.isSelectionEmpty()){
    			showerror();
    		}
    		
    		else{
    			JButton source = (JButton) ev.getSource();
    		
    		String usname = (String)list.getSelectedValue();
    		String message = textarea2.getText();
    		out.println("privatechat");
    		out.println(icqno);
			out.println(usname);
    		out.println(message);
    		out.flush();
    		
    		message = "��"+usname+"�����Ļ�:" +textarea2.getText();
			textarea1.append(message + "\n");
			textarea2.setText("");
			
			message = "msg: " + message;
    		}
    		
    		
    		
      }	
    	
      }
      
      /*class ButtonListener4 implements ActionListener {

    	public void actionPerformed(ActionEvent ev) {
    		String value = (String)list.getSelectedValue();
    		System.out.println(value);
    		
      }	
    	
      }*/
          

} //end class clientframe
	  





      


	

