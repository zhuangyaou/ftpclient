
package page;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;



public class SurFace extends JFrame implements MouseListener,ActionListener{
	   JLabel    label;
	   JButton   close; 
	   JButton   exitButton;
	   JButton   upButton;
	   JPanel    contain;
	   JPanel    contain1,FileDescription,contain2,contain3;
	   JLabel    discription,ConnectStatus; 
	   JTextArea PlayArea;
	   JTextField displayFiled,displayFiled1,displayFiled2;
	   Icon Image,FileImage,UpImage,DownImage;
	   JLabel label1,label2,filelabel,Commend_D,label3;
	   JTextField File_D;
	   JPopupMenu Pop,Pop1;
	   JMenuItem  Pop_Down,Pop_Del,Pop_Ref,Pop_Up,Pop_Open,Pop_Up_D;
	   String  name;     //返回当前用户选择文件名
	   FlowLayout layout;
	   GridLayout layout1;
	   Font font;
	   int  filetype=0;
	   String type="";
	   Box  box;
	   public SurFace(){
	     super("FTP客户端");
	     Container container=getContentPane();
	     layout=new FlowLayout();
	     layout.setAlignment(FlowLayout.LEFT);
	     layout.setHgap(30);
	     layout.setVgap(30);
	     layout1=new GridLayout(8,6,3,3); 
	   	 ConnectStatus=new JLabel();
	   	 ConnectStatus.setFont(new Font(null,Font.PLAIN+Font.BOLD,15));
	   	 ConnectStatus.setIcon(new ImageIcon("image/connect.gif"));
	   	 ConnectStatus.setText("服务器已连接");
	   	 discription=new JLabel();
	     discription.setFont(new Font(null,Font.PLAIN+Font.BOLD,20));
	     discription.setIcon(new ImageIcon("image/server.png"));
	     discription.setText("服务器文件列表");

	   	 Commend_D=new JLabel();
	     Commend_D.setFont(new Font(null,Font.PLAIN+Font.BOLD,20));
	     Commend_D.setIcon(new ImageIcon("image/commend.png"));
	     Commend_D.setText(" 操作 ");
	     final String id=Commend_D.getText();
	     FileDescription=new JPanel();
	   	 displayFiled=new JTextField(40); 
	   	 label=new JLabel(" 小熊制作 QQ：282592203");
	     Image=new ImageIcon("image/file.gif");    
	     FileImage=new ImageIcon("image/file1.gif");
	     UpImage=new ImageIcon("image/up.png");
	     DownImage=new ImageIcon("image/down.gif");
	     close=new JButton("断开");
	    // close.setIcon(new ImageIcon("image/dis.png"));
	         exitButton=new JButton("退出");
	     //    exitButton.setIcon(new ImageIcon("image/exit.png"));
	         contain=new JPanel();
	         contain1=new JPanel();
	         contain3=new JPanel();
	        contain3.setPreferredSize(new Dimension(100,100));
	
	         contain3.setBackground(Color.white);
	      //   upButton=new JButton("首目录");
	         filelabel=new JLabel();
	        
	         Font font = new Font("下载",Font.PLAIN,16);
	         label1=new JLabel();
	         label1.setEnabled(false);
	        // label1.setText("首目录");
	         label1.setIcon(new ImageIcon("image/main.png"));
	         label1.setBackground(Color.WHITE);
	         	         label2=new JLabel("下载");
	         	         
	         label2.setFont(new Font(null,Font.PLAIN+Font.BOLD,20));

	         label2.setIcon(new ImageIcon("image/down.gif"));
	         label2.addMouseListener(this);
	         label1.setBackground(Color.WHITE);
	         label2.setEnabled(false);
	         
	         contain3.setLayout(new GridLayout(2,1));
             contain3.add(Commend_D);
	   
             contain3.add(label2);
	         
             container.setLayout(new BorderLayout());
	         container.add(contain,BorderLayout.SOUTH);
	         container.add(contain3,BorderLayout.EAST);
	         
	         contain2=new JPanel();
	         contain2.setLayout(new GridLayout(1,2,1,1));
	         container.add(contain2,BorderLayout.NORTH);
	       //  contain2.add(ConnectStatus);
	         label3=new JLabel();
	         label3.setIcon(new ImageIcon("image/tip.png"));
	         contain2.add(label3);
	         contain2.add(discription);
	         
	         container.add(FileDescription,BorderLayout.WEST);
	         FileDescription.setLayout(new GridLayout(4,1,0,0));
             displayFiled=new JTextField(5);
		     displayFiled.setBackground(Color.lightGray);
             displayFiled.setEditable(false);
		     displayFiled1=new JTextField(5);
		     displayFiled1.setEditable(false);
		     displayFiled1.setBackground(Color.lightGray);
             displayFiled2 = new JTextField(5);
             displayFiled2.setEditable(false);
             displayFiled2.setBackground(Color.lightGray);
             File_D=new JTextField("文件信息描述");
		     File_D.setEditable(false);
		     File_D.setBackground(Color.lightGray);
             File_D.setFont(new Font(null,Font.PLAIN+Font.BOLD,15));
		     FileDescription.add(File_D);
	         FileDescription.add(displayFiled);
	         FileDescription.add(displayFiled1);
	         FileDescription.add(displayFiled2);
	         //contain.add(label);
	         contain.add(exitButton);
	        // contain.add(close);
	   
	    
	        
	         container.add(new JScrollPane(contain1),BorderLayout.CENTER);
	        // container.add(contain1,BorderLayout.CENTER);
	         contain1.setBackground(Color.WHITE);
	         Pop=new JPopupMenu(id);
     	     Pop_Down=new JMenuItem("下载");
     	     Pop_Del=new JMenuItem("删除");
     	     Pop_Ref=new JMenuItem("刷新");
     	     Pop_Open=new JMenuItem("打开文件夹");
     	     Pop_Up_D=new JMenuItem("上层");
     	     Pop.add(Pop_Open);
     	     Pop.add(Pop_Up_D);
     	     Pop.add(Pop_Down);
     	     Pop.add(Pop_Del);
     	     Pop.add(Pop_Ref);
     	     Pop1=new JPopupMenu();
     	     Pop_Up=new JMenuItem("上传");
     	     Pop.add(Pop_Up);
             Pop_Ref.addActionListener(this);
	         Pop.addMouseListener(this);
	         contain1.addMouseListener(this);
	         filelabel.addMouseListener(this);
	         Commend_D.setComponentPopupMenu(Pop);
	         
	         this.setPop_Up_D_F(); 
	         this.setSize(800,600);
	          setVisible(true);
	         this.validate();
	         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
	 public void setPop(){
	   	this.Pop_Up.setEnabled(true);
	   	this.Pop_Del.setEnabled(true);
	   }
	   public void setPop_Up_D_F(){
	   	this.Pop_Up_D.setEnabled(false);
	   }
	   public void setPop_Up_D_T(){
	   	this.Pop_Up_D.setEnabled(true);
	   }
	   public void setLabel1_T(){
	   	this.label1.setEnabled(true);
	   }
	   public void setLabel1_F(){
	   	this.label1.setEnabled(false);
	   }
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand()=="刷新"){
			    contain1.validate();
			    contain1.updateUI();
		}
		}
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	 if(arg0.getSource()!=label1&&arg0.getSource()!=contain1&&arg0.getSource()!=label2){
      name=((JLabel)arg0.getSource()).getText();
    String t=((JLabel)arg0.getSource()).getToolTipText();      
       type=t;      
	    if(type.equals("文件夹")){
	    	this.label2.setEnabled(false);
	    }
	    else {
	    	this.label2.setEnabled(true);
	    }
       this.SetSelectFile(name,"unknow",type);	 
		  	    
	 }
	 else if(arg0.getSource()==label1){ 
	 }
	 else if(arg0.getSource()==Pop_Ref){
	 	  contain1.updateUI(); 
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
	  if(arg0.isPopupTrigger()){
	  	this.Pop.show((Component)arg0.getSource(),arg0.getX(),arg0.getY());      
	  	if(arg0.getSource()!=contain1&&arg0.getSource()!=label1){
	  	  String t=((JLabel)arg0.getSource()).getToolTipText();  
	  		type=t;
	  		  if(type.equals("文件夹")){
	 		  	 // Pop_Del.setEnabled(true);
	 		  		Pop_Open.setEnabled(true);
	  		        Pop_Down.setEnabled(false);
	  		        label2.setEnabled(false);
	  		  }
	  		  else {
	  		  Pop_Down.setEnabled(true);
	  		  Pop_Open.setEnabled(false);
	  		  label2.setEnabled(true);
	  		  }
	  	  name=((JLabel)arg0.getSource()).getText();
	  		 this.SetSelectFile(name,"unknow",type);
	  	      }
	  	else {Pop_Down.setEnabled(false);      
	  	      Pop_Open.setEnabled(false);
	  	}
	  	}
	
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
	public void addfile(String filename,int type){//0表示文件夹，1表示文件
	   filelabel=new JLabel();
	   if(type==0){
	   	filelabel.setIcon(Image);
	    filelabel.setText(filename);
	    filelabel.setToolTipText("文件夹");
	   }
	   else if(type==1){
	   	 filelabel.setIcon(FileImage);
	   	 filelabel.setText(filename);
	     filelabel.setToolTipText("文件");
	   }
	   else   filelabel.setText("无法识别文件"+filename);
	   contain1.add(filelabel);
	   filelabel.addMouseListener(this);
       contain1.setLayout(layout1);
 	   layout1.layoutContainer(contain1);
	   contain1.validate();
 	   contain1.updateUI();
	   contain1.setVisible(true);
	}
public String GetFileName(){
	return name;
}

public void SetSelectFile(String info,String info1,String info2){
	displayFiled.setText("文件名:"+info);
	displayFiled1.setText("大小:"+info1);
	displayFiled2.setText("类型:"+info2);
}
public static void main(String args[]){
	  	SurFace Face=new SurFace();
	  	Face.addfile("wori",0);
	  	Face.addfile("haha",1);
	  	Face.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	}
}
