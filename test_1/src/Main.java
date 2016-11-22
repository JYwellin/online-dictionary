import java.awt.*;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

//Ö÷º¯Êý
public class Main {
	public static void main(String[] args) throws IOException
	{
		FrontPage frame=new FrontPage();
		frame.setTitle("Online dictionary");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setVisible(true);
	}
}