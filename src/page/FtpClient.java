package page;
import java.net.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

//import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;


public class FtpClient extends JFrame implements Runnable,ActionListener,MouseListener{
 int MAXSIZE=1000;
	SurFace Face=null;
	Socket MySocket=null,Rec=null,UpSocket=null;
	DataOutputStream OutputStream;
	DataInputStream InputStream;
	int key=0;
	DataInputStream input;
	DataOutputStream output;
	LogIn Log;
	Thread thread;
	String[] filename;
    String SaveFileName=null;
	File UpFile=null;
    String Password=null;
    public FtpClient() throws UnknownHostException{
	Log=new LogIn();
	Log.comeIn.addActionListener(this);

	}
	 public void connect(LogIn w) {
	  try{ 	
	  	MySocket=new Socket(w.getIP(),1025);
	  	InputStream=new DataInputStream(MySocket.getInputStream());
	    OutputStream=new DataOutputStream(MySocket.getOutputStream());
	    //input=new DataInputStream(Rec.getInputStream());
	    w.setLabel("服务器连接成功");
	    if(Face==null)
	    	Face=new SurFace();
	    Password=JOptionPane.showInputDialog(this,"输入服务器密码 !\n匿名密码：111");
       if(Password==null){
       	System.exit(0);
       }
	    OutputStream.writeUTF(Password);
	    OutputStream.flush();
	    OutputStream.writeUTF("文件");
	    OutputStream.flush();
	    Face.addWindowListener(new WindowAdapter(){
	    	 public void windowClosing(WindowEvent e){
	    	    try{
	    	    	MySocket.close();
	    	        System.exit(0);
	    	    }catch(IOException ee){
	    	    	
	    	    }
	    	 }
	    });
	    Face.exitButton.addActionListener(this);
	 	Face.close.addActionListener(this);
	 	 Face.contain1.addMouseListener(this);
         Face.filelabel.addMouseListener(this); 
         Face.label2.addMouseListener(this);
	 	 Face.Pop_Open.addActionListener(this);
	 	 Face.Pop_Del.addActionListener(this);
	 	 Face.Pop_Down.addActionListener(this);
	 	 Face.Pop_Up.addActionListener(this);
         Face.Pop_Up_D.addActionListener(this);
       //  Face.label1.addMouseListener(this);
	 	 Log.dispose();
	  }catch(IOException e){
	  	w.setLabel("无连接");
	  }
	  if(thread==null){
	  	  thread=new Thread(this);
	     thread.start();
	  }
	 }
	 public void run(){
       String msg;
	 	String name=null; 
	 	String check="."; 
		int type=0;
	 	while(thread!=null){	 		
       	 try{
       	 	msg=InputStream.readUTF();
       	 if(msg.startsWith("匿名")){
       	 	Face.setPop();//匿名用户不能上传不能删除
       	  JOptionPane.showMessageDialog(null,"你将使用匿名服务");	
       	 }
       	 else if(msg.startsWith("拒绝")){
           	JOptionPane.showMessageDialog(this,"密码错误\n服务器访问拒绝");
           	Face.close.setText("连接服务器");  
           	break;
           }
        if(msg.startsWith("文件名"))
           {  
        name=msg.substring(5);
           type=Integer.parseInt(msg.substring(4,5));
           Face.addfile(name,type);
           }
        else if(msg.startsWith("内容")){
        	long filelong=Integer.parseInt(msg.substring(2));
      if(Rec.isConnected()){
           	DownThread a=new DownThread(Rec,filelong);
           	a.start();
           	}
          	else JOptionPane.showMessageDialog(null,"和服务器没有建立连接");
           	}
       	 }catch(IOException e){
       	 	JOptionPane.showMessageDialog(null,"服务器连接已被终止");
       	 	Face.contain1.removeAll();
       	 	Face.contain1.updateUI();
       	 	Face.close.setText("连接服务器");
       	 	break;
       	 }
       }
	 }
	 public void actionPerformed(ActionEvent e){
	 	if(e.getSource()==Log.comeIn||e.getSource()==Log.Port||e.getSource()==Log.intel)
	 		 this.connect(Log);
	 	else if(e.getActionCommand()=="断开服务器")
	    	   this.close();
	 	else if(e.getSource()==Face.exitButton)
	 		  this.exit();
	 	else if(e.getSource()==Face.Pop_Open){
	 		if(Face.type.equals("文件夹")){
			 	try{
			 		key++;
			        Face.contain1.removeAll();
			 		Face.contain1.updateUI();
			 		OutputStream.writeUTF("文件夹"+Face.GetFileName());
                    OutputStream.flush();
			 		Face.setPop_Up_D_T();
			 		Face.setLabel1_T();
			 	    Face.SetSelectFile("","","");
			 	    Face.label1.addMouseListener(this);
			 	}catch(IOException ee){
			 	System.out.println(ee);
			 }
	 	}
	 	}
	 		else if(e.getSource()==Face.Pop_Down){
	 			if(!Face.type.equals("文件夹")){
	 			 	try{
		               Rec=new Socket(Log.getIP(),1026);
		               if(Rec.isConnected()){
		               OutputStream.writeUTF("下载"+Face.GetFileName());
				 		OutputStream.flush();}
		               
	 			 	}catch(IOException ee){
	 			 		JOptionPane.showMessageDialog(null,"连接服务器失败");
				 }
	 			}
	 		}
	 		else if(e.getActionCommand()=="连接服务器"){
	 			Face.dispose();   
	 			try {
					FtpClient newclient=new FtpClient();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	 			      
	 		}
	 	else if(e.getSource()==Face.Pop_Up_D){
	 		try{	        
	 			key--;
           if(key<=0){
					Face.setPop_Up_D_F();
				    Face.setLabel1_F();
				}
			    Face.contain1.removeAll();  
			    Face.contain1.updateUI();
				OutputStream.writeUTF("向上");
			    OutputStream.flush();
                Face.SetSelectFile("","","");
			    Face.label1.removeMouseListener(this);
	 		}catch(IOException h){
				System.out.println(h);
			}
	 	}
	 	else if(e.getSource()==Face.Pop_Up){   //文件上传
	 	   String upfile=this.SelectUpFile();
	 		try{
	 			UpSocket=new Socket(Log.getIP(),1025);  	
	 	 if(UpFile!=null&&UpSocket.isConnected()){
	 	 	OutputStream.writeUTF("上传"+upfile);  
	 	 	UpToServer myup=new UpToServer(UpSocket,UpFile);
	 	     myup.start(); 
	 	    
	 		}
	 		 }catch(IOException ee){
	            JOptionPane.showMessageDialog(null,"UP ERROR");
	 	 	   }
	 		}
	 }
	 public String SelectUpFile(){
	    JFileChooser UpSelect=new JFileChooser();
	    UpSelect.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    int result=UpSelect.showOpenDialog(UpSelect);
	    UpFile=UpSelect.getSelectedFile();
	    String filename=UpFile.getName();
	   	if(UpFile==null||UpFile.equals(""))
	   	   JOptionPane.showMessageDialog(null,"不合法文件");
	   return filename;
	}
	 
	 public String SelectSaveFile(){
	   	JFileChooser filechooser=new JFileChooser();
	   	filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	   	int result=filechooser.showSaveDialog(filechooser);
	   if(result==JFileChooser.CANCEL_OPTION)
	       return null;	  
	   File fileName=filechooser.getSelectedFile();
	      if(fileName==null||fileName.getName().equals(""))
	      	     JOptionPane.showMessageDialog(null,"不合法文件名","错误提示",JOptionPane.ERROR_MESSAGE);
	   return fileName.toString();
	 }
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getSource()==Face.label1){
		try{	        
			key--;
			Face.label1.setEnabled(false);
			Face.label1.removeMouseListener(this);
		    OutputStream.writeUTF("向上");
		    Face.contain1.removeAll(); 
		    OutputStream.flush();
		    Face.Pop_Up_D.setEnabled(false);
		 //   JOptionPane.showMessageDialog(null,"UP");
		    Face.SetSelectFile("","","");
			
			}catch(IOException e){
			System.out.println(e);
		}
		}
		else if(arg0.getSource()==Face.label2){
			if(!Face.type.equals("文件夹")){
			 	try{
		               Rec=new Socket(Log.getIP(),1026);
		               OutputStream.writeUTF("下载"+Face.GetFileName());
				 		OutputStream.flush();
		              }catch(IOException ee){
				 	JOptionPane.showMessageDialog(null,"连接服务器失败");
				 }
		}
		}
		}
		
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
  
	public void exit(){
	try{
		if(MySocket!=null){
			MySocket.close();
		}
	 System.exit(0);
	}catch(IOException e){
		System.out.println(e);
	}
}
	public void close(){
      if(MySocket!=null){
		try{
      	MySocket.close();
      	Face.close.setText("连接服务器");
      	Face.close.setIcon(new ImageIcon("image/reconnect.png"));
      	Face.contain1.removeAll();
      	Face.contain1.validate();
      	Face.validate();
      }catch(IOException e){
      	System.out.println(e);
      }
      }
      }
	
	public static void main(String args[]){
		try {
			FtpClient ftp=new FtpClient();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 }
}

class getfile extends Thread{
  public getfile(Socket socket,String IP,int Port){
  	
  }
	public void run(){
	 	
	 }
}