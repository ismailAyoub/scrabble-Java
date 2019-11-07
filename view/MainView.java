package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainView extends JFrame
{
	private JPanel contents;
	private Controller controller;
	
	public MainView() 
	{
		this.setSize(new Dimension(1000, 640));
		this.getContentPane().setBackground(Colors.red);
		contents = new StartMenuPanel();
		//this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		this.getContentPane().add(contents);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public Controller getController()
	{
		return controller;
	}
	
	public void setController(Controller c)
	{
		this.controller = c;
	}
	
	public void loadMenu(String s)
	{
		
		this.remove(contents);
		if (s.equals("StartMenu"))
		{
			this.contents = new StartMenuPanel();
		}
		if (s.equals("GlobalOptionsMenu"))
		{
			this.contents = new GlobalOptionsMenuPanel();
		}
		if (s.equals("Options"))
		{
			this.contents = new GlobalOptionsMenuPanel();
			
		}
		if (s.equals("Quit"))
		{
			System.exit(0);
		}
		this.add(contents);
		this.validate();
		this.repaint();
	}
	
	public void execute(String s)
	{
		if (s.equals("StartMenu"))
		{
			
			this.remove(contents);
			this.contents = new StartMenuPanel();
			this.add(contents);
			this.validate();
			this.repaint();
		}
		if (s.equals("GlobalOptionsMenu"))
		{
			this.remove(contents);
			this.contents = new GlobalOptionsMenuPanel();
			this.add(contents);
			this.validate();
			this.repaint();
		}
		if (s.equals("Options"))
		{
			this.remove(contents);
			this.contents = new GlobalOptionsMenuPanel();
			this.add(contents);
			this.validate();
			this.repaint();
		}
		if (s.equals("Quit"))
		{
			System.exit(0);
		}
	}


}