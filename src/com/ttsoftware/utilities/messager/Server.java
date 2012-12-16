package com.ttsoftware.utilities.messager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.StringTokenizer;
import java.util.Vector;





/*
 * Created on 2005-11-14
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author TANG
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Server {
	static final int PORT = 6000;

	public static Vector<Socket> clients = new Vector<Socket>();//�ͻ��߳��б�
    
	public static Vector<String> userlist= new Vector<String>();//��¼�������û��б�
	
	public static void main(String[] args) throws IOException {
		ServerSocket s = new ServerSocket(PORT);
		System.out.println("Server Started"+s);
		Serverframe f=new Serverframe();
		f.setVisible(true);	
	    try {
		 
		 while (true) {
			Socket socket = s.accept(); //����˿ڣ�����Ƿ�������
			System.out.println("Connection accepted-main: " + socket);
			
			
			try {
				clients.add(socket);
				new ServerThread(socket);//l�ӿͻ���
				
			} catch (IOException e) 
			{socket.close();}
            }//while
	        }//try
	         finally 
			 {s.close();
		      System.out.println("chat server close");
		      }
	        
	}//main

}

	
class ServerThread extends Thread{//�̳��߳�
	
	private Socket socket;//�����׽ӿ�
	private BufferedReader in;//����������
	private PrintWriter out;//���������
	private PrintWriter toclient;
	int icqno;//���������icqno����
	
	
	
	public ServerThread(Socket s) throws IOException {//�̹߳��캯��
	
	  socket=s;//ȡ�ô��ݲ���
	  in=new BufferedReader(new InputStreamReader(socket.getInputStream()));//����������
	  out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);//���������
	  System.out.println("ServerThread Open");
	  start();//���߳�
	
	}
	public static void SendServerMsg(String msg) throws Exception {//������Ϣ�ӷ�����˵����еĿͻ���
		for (int i = 0; i < Server.clients.size(); i++) {
			if ((Server.clients.get(i)).isConnected()) {
				PrintWriter ssm= new PrintWriter(
						         new BufferedWriter(
								 new OutputStreamWriter(
						         (Server.clients.get(i))
						         .getOutputStream())), true);
				ssm.println(msg);
			}
		}
	}
	
	public static void SendPrivate(String username,String msg) throws Exception{
		for (int i = 0;i<Server.userlist.size();i++){
			String ss = Server.userlist.get(i);
			System.out.println("SendPrivate = "+ss);
			System.out.println("Sendto = "+username);
			if (Server.userlist.get(i).equals(username)){
				  System.out.println("i="+i);
				  PrintWriter ssm= new PrintWriter(
				         new BufferedWriter(
						 new OutputStreamWriter(
				         (Server.clients.get(i))
				         .getOutputStream())), true);
		       ssm.println(msg);
		       //ssm.flush();
		       int j = i;
		       System.out.println("j="+j);
		       System.out.println("--------"+(Server.clients.get(i)));
		       System.out.println("private:"+username+msg);
			}
		}
	}
	
    public void run(){//�̼߳�����
		 try {
			String nextstr = "";
			StringTokenizer st;//��ݷָ����ַ�ָ�ɱ�ǣ�Token����Ȼ�������󷵻ظ���ǡ�
		 	while(true){
	
	            String str=in.readLine();//ȡ�������ַ�
	            
	            if (str != null && !str.equals("")){ 
	            	
	            	System.out.println("str :" + str);
	            	st = new StringTokenizer(str, ":");//��str�÷ָ��st�ָ4
					if (st.hasMoreTokens()){
						Returns:nextstr = st.nextToken();
					    System.out.println("nextstr :" + nextstr);
					}
                  																
					if (nextstr.equals("login")){
						
					  try{ 
					  	   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");//l����ݿ�
				           Connection c=DriverManager.getConnection("jdbc:odbc:javaicq"," "," ");
				           String sql="select username,userpwd from icq where icqno=?";//׼������ݿ�ѡ���ǳƺ�����
				           PreparedStatement prepare=c.prepareCall(sql);//�趨��ݿ��Ѱ���
				           
				           String icqno=in.readLine();//����������ַ�
				           int g=Integer.parseInt(icqno);//�ַ�icqno��Ϊ����
				           System.out.println("icqno:"+icqno);
				           String userpwd=in.readLine().trim();//ȡ�����������
				           System.out.println("userpwd:"+userpwd);
				           prepare.clearParameters();
				           prepare.setInt(1,g);
                           				           
                           ResultSet r=prepare.executeQuery();//ִ����ݿ��Ѱ,ȡ�ý��
                           //���±Ƚ�����ĺ����������Ƿ���ͬ
                           
				           if(r.next()){// ���ָ��ָ����һ��
										
				              String user=r.getString("username").trim();
				              //String icq=r.getString("icqno").trim();
				              System.out.println("username:"+user);
				              String pass=r.getString("userpwd").trim();//ȡ���ֶ�userpwd�����ݸ�pass
				              System.out.println("password:"+pass);
				           
				              if(userpwd.regionMatches(0,pass,0,pass.length()))//ƥ��
				              { 
				              	
				              	out.println("login success");//�����ͬ�͸��߿ͻ�ok,���Ҹ�����ݿ��û�Ϊ����,�Լ�ע���û���ip
															 // ��ַ
				                user=user+":"+icqno;
				                Server.userlist.add(user);
				                Serverframe.deflist.addElement(user); 
				                
				                
				                
				                
				               
				                String setip="update icq set ip=? where icqno=? "; //update
																				   // ipaddress
				                PreparedStatement prest=c.prepareCall(setip);
				                prest.clearParameters();
				                prest.setString(1,socket.getInetAddress().getHostAddress());//����ip=?
				                prest.setInt(2,g);//����icqno=?
				                int set=prest.executeUpdate();
				                System.out.println("update:"+set);
				                
				                String list=" ";
				                for (int i = 0; i < Server.userlist.size(); i++)
				                	list+=Server.userlist.get(i)+":";
				                for (int i = 0; i < Server.clients.size(); i++) {
									 if ((Server.clients.get(i)).isConnected()) {
										toclient= new PrintWriter(
												  new BufferedWriter(
												  new OutputStreamWriter(
												  (Server.clients.get(i))
												  .getOutputStream())),true);
										toclient.println("user:" + list);
										
									     }//if
								       }//for
				                String msg = "welcome:"+"��ӭ"+user+"����������"; 
				                SendServerMsg(msg);
				                Serverframe.textarea1.append(msg+"\n");
				                 }//if
				        
				            else {out.println("userpwd false");//������߿ͻ�ʧ��
				                  System.out.println("userpwd false");
				              	  r.close();
				                  c.close();}
				           }//if
				            else {out.println("icqno false");
				                  System.out.println("icqno false");
				                  r.close();
				                  c.close();}
				                  }
				        catch (Exception e)
						{e.printStackTrace();}
				       }//end login����¼����
					
					
					
					if(nextstr.equals("msg")){
						String msg="";
						if (st.hasMoreTokens())
							msg=st.nextToken();
							Serverframe.textarea1.append(msg + "\n");
							try{
								msg="msg:"+msg;
								SendServerMsg(msg);
							}catch(Exception e){
								
							}		
					}
					
                     if(nextstr.equals("register")){
						System.out.println("register");
						try{ Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");//l����ݿ�
						     Connection c2=DriverManager.getConnection("jdbc:odbc:javaicq"," "," ");
						     String username=in.readLine().trim();
						     String userpwd=in.readLine().trim();
						     String sex=in.readLine().trim();
						     String age=in.readLine().trim();
						     String email=in.readLine().trim();
						     String place=in.readLine().trim();
						     System.out.println(place);
						     String info=in.readLine().trim();
						     System.out.println(info);
						     String matchsql="select username,userpwd from icq";
						     PreparedStatement pre=c2.prepareCall(matchsql);
						     ResultSet m=pre.executeQuery();
						     if(m.next()){
						        String user=m.getString("username").trim();
				                String pass=m.getString("userpwd").trim();
						     if(userpwd.regionMatches(0,pass,0,pass.length())||username.regionMatches(0,user,0,user.length())){
						     	System.out.println("username or userpwd is same");
						     	out.println("register false");
						     	c2.close();
						     }
						     else{
						     	String newsql="insert into icq(username,userpwd,sex,age,email,place,info) values(?,?,?,?,?,?,?)";
	                             //	׼�������û����ǳƣ�����,�Ա����䣬email���������ϵ���Ϣ
							     PreparedStatement prepare2=c2.prepareCall(newsql);
						     prepare2.clearParameters();
						     prepare2.setString(1,username);
						     prepare2.setString(2,userpwd);
						     prepare2.setString(3,sex);
						     prepare2.setString(4,age);
						     prepare2.setString(5,email);
						     prepare2.setString(6,place);
						     prepare2.setString(7,info);
						     int r2=prepare2.executeUpdate();//ִ����ݿ����
						     
						     String sql2="select icqno from icq where username=?";//�ͻ���ע����û���ѡ��icqno
						     PreparedStatement prepare3=c2.prepareCall(sql2);
						     prepare3.clearParameters();
						     prepare3.setString(1,username);
						     ResultSet r3=prepare3.executeQuery();
						     while(r3.next()){ //out.println(r3.getInt(1));
						      icqno=r3.getInt(1);//columnIndex - the first
												 // column is 1, the second is
												 // 2, ...
						      //�һص�ǰ����ָ����1�е�ֵ
						      System.out.println(icqno);
						      
						      }
						     String reg="register success:"+icqno;
						     out.println(reg);
						     c2.close();
						      
						     }
						     }
					}//��ݿ����
						 catch(Exception e){e.printStackTrace();
						       out.println("register false");}
					     
					    }//end new,�½��û�����
                     
                     
			
                     else if(nextstr.equals("exit")){//���´����û��˳����
					 	try{ Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					 	     Connection c8=DriverManager.getConnection("jdbc:odbc:javaicq"," "," ");
					 	     //l����ݿ⣬��ݽ��ܵ��û����룬����״̬�ֶ���Ϊ0����ip��ַ��Ϊ��
					 	     String icqno=in.readLine();//����������ַ�
					         int myicqno=Integer.parseInt(icqno);
					 	     System.out.println("myicqno:"+myicqno);
					 	     String status="update icq set status=0 , ip=' ' where icqno=?";
				             //String user="select username where status=0";
					 	     PreparedStatement prest8=c8.prepareCall(status);
					 	     
					 	     prest8.clearParameters();
					 	     prest8.setInt(1,myicqno);
					 	     int r8=0;
							 r8=prest8.executeUpdate();
					 	     if(r8==1) 
					 	     {
					 	     String sql2="select username from icq where icqno=?";
						      PreparedStatement prepare=c8.prepareCall(sql2);
						     // prepare.clearParameters();
						      prepare.setInt(1,myicqno);
						      ResultSet r=prepare.executeQuery();
						      while(r.next()){
						      	String user=r.getString("username");
						      	String msg="msg:"+user+" logout";
						      	Serverframe.textarea1.append(msg + "\n");
						      	SendServerMsg(msg);
						      	System.out.println("users logout");
						      	user=user+":"+icqno;
						      	Server.userlist.remove(user);
						      	System.out.println("del users");
						      	Serverframe.deflist.removeElement(user);
	
						    	  String leaveuser="exit:"+user;
						      	SendServerMsg(leaveuser);
						      	}
					 	     	
						      System.out.println("ok exit");
					 	      
					 	     }
					 	     
					 	     else  System.out.println("false exit");
					 	     
						      
					 	}catch (Exception e)
						{e.printStackTrace();
						 System.out.println("exit false");}
					}//exit end
                    
                if(nextstr.equals("privatechat")){
                	String icqno=in.readLine();
                	String usname=in.readLine();
                	String message=in.readLine();
                	try{
                		String message1="msg:"+icqno+"��������Ļ���"+message;
                		String message2=icqno+"��"+usname+"�����Ļ���"+message+"\n";
                		//System.out.println("msg:"+message);
                		
                		SendPrivate(usname,message1);
                		Serverframe.textarea1.append(message2);
                	}catch(Exception e){}
                }
	            
                   
				   
			   
			    System.out.println("Echoing :"+str);
	            }//if
	           
		 	}//while
		 	  
		 	}//try
		 	catch(IOException e){
		 	System.err.println("Exception: " + e);
			e.printStackTrace();}//�����쳣
			/*
			 * finally { try {socket.close(); System.out.println("ServerSocket
			 * is Closed");} catch(IOException e) {e.printStackTrace();
			 * System.err.println("ServerSocket not closed");} }
			 */
					
		}//run
    
    
    
    
					 	
 

}//ServerThread
              


