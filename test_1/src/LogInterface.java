

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogInterface extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public LogInterface() {
		setLayout (new BorderLayout(10, 10));
		JPanel jpShowPane = constructShowPane();
		jpShowPane.setPreferredSize(new Dimension(300, 500));
		add(jpShowPane, BorderLayout.WEST);
		
		JPanel jpLogPane = constructLogPane();
		jpLogPane.setPreferredSize(new Dimension(100, 500));
		add(jpLogPane, BorderLayout.CENTER);
		
		
	}
	
	private JPanel constructLogPane() {
		JPanel jpLogPane = new JPanel();
		jpLogPane.setLayout(new BorderLayout(10, 10));
		
		JPanel jpUserPane = new JPanel();
		jpUserPane.setPreferredSize(new Dimension(110, 60));
		JLabel jlbUser = new JLabel("鐢ㄦ埛鍚�:");
		jlbUser.setFont(new java.awt.Font("瀹嬩綋", 1, 15));
		jlbUser.setPreferredSize(new Dimension(110,30));
		jpUserPane.add(jlbUser);
		JTextField jtfUser = new JTextField(10);
		jtfUser.setPreferredSize(new Dimension(110,20));
		jpUserPane.add(jtfUser);
		
		JPanel jpPasswordPane = new JPanel();
		jpPasswordPane.setPreferredSize(new Dimension(110, 60));
		JLabel jlbPassword = new JLabel("瀵嗙爜:");
		jlbPassword.setFont(new java.awt.Font("瀹嬩綋", 1, 15));
		jlbPassword.setPreferredSize(new Dimension(110, 30));
		jpPasswordPane.add(jlbPassword);
		JPasswordField jpfPassword = new JPasswordField(10);
	//	JTextField jtfPassword = new JTextField(10);
		jpPasswordPane.add(jpfPassword);
		
		JPanel jpSignPane = new JPanel();
		jpSignPane.setPreferredSize(new Dimension(110, 100));
		JButton jpLog = new JButton("鐧诲綍");
		jpSignPane.add(jpLog);
		JButton jpSignUp = new JButton("娉ㄥ唽");
		jpSignPane.add(jpSignUp);
		
		jpLogPane.add(jpUserPane, BorderLayout.NORTH);
		jpLogPane.add(jpPasswordPane, BorderLayout.CENTER);
		jpLogPane.add(jpSignPane, BorderLayout.SOUTH);
		
		return jpLogPane;
		
	}
	
	private JPanel constructShowPane() {
		JPanel jpShowPane = new JPanel();
		jpShowPane.setLayout(new BorderLayout(0, 10));
		
		JPanel jpTitlePane = new JPanel();
		
		JLabel jlbTitle = new JLabel("鍦ㄧ嚎璇嶅吀");
		jlbTitle.setFont(new java.awt.Font("妤蜂綋", 1, 40));
		jpTitlePane.add(jlbTitle);
				
		ImageIcon img = new ImageIcon("瀛楀吀.png");
		img.setImage((img.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT)));
		JLabel lblImage = new JLabel(img);
	//	lblImage.setBounds(0, 0, 20, 20);
		jpTitlePane.add(lblImage);
		
		JPanel jpTextPane = new JPanel();
	//	jpTextPane.setLayout(new BorderLayout(5, 5));
		JLabel jlbText0 = new JLabel("璇存槑锛�");
		jlbText0.setFont(new java.awt.Font("瀹嬩綋", 0, 18));
		JLabel jlbText1 = new JLabel("鏈瘝鍏告敮鎸佺櫨搴︺�佹湁閬撱�佸繀搴旀煡璇�");
		jlbText1.setFont(new java.awt.Font("瀹嬩綋", 0, 18));
		JLabel jlbText2 = new JLabel("鍦ㄥ彸渚х櫥褰曪紝鏂扮敤鎴疯鍏堟敞鍐�");
		jlbText2.setFont(new java.awt.Font("瀹嬩綋", 0, 18));
		jpTextPane.add(jlbText0, BorderLayout.NORTH);
		jpTextPane.add(jlbText1, BorderLayout.CENTER);
		jpTextPane.add(jlbText2, BorderLayout.SOUTH);
		
		JPanel jpTourPane = new JPanel();
		JLabel jlbText3 = new JLabel("杩涘叆璁垮妯″紡");
		jlbText3.setFont(new java.awt.Font("瀹嬩綋", 0, 18));
		JButton jbtTourOk = new JButton("Go");
		jbtTourOk.setFont(new java.awt.Font("瀹嬩綋", 0, 10));
		jpTourPane.add(jlbText3);
		jpTourPane.add(jbtTourOk);
		
		jpShowPane.add(jpTitlePane, BorderLayout.NORTH);
		jpShowPane.add(jpTextPane, BorderLayout.CENTER);
		jpShowPane.add(jpTourPane, BorderLayout.SOUTH);
		return jpShowPane;
	}
	
	public static void main(String args[]) {
		LogInterface frame = new LogInterface();
		frame.setTitle("Online Dictionary");
		frame.setSize(500, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
