package pl.edu.pw.fizyka.java.Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ScoreBoardFrame extends JFrame{
	
	JTextArea area;

	String[][] scoretab = new String[10][3];
	int counter;
	String text;
	
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	
	int width = d.width;
	int height = d.height;
	
	public ScoreBoardFrame() {
		
		super("Scoreboard");
		
		counter = 0;
		setPreferredSize(new Dimension(400, 600));
		setSize(400, 600);
		setLocation((width*5)/12, height/6);
		setResizable(false);
 		setVisible(true);
 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		
 		area = new JTextArea();
 		area.setEditable(false);
 		area.setBackground(Color.GREEN);
		
 		try {
			loadScore();
		} catch (FileNotFoundException e1) {
			System.out.println("error");
		}
 		
 		JPanel pane = new JPanel();
 		pane.setBackground(Color.YELLOW);
 		
 		JPanel pane2 = new JPanel();
 		pane2.setBackground(Color.YELLOW);
 		JLabel lab = new JLabel("Top 10 best scores:");
 		lab.setBackground(Color.YELLOW);
 		
 		JButton pres = new JButton("Back");
 		
 		pane.add(pres);
 		
 		pane2.add(lab);
 		
 		this.add(area, BorderLayout.CENTER);
 		this.add(pane, BorderLayout.SOUTH);
 		this.add(pane2, BorderLayout.NORTH);
 		
 		
 		setVisible(true);
 		
 		pres.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new MenuFrame();
				shutdown();
				
			}
		});
 		
 		
 		
	}
	
	void loadScore() throws FileNotFoundException {
		
		File toread = new File("Score.txt");
		
		Scanner scan;
		
		try {
			scan = new Scanner(toread, "UTF-8");
			
		} catch (FileNotFoundException e) {
			
			PrintWriter writer;
			
			try {
				writer = new PrintWriter("Score.txt", "UTF-8");
				writer.println("Scores: \n Name | Time | points \n1. 0 0 0");
				writer.println("2. 0 0 0\n3. 0 0 0\n4. 0 0 0");
				writer.println("5. 0 0 0\n6. 0 0 0\n7. 0 0 0");
				writer.println("8. 0 0 0\n9. 0 0 0\n10. 0 0 0");
				writer.close();
				
			} catch (FileNotFoundException e1) {
				
			} catch (UnsupportedEncodingException e1) {
				
			}
			
				scan = new Scanner(toread);
			
			
		}
		
		String line = scan.nextLine();
		
		counter = 0;
		
		
		while(scan.hasNextLine()) {
			
			line = line +  "\n" + scan.nextLine(); //problem -
			
		}
		
		
		text = line;
		area.setText(line);
		counter = 0;
		
		String[] tab = new String[12];
		String[][] tab2 = new String[10][5];
		
		tab = line.split("\n");
		
		for(int i = 2; i < tab.length; i++) {
			
			tab2[i-2] = tab[i].split(" ");
		}
		
		for(int i = 0; i < scoretab.length; i++) {
			
			scoretab[i][0] = tab2[i][1];
			scoretab[i][1] = tab2[i][2];
			scoretab[i][2] = tab2[i][3];
			
			
		}
		
	}
	
	
	void savescore(int x, String q, String w, String e) {
		
		if(x > 0) {
			
			if(scoretab[x][2].equals("0")) {
				scoretab[x][0] = q;
				scoretab[x][1] = w;
				scoretab[x][2] = e;
			}
			else {
			
				if(Integer.parseInt(e) > Integer.parseInt(scoretab[x][2])) {
				savescore(x-1, scoretab[x][0], scoretab[x][1], scoretab[x][2]);
				scoretab[x][0] = q;
				scoretab[x][1] = w;
				scoretab[x][2] = e;
				}
				else {
					savescore(x+1, q, w, e);
				}
			
		}
			
		}
		if(x == 0) {
			
			if(scoretab[x][2].equals("0")) {
				scoretab[x][0] = q;
				scoretab[x][1] = w;
				scoretab[x][2] = e;
			}
			else {
			
			if(Integer.parseInt(e) > Integer.parseInt(scoretab[x][2])) {
				savescore(x+1, scoretab[x][0], scoretab[x][1], scoretab[x][2]);
				scoretab[x][0] = q;
				scoretab[x][1] = w;
				scoretab[x][2] = e;
				}
			
			if(Integer.parseInt(e) < Integer.parseInt(scoretab[x][2])) {
				savescore(x+1, q, w, e);
			}
			
			}
		
		
		}
		
		
		text = "Scores: \n Name | Time | points \n";
		
		for(int i = 0; i < scoretab.length; i++) {
			text = text + (i + 1) + ". " + scoretab[i][0] + " " + scoretab[i][1] + " " + scoretab[i][2] + "\n";
		}
		
	}
	
	String retText() {
		return text;
	}
	
	void shutdown() {
		this.dispose();
	}
	
}
