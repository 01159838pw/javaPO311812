package pl.edu.pw.fizyka.java.Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MenuFrame extends JFrame{

	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	
	int width = d.width;
	int height = d.height;
	Font font = new Font("jakis", Font.ITALIC, 14);
	OknoRysowania okno;
	BufferedImage img;
	
	public MenuFrame() {
		super("Menu Demon Hord");
		setPreferredSize(new Dimension(width/6, height/4));
		setSize(width/4, (3*height)/4);
		setLocation((width*5)/12, height/7);
		setResizable(false);
 		setVisible(true);
 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		setLayout(new GridLayout(4, 1));
 		
 		try {
 			img = ImageIO.read(new File("hero.jpg"));
 		}
 			catch(IOException e) {
 				
 			}
 		
 		setIconImage(img);
 		
 		
 		JPanel p1 = new JPanel();
 		JPanel p2 = new JPanel();
 		JPanel p3 = new JPanel();
 		
 		p1.setBackground(Color.GREEN);
 		p2.setBackground(Color.GREEN);
 		p3.setBackground(Color.GREEN);
 		
 		JButton b1 = new JButton("Start");
 		JButton b2 = new JButton("Score board");
 		JButton b3 = new JButton("Exit");
 		
 		JTextArea lab = new JTextArea("	  Demon Hord \n 	Menu:");
 		lab.setBackground(Color.GREEN);
 		lab.setEditable(false);
 		lab.setFont(font);
 		
 		p1.add(b1);
 		p2.add(b2);
 		p3.add(b3);
 		
 		add(lab);
 		add(p1);
 		add(p2);
 		add(p3);
 		
		setVisible(true);
		
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				EventQueue.invokeLater(new Runnable() {
		 			
		 			public void run() {
		 				okno = new OknoRysowania();
		 				
		 				
		 				if(!okno.isDisplayable()) {
		 					
		 				
		 				}
		 			}
		 		});
				
				shutdown();
				
			}
		});
		
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			new ScoreBoardFrame();
			shutdown();
				
			}
		});
		
		b3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				shutdown();
				
			}
		});
		
	}
	
	void shutdown() {
		this.dispose();
	}
	
	
	
}
