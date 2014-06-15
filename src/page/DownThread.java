package page;

import java.io.*;
import java.net.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;



public class DownThread extends Thread{
	static int MAXSIZE=4096;
	String file;
	File SaveFile=null;
	String address;
	Socket socket;
	DataInputStream input;
	DataOutputStream output;
	DownSurFace show;
	long filelong;
	public DownThread(Socket in,long l){
	  	filelong=l/1024;
	  socket=in;
	}
public void run(){
	int len;
	long currentlen=0;
    double per=0;
	try{
		file=this.SelectSaveFile();
		if(file!=null){
		SaveFile=new File(file);
		output=new DataOutputStream(new FileOutputStream(new File(file)));
		input= new DataInputStream(socket.getInputStream());
		byte[] buf=new byte[MAXSIZE];
	    show=new DownSurFace(); 
		while(true){
	if((len=input.read(buf))!=-1){
		output.write(buf,0,len);		
	    currentlen+=len/1024;
	    System.out.println(socket.getReceiveBufferSize());
	    per=(double)currentlen/(double)filelong;
	    show.setP((int)(per*100));
	}
	   else {
     	   show.label1.setText("下载已完成%100");
	   	    show.Change();
     	   	output.close();
		    input.close();
		    socket.close();
	   	    break;	
	   }
	}
	}
	}catch(IOException e){
    System.out.println("DownThread:"+e);
	}
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
}


