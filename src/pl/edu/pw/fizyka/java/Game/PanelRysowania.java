package pl.edu.pw.fizyka.java.Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

class PanelRysowania extends JPanel {

	
	private static final long serialVersionUID = 1L;
	List<Prostokat> bullets = new ArrayList<Prostokat>();
	List<Prostokat> mobs = new ArrayList<Prostokat>();
	Prostokat hero;

	private Timer timer;
	
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	
	private BufferedImage image;
	
	
	int amount = 0;
	
	int nan;
	int timeh, timem, times;
	int points;
	
	Music mus  = new Music();
	
	SaveScore SaveMachin;

	OknoRysowania okno;
	
	int width = d.width;
	int height = d.height;
	
	public PanelRysowania(OknoRysowania okno) {
		
		File imageFile = new File("las.jpg");
 		try {
 			image = ImageIO.read(imageFile);
 		} catch (IOException e) {
 			System.err.println("Blad odczytu obrazka");
 			e.printStackTrace();
 		}
 
 		Music.runMusic();
 		
 		
		
		addHero(100, 100, 40, 40, Color.blue);
		timeh = 0;
		timem = 0;
		times = 0;
		nan = 0;
		points = 0;
		//SaveMachin = new SaveScore();
		
		this.okno = okno;
		
	}
	
	
	
	public void dodajProstokat(int x, int y, int width, int height, Color c, int xv, int yv){
		Prostokat p = new Prostokat(2);
		p.setX(x);
		p.setY(y);
		p.setWidth(width);
		p.setHeight(height);
		p.setColor(c);
		
		x = 10;
		y = 10;
		
		x = (int)(Math.random()*6) + 3;
		y = (int)(Math.random()*6) + 3;
		
		p.setxV(xv);
		p.setyV(yv);	

		mobs.add(p);
		
		
	}
	
	public void addBullet(int width, int height, Color c, int xv, int yv){
		Prostokat p = new Prostokat(1);
		p.setX(hero.getX() + (hero.getWidth()/2));
		p.setY(hero.getY() + (hero.getHeight()/2));
		p.setWidth(width);
		p.setHeight(height);
		p.setColor(c);
	
		p.setxV(xv);
		p.setyV(yv);	

		bullets.add(p);
		
		
	}
	
	public void addHero(int x, int y, int width, int height, Color c){
		Prostokat p = new Prostokat(0);
		p.setX(x);
		p.setY(y);
		p.setWidth(width);
		p.setHeight(height);
		p.setColor(c);
		
		x = 10;
		y = 10;
		
	
		p.setxV(x/2);
		p.setyV(y/2);	

		//prostakaty.add(p);		
		hero = p;
		
		
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
 		g2d.drawImage(image, 0, 0, d.width, d.height, this);
		
		hero.paint(g);
		
		
		for (Prostokat pr : mobs) {
			pr.paint(g);
		}
		
		for (Prostokat pr : bullets) {
			pr.paint(g);
		}

	}
	
	
	void start() {
		
		timer = new Timer(34, null);
		
		

		timer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				
				if(amount == 75) {
					switch((int)(Math.random()*4 + 1)) {
						case 1:
							dodajProstokat(0, 0, 40, 40, Color.GREEN, (int)(Math.random()*6) + 3, (int)(Math.random()*6) + 3);
						break;
						
						case 2:
							dodajProstokat(1600, 0, 40, 40, Color.GREEN, -((int)(Math.random()*6) + 3), (int)(Math.random()*6) + 3);
						break;
						
						case 3:
							dodajProstokat(0, 900, 40, 40, Color.GREEN, (int)(Math.random()*6) + 3, -((int)(Math.random()*6) + 3));
						break;
						
						case 4:
							dodajProstokat(1600, 900, 40, 40, Color.GREEN, -((int)(Math.random()*6) + 3), -((int)(Math.random()*6) + 3));
						break;
					}
					amount = 0;
				}
				amount++;
				
				for (int i = 0; i< mobs.size() ; i++) {
					
					mobs.get(i).follow(hero);
					mobs.get(i).hitcheck(hero);
					
				}
				
				if(!hero.isalive()) {
						//usuwanie bohatera/koñczenie gry
						JFrame GETREKT = new JFrame();
						GETREKT.setLayout(new GridLayout(5, 1));
						GETREKT.setSize(100, 200);
						GETREKT.setLocation((width*5)/12, height/3);
						JLabel label1 = new JLabel("You get hit");
						
						String stime, spoints;
						stime = timeh + ":" + timem + ":" + times;
						spoints = "" + points;
						
						JLabel label2 = new JLabel("Time: " + stime);
						JLabel label3 = new JLabel("Points: " + spoints);
						JTextArea area = new JTextArea();
						JButton enter = new JButton("ok");
						
						label1.setBackground(Color.GREEN);
						label2.setBackground(Color.GREEN);
						label3.setBackground(Color.GREEN);
						
						GETREKT.add(label1);
						GETREKT.add(label2);
						GETREKT.add(label3);
						GETREKT.add(area);
						GETREKT.add(enter);
						
						GETREKT.setVisible(true);
						
						
						
						enter.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								SaveMachin = new SaveScore(area.getText(), stime, spoints);
								SaveMachin.save();
								GETREKT.setVisible(false);
								
								//new MenuFrame();
								okno.shutdown();
								
								
							}
						});
						
						timer.stop();
					}
				
				for (int i = 0; i< bullets.size() ; i++) {
					
					bullets.get(i).fly();
					if(bullets.get(i).wallcheck()) {
						bullets.remove(i);
						i--;
					}
					for(int j = 0; j < mobs.size(); j++) {
						
						try {
							bullets.get(i).hitcheck(mobs.get(j));
							
							if(!mobs.get(j).isalive()) {
								mobs.remove(j);
								points = points + 10;
							}
							if(!bullets.get(i).isalive()) {
								bullets.remove(i);
							}
						} catch(IndexOutOfBoundsException exp) {
							//problem rozwi¹zany
						}
						
						
					}
				}
				
				nan++;
				if(nan == 30) {
					points++;
					times++;
					nan = 0;
				}
				
				if(times == 60) {
					timem++;
					times = 0;
				}
				if(timem == 60) {
					timeh++;
					timem = 0;
				}
				
				
			
				repaint();
			}
		});
		
		timer.start();
		
		
	}
	
	public void move(char c) {
		hero.travel(c);
	}


	
	
	
	
}
