
package page;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.InetAddress;
import java.awt.event.*;
import javax.swing.*;




public class LogIn extends JFrame implements ActionListener{
	  JTextField intel,Port;
	   JLabel    label,label1;
	   JButton   comeIn; 
	   FlowLayout layout;
	   int PortNumber=1025;//默认端口为21
	   public LogIn(){
	     super("FTP客户端");	
	   	Container container=getContentPane();
	    	layout=new FlowLayout();
	    	label=new JLabel("输入主机地址");
	    //	label.setIcon(new ImageIcon("image/IP.png"));
	        label1=new JLabel("连接端口："); 
	    //	label1.setIcon(new ImageIcon("image/port.png"));
	        Port=new JTextField("1025");
	        comeIn=new JButton("  连接  ");
	     //   comeIn.setIcon(new ImageIcon("image/reconnect.png"));
	        comeIn.addActionListener(this); 
	        container.setLayout(layout);
	         container.add(label);
	         intel=new JTextField("192.168.199.244");
	         intel.addActionListener(this);
             container.add(intel);
	         container.add(label1);
	         container.add(Port);
	         container.add(comeIn);
	         setSize(500,400);
	         setVisible(true);
	         this.validate();
	   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     

	   }	
 
/* (non-Javadoc)
 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
 */
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if(e.getSource()==intel||e.getSource()==comeIn||e.getSource()==Port){
		 if(!this.checkIP(intel.getText())){
    		 	JOptionPane.showMessageDialog(null,"你输入了不合法的主机地址\n请重输入","错误信息",JOptionPane.ERROR_MESSAGE);
    		 	intel.setText("");
		 }
    		 	if(!Port.getText().equals(""))
	   	 	     PortNumber=Integer.parseInt(Port.getText().toString());
	}
	}
public boolean checkIP(String ip){
    if(ip.length()>=16||ip.length()<8)
    	 return false;
    else 
    	 return true;
}
public String getIP(){
 return intel.getText().toString();
}
public int getPort(){
	return PortNumber;
}
public void setLabel(String s){
	label.setText(s);
}

  	
}