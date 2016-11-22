import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;




import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

public class FrontPage extends JFrame{
	private Font font=new Font("Microsoft YaHei UI",0,20);
	
	public JPanel searchBox=setSearchBox();
	
	public JPanel checkBox=setCheckBox();
	
	public JPanel showPanel=new JPanel();
	
	public JPanel jpYouDao=ShowYouDao();
	
	public JPanel jpBaidu=showBaiDu();
	
	public JPanel jpBing=showBing();
	
	public JTextField userInput=new JTextField(50);
	
	public JButton jbtSearch=new JButton("Search");
	
	
	public FrontPage() throws IOException
	{
		setSize(840,700);
		EmptyBorder jbup=new EmptyBorder(20,20,0,20);
		EmptyBorder jbmiddle=new EmptyBorder(0,100,0,0);
		EmptyBorder jbDown=new EmptyBorder(0,20,40,20);
		setLayout(new BorderLayout(5,10));
		
		
		searchBox.setPreferredSize(new Dimension(840,170));
		searchBox.setBorder(jbup);
		
		userInput.setFont(font);
		userInput.setPreferredSize(new Dimension(600,15));
		//userInput.addKeyListener(new Message_fixup());
		searchBox.add(userInput,BorderLayout.CENTER);
		add(searchBox,BorderLayout.NORTH);
		
		
		jbtSearch.setFont(font);
		jbtSearch.setPreferredSize(new Dimension(100,15));
		jbtSearch.addActionListener(new jbtSearchListener());
		searchBox.add(jbtSearch,BorderLayout.EAST);
		
		
		checkBox.setPreferredSize(new Dimension(840,50));
		checkBox.setBorder(jbmiddle);
		add(checkBox,BorderLayout.CENTER);
		
		
		showPanel.setLayout(new BorderLayout(5,10));
		showPanel.setPreferredSize(new Dimension(840,360));
		showPanel.setBorder(jbDown);
		
		
		jpYouDao.setPreferredSize(new Dimension(800,100));
		showPanel.add(jpYouDao,BorderLayout.NORTH);
		
		
		jpBaidu.setPreferredSize(new Dimension(800,100));
		showPanel.add(jpBaidu, BorderLayout.CENTER);
		
		
		jpBing.setPreferredSize(new Dimension(800,100));
		showPanel.add(jpBing, BorderLayout.SOUTH);
		
		add(showPanel,BorderLayout.SOUTH);
		
	}
	
	class Message_fixup extends KeyAdapter //监听键盘输入
	{
		public void keyReleased(KeyEvent e)
		{
			String input = userInput.getText();
			int count = showPanel.getComponentCount();
			for(int i = 0;i < count;i++)
			{
				Component comp = showPanel.getComponent(i);
				int cou = ((JPanel) comp).getComponentCount();
				for(int j = 0;j < cou;j++)
				{
					Component temp =((JPanel) comp).getComponent(j);
					if(temp instanceof JTextArea)
				{
						Search p = new Search();
						String res;
						try {
							res = p.Find_You_Dao(input);
							((JTextArea) temp).setText(res);
						} catch (IOException e1) {
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
						}
					
				}
				}
			}
		}
	}
	
	class jbtSearchListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String input = userInput.getText();
			Component comp_0 = showPanel.getComponent(0);
			Component temp_0 =((JPanel) comp_0).getComponent(0);
			Search p = new Search();
			try {
				String res = p.Find_You_Dao(input);
				((JTextArea) temp_0).setText(res);
				if(res.length() == 0)
					((JTextArea) temp_0).setText("未找到释义");
			} catch (IOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
	}
	
	private JPanel setSearchBox()
	{
		
		JPanel searchBox=new JPanel();
		searchBox.setLayout(new BorderLayout(5,10));
		
		JLabel headLine=new JLabel("                   Online Dictionary");
		headLine.setFont(new Font("Microsoft YaHei UI",1,40));	
		headLine.setPreferredSize(new Dimension(800,90));
		//headLine.setBounds(0,0,400,200);
		searchBox.add(headLine,BorderLayout.NORTH);
		
		JLabel inputNotice=new JLabel(" Input");
		inputNotice.setFont(font);
		inputNotice.setPreferredSize(new Dimension(80,15));
		//headLine.setBounds(0,210,50,30);
		searchBox.add(inputNotice,BorderLayout.WEST);

		
		return searchBox;
	}

	private JPanel setCheckBox()
	{
		JPanel checkBox=new JPanel();
		checkBox.setLayout(new BorderLayout(5,10));
		
		JCheckBox jcYouDao=new JCheckBox("有道");
		//jcYouDao.setSize(30, 30);
		jcYouDao.setFont(font);
		jcYouDao.setPreferredSize(new Dimension(250,10));
		checkBox.add(jcYouDao, BorderLayout.WEST);
		
		JCheckBox jcBaiDu=new JCheckBox("百度");
		//jcBaiDu.setSize(30, 30);
		jcBaiDu.setFont(font);
		jcBaiDu.setPreferredSize(new Dimension(250,10));
		checkBox.add(jcBaiDu,BorderLayout.CENTER);
		
		JCheckBox jcBing=new JCheckBox("Bing");
		//jcBing.setSize(30, 30);
		jcBing.setFont(font);
		jcBing.setPreferredSize(new Dimension(250,10));
		checkBox.add(jcBing,BorderLayout.EAST);
		
		return checkBox;
	}

	private JPanel ShowYouDao()
	{
		JPanel jpYouDao=new JPanel();

		jpYouDao.setLayout(new BorderLayout(5,10));
		
		JTextArea jtYouDao=new JTextArea();
		jtYouDao.setFont(font);

		jpYouDao.setPreferredSize(new Dimension(600,100));
		jpYouDao.add(jtYouDao,BorderLayout.CENTER);
		
		TitledBorder jlbYouDao=new TitledBorder("有道");
		jlbYouDao.setTitleFont(font);

		jpYouDao.setBorder(jlbYouDao);
		
		JButton jbYouDao=new JButton("点赞");
		jbYouDao.setFont(font);

		jbYouDao.setPreferredSize(new Dimension(100,30));
		jpYouDao.add(jbYouDao,BorderLayout.EAST);
		
		return jpYouDao;	
	}
	private JPanel showBaiDu()
	{
		JPanel jpBaiDu=new JPanel();

		jpBaiDu.setLayout(new BorderLayout(5,10));
		
		JTextArea jtBaiDu=new JTextArea();
		jtBaiDu.setFont(font);

		jtBaiDu.setPreferredSize(new Dimension(600,100));
		jpBaiDu.add(jtBaiDu,BorderLayout.CENTER);
		
		TitledBorder jlbBaiDu=new TitledBorder("百度");
		jlbBaiDu.setTitleFont(font);

		jpBaiDu.setBorder(jlbBaiDu);
		
		JButton jbBaiDu=new JButton("点赞");
		jbBaiDu.setFont(font);

		jbBaiDu.setPreferredSize(new Dimension(100,30));
		jpBaiDu.add(jbBaiDu,BorderLayout.EAST);
		
		return jpBaiDu;	
	}
	
	private JPanel showBing()
	{
		JPanel jpBing=new JPanel();
		jpBing.setLayout(new BorderLayout(5,10));
		
		JTextArea jtBing=new JTextArea();
		jtBing.setFont(font);

		jtBing.setPreferredSize(new Dimension(600,100));
		jpBing.add(jtBing,BorderLayout.CENTER);
		
		TitledBorder jlbBing=new TitledBorder("Bing");
		jlbBing.setTitleFont(font);

		jpBing.setBorder(jlbBing);
		
		JButton jbBing=new JButton("点赞");
		jbBing.setFont(font);

		jbBing.setPreferredSize(new Dimension(100,30));
		jpBing.add(jbBing,BorderLayout.EAST);
		
		return jpBing;	
	}
}
