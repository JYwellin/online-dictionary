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
	
	public JButton jbtlogin = new JButton("Login");
	
	public boolean[] flag = new boolean[3];
	
	
	public FrontPage() throws IOException
	{
		flag[0] = flag[1] = flag[2] = false;
		
		setSize(840,700);
		EmptyBorder jbup=new EmptyBorder(20,20,0,20);
		EmptyBorder jbmiddle=new EmptyBorder(0,100,0,0);
		EmptyBorder jbDown=new EmptyBorder(0,20,40,20);
		setLayout(new BorderLayout(5,10));
		
		
		searchBox.setPreferredSize(new Dimension(840,170));
		searchBox.setBorder(jbup);
		
		userInput.setFont(font);
		userInput.setPreferredSize(new Dimension(600,15));
		userInput.addKeyListener(new Message_fixup());
		searchBox.add(userInput,BorderLayout.CENTER);
		add(searchBox,BorderLayout.NORTH);
		
		
		jbtSearch.setFont(font);
		jbtSearch.setPreferredSize(new Dimension(100,15));
		jbtSearch.addActionListener(new jbtSearchListener());
		jbtlogin.addActionListener(new jbtloginListener());
		searchBox.add(jbtSearch,BorderLayout.EAST);
		searchBox.add(jbtlogin,BorderLayout.WEST);
		
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
		public void keyPressed(KeyEvent e)
		{
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				search_main();
			}
		}
	}
	
	class jbtSearchListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			search_main();
		}
	}
	
	class jbtloginListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			LogInterface frame = new LogInterface();
			frame.setTitle("Online Dictionary");
			//frame.setSize(500, 300);
			//frame.setLocationRelativeTo(null);
			//frame.setVisible(true);
		}
	}
	
	public void search_main()
	{
		String input = userInput.getText();
		boolean temp = false;
		int count = 0;
		for(int i = 0;i < input.length();i++)
		{
			if(input.compareTo("") == 0)
				break;
			if((input.charAt(i) >= '1' && input.charAt(i) <= '9') || (input.charAt(i) >= 'a' && input.charAt(i) <= 'z') || (input.charAt(i) >= 'A' && input.charAt(i) <= 'Z') || input.charAt(i) == ' ')
				count++;
			else
			{
				JOptionPane.showMessageDialog(null,"输入有误,请重新输入", "ERROR", JOptionPane.WARNING_MESSAGE);			
				String inputValue = JOptionPane.showInputDialog("请重新输入"); 
				userInput.setText(inputValue);
				search_main();
			}
		}
		if(count == input.length() && input.compareTo("") != 0)
			temp = true;
		if(temp)
		{
		if(flag[0] == false && flag[1] == false && flag[2] == false)
		{
			search_youdao(input,0);
			search_baidu(input,0);
			search_bing(input,0);
		}
		else 
		{
			if(flag[0])
				search_youdao(input,0);
			else if(!flag[0])
				search_youdao(input,1);
			if(flag[1])
				search_baidu(input,0);
			else if(!flag[1])
				search_baidu(input,1);
			if(flag[2])
				search_bing(input,0);
			else if(!flag[2])
				search_bing(input,1);
		}
		}
	}
	
	public void search_baidu(String input,int index)
	{
		if(index == 0)
		{

		Search p = new Search();
		try {
			String res = p.Find_Baidu(input);
			text_to_scream(res,1);
			if(res.length() == 0)
				text_to_scream("未找到释义",1);
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		}
		else
		{
			text_to_scream(null,1);
		}
	}
	
	public void search_youdao(String input,int index)
	{
		if(index == 0)
		{
		Search p = new Search();
		try {
			String res = p.Find_You_Dao(input);
			text_to_scream(res,0);
			if(res.length() == 0)
				text_to_scream("未找到释义",0);
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		}
		else
		{
			text_to_scream(null,0);
		}
	}

	public void search_bing(String input,int index)
	{
		if(index == 0)
		{
		Search p = new Search();
		try {
			String res = p.Find_Bing(input);
			text_to_scream(res,2);
			if(res.length() == 0)
				text_to_scream("未找到释义",2);
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		}
		else
		{
			text_to_scream(null,2);
		}
	}
	
	public void text_to_scream(String text,int index)
	{
		Component comp_0 = showPanel.getComponent(index);
		Component temp_0 =((JPanel) comp_0).getComponent(0);
		JTextArea textarea = (JTextArea)(((JScrollPane) temp_0).getViewport().getView());
		textarea.setText(text);
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
		//JLabel inputNotice=new JLabel(" Input");
		//inputNotice.setFont(font);
		//inputNotice.setPreferredSize(new Dimension(80,15));
		//headLine.setBounds(0,210,50,30);
		//searchBox.add(inputNotice,BorderLayout.WEST);

		
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
		jcYouDao.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	flag[0] = !flag[0];
            }
        });
		checkBox.add(jcYouDao, BorderLayout.WEST);
		
		JCheckBox jcBaiDu=new JCheckBox("百度");
		jcBaiDu.setFont(font);
		jcBaiDu.setPreferredSize(new Dimension(250,10));
		jcBaiDu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	flag[1] = !flag[1];
            }
        });
		checkBox.add(jcBaiDu,BorderLayout.CENTER);
		
		JCheckBox jcBing=new JCheckBox("Bing");
		jcBing.setFont(font);
		jcBing.setPreferredSize(new Dimension(250,10));
		jcBing.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	flag[2] = !flag[2];
            }
        });
		checkBox.add(jcBing,BorderLayout.EAST);
		
		return checkBox;
	}

	private JPanel ShowYouDao()
	{
		JPanel jpYouDao=new JPanel();

		jpYouDao.setLayout(new BorderLayout(5,10));
		
		JTextArea jtYouDao=new JTextArea();
		jtYouDao.setFont(font);
		JScrollPane jspAssociation = new JScrollPane(jtYouDao);			 //设置滑轮滚动
		jpYouDao.setPreferredSize(new Dimension(600,100));
		jpYouDao.add(jspAssociation,BorderLayout.CENTER);
		
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
		JScrollPane jspAssociation = new JScrollPane(jtBaiDu);			 //设置滑轮滚动
		jpBaiDu.setPreferredSize(new Dimension(600,100));
		jpBaiDu.add(jspAssociation,BorderLayout.CENTER);
		
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

		
		JScrollPane jspAssociation = new JScrollPane(jtBing);			 //设置滑轮滚动
		jpBing.setPreferredSize(new Dimension(600,100));
		jpBing.add(jspAssociation,BorderLayout.CENTER);
		
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
