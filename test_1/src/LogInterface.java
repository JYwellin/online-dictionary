

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogInterface extends JFrame{
	JFrame frame = new JFrame("Login");
	
	JButton jbtlog = new JButton("Login");
	JButton jbtreg = new JButton("Regist"); 
	JButton jbtwordcard = new JButton("Send Cards");
	JTextField username = new JTextField(30);
	JTextField userpass = new JTextField(30);
	JLabel text_login = new JLabel("login");
	JLabel text_reg = new JLabel("regist");
	LogInterface()
	{

		frame.setSize(500, 300);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		text_login.setBounds(65, 30, 80, 40);
		text_reg.setBounds(60, 100, 80, 40);
		username.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
		text_login.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
		text_reg.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
		username.setBounds(130,30,280,40);
		
		userpass.setBounds(130,100,280,40);
		userpass.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
		
		jbtlog.setBounds(80,180,80,40);
		jbtreg.setBounds(200,180,80,40);
		jbtwordcard.setBounds(320,180,120,40);
		
		frame.add(text_login);
		frame.add(text_reg);
		frame.add(jbtwordcard);
		frame.add(jbtlog);
		frame.add(jbtreg);
		frame.add(username);
		frame.add(userpass);
		frame.setVisible(true);
	}
}
