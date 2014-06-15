package page;

import java.net.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
public class UpToServer extends Thread{
	JFileChooser UpFile;
	File file;
	Up_Sur face;
	Socket mysocket=null;
	DataInputStream input;
	DataOutputStream output;
	public UpToServer(Socket IP,File filename){
    file=filename;
		mysocket=IP;
	}

public void run(){
	int len;
  long all=0;
 float per=0;
  long current=0;
	try{
	    face=new Up_Sur();
	    input=new DataInputStream(new FileInputStream(file));
	    output=new DataOutputStream(mysocket.getOutputStream());
  	  }catch(IOException e){
		JOptionPane.showMessageDialog(null,"上传不能连接服务器");
	}
    byte[]  buf=new byte[2048];
     all=file.length();
    try{
    while((len=input.read(buf))!=-1){
    	output.write(buf,0,len);
    	output.flush();
      current+=len;
      per=(float)current/(float)all;
      face.setP((int)(per*100));
    }
    face.setP(100);
    input.close();
    output.close();
    face.Change();
  }catch(IOException e){
      JOptionPane.showMessageDialog(null,"上传错误\n可能是服务器关闭或网络中断");	
  }
  }
}