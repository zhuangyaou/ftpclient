package page;

import javax.swing.*;
import java.awt.*;


public class DownSurFace extends JFrame{
	JLabel label,label1;  
	public DownSurFace(){
		Container container=getContentPane();
		label=new JLabel();
	  	label1=new JLabel();
	  	label1.setText("���������%");
		label.setIcon(new ImageIcon("image/download.png"));
	  	label.setText("һ�ļ���������");
	  	container.add(label,BorderLayout.NORTH);
	  	container.add(label1,BorderLayout.SOUTH);
	  	this.setVisible(true);
	  	this.setSize(170,100);
	    this.validate();  
	}
	public void Change(){
		label.setIcon(new ImageIcon("image/end.png"));
		label.setText("���������");
		this.validate();
	}
	public void setP(double a){
		label1.setText("���������%"+a);
	}
     public static void main(String args[]){
     	 DownSurFace a=new DownSurFace();
     	 a.Change();
     	 DownSurFace t=new DownSurFace();
     	 t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     	 a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         t.setP(1.2);
     }
}