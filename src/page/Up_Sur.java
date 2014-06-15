package page;
import javax.swing.*;
import java.awt.*;


public class Up_Sur extends JFrame{
	JLabel label,label1;  
	public Up_Sur(){
		Container container=getContentPane();
		label=new JLabel();
	  	label1=new JLabel();
	  	label1.setText("上传已完成%");
		label.setIcon(new ImageIcon("image/up.png"));
	  	label.setText("一文件正在上传");
	  	container.add(label,BorderLayout.NORTH);
	  	container.add(label1,BorderLayout.SOUTH);
	  	this.setVisible(true);
	  	this.setSize(170,120);
	  	this.validate();  
	}
	public void Change(){
		label.setIcon(new ImageIcon("image/end.png"));
		label.setText("上传已完成");
		this.validate();
	}
	public void setP(int a){
		label1.setText("上传已完成%"+a);
	}
     public static void main(String args[]){
     	Up_Sur a=new Up_Sur();
     	 a.Change();
     	 Up_Sur t=new Up_Sur();
     	 t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     	 a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     }
}