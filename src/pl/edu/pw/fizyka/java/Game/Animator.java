package pl.edu.pw.fizyka.java.Game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

public class Animator implements KeyListener {
	
	private Timer right;
	private Timer left;
	private Timer down;
	private Timer up;
	
	private int x, y;
	
	private PanelRysowania panel;
	
	public Animator(PanelRysowania panel) {
		defineTimers();
		this.panel = panel;
		x = 0;
		y = 20;
	}
	
	public void defineTimers() {
		
	
	ActionListener l = new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	//char c = e.getKeyChar();
	    		panel.move('a');
	    		panel.repaint();
	        	
	        }
	    };
	    
	    left = new Timer(10, l);
	    
	    ActionListener r = new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	panel.move('d');
	    		panel.repaint();
	        }
	    };
	    right = new Timer(10, r);
	    ActionListener d = new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	panel.move('s');
	    		panel.repaint();
	        }
	    };
	    down = new Timer(10, d);
	    ActionListener u = new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	panel.move('w');
	    		panel.repaint();
	        }
	    };
	    up = new Timer(10, u);
	   
	}

	    @Override
	public void keyTyped(KeyEvent e) {
	    

	}
	@Override
	public void keyPressed(KeyEvent e) {
	    if (e.getKeyCode() == KeyEvent.VK_W) {
	    	x = 0;
	    	y = -20;
	        up.start();
	    } else if (e.getKeyCode() == KeyEvent.VK_S) {
	    	x = 0;
	    	y = 20;
	        down.start();
	    } else if (e.getKeyCode() == KeyEvent.VK_D) {
	    	x = 20;
	    	y = 0;
	        right.start();
	    } else if (e.getKeyCode() == KeyEvent.VK_A) {
	    	x = -20;
	    	y = 0;
	        left.start();
	    }  else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
	        //spacebar.start();
	    	panel.addBullet(10, 10, Color.RED, x, y);
    		panel.repaint();
	    }
	}
	@Override
	public void keyReleased(KeyEvent e) {
	    if (e.getKeyCode() == KeyEvent.VK_W) {
	        up.stop();
	    } else if (e.getKeyCode() == KeyEvent.VK_S) {
	        down.stop();
	    } else if (e.getKeyCode() == KeyEvent.VK_D) {
	        right.stop();
	    } else if (e.getKeyCode() == KeyEvent.VK_A) {
	        left.stop();
	    }
	}

	
	
}