package pl.edu.pw.fizyka.java.Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class OknoRysowania extends JFrame{

	JLabel lab;
	PanelRysowania panel;
	
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	
	int width = d.width;
	int height = d.height;
	
	public OknoRysowania() {
		super("jakis projekt");
		setPreferredSize(new Dimension(width, height));
		setResizable(false);
		panel = new PanelRysowania(this);
 		addKeyListener(new Animator(panel));
 		setVisible(true);
 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		
 		pack();
 		
 		
		panel.setBackground(Color.YELLOW);
		
	
		
		add(panel, new BorderLayout().CENTER);
		setVisible(true);
		
		panel.start();
		
		
	}
	
	void shutdown() {
		this.dispose();
	}
	
	
}
