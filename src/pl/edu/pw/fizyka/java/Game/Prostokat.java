package pl.edu.pw.fizyka.java.Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Prostokat{

	
	
    private int xPos = 2000;
	private int yPos = 2000;
    private int width = 20;
    private int height = 20;
    private Color color = Color.BLACK;
    private BufferedImage[] img = new BufferedImage[3];
    int Models;
    
    
    public Prostokat(int x) {
    	
    	for(int i = 0; i < 3; i++) {
    		img[i] = null;
    	}
    	
    	try {
    	    img[0] = ImageIO.read(new File("hero.jpg"));
    	    img[1] = ImageIO.read(new File("chomik.jpg"));
    	    img[2] = ImageIO.read(new File("mob.jpg"));
    	} catch (IOException e) {
    	}
    	Models = x;
    }
    
    public Prostokat(boolean b) {
    	if(b) {
    		xPos = 2000;
    		yPos = -2000;
    	    width = 20;
    	    height = 20;
    	    color = Color.BLACK;
    	}
    }
    
    private int xV = 0;
    private int yV = 0;
    
    private boolean alive = true;
    
    public int getxV() {
		return xV;
	}

	public void setxV(int xV) {
		this.xV = xV;
	}

	public int getyV() {
		return yV;
	}

	public void setyV(int yV) {
		this.yV = yV;
	}

	public int getX() {
		return xPos;
	}

	public void setX(int xPos) {
		this.xPos = xPos;
	}

    public void setY(int yPos){
        this.yPos = yPos;
    }

    public int getY(){
        return yPos;
    }

    public int getWidth(){
        return width;
    } 

    public int getHeight(){
        return height;
    }

    public boolean ifcontain(int x, int y) {
    	
    	if(xPos < x && x < (xPos + width) && yPos < y && y < (yPos + height)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    	
    }

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

    public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void paint(Graphics g){
        g.setColor(getColor());
        g.drawImage(img[Models], xPos, yPos, width, height, null);
        
    }
	
	public void travel(char c) {
		
		
		switch (c) {
			case 'a':
				if(xPos > 0) {
					xPos = xPos - xV;
				}
				break;
			case 'w':
				if(yPos > 0) {
					yPos = yPos - yV;
				}
				break;
			case 'd':
				if(xPos < 1843) {
					xPos = xPos + xV;
				}
				break;
			case 's':
				if(yPos < 910) {
					yPos = yPos + yV;
				}
				break;
		}
				
		
	}
	
	public void fly() {
		
			xPos = xPos + xV;
			yPos = yPos + yV;
		
		
	}
	
	boolean wallcheck() {
		
		boolean a, b;
		if(xPos > 0 && xPos < 1843) {
			a =  false;
		}
		else {
			a =  true;
		}
		if(yPos > 0 && yPos < 910) {
			b =  false;
		}
		else {
			b =  true;
		}
		
		if(a && b) {
			return true;
		}
		else if(xPos == 2000 && yPos == 2000) {
			return true;
		}
		else {
			return false;
		}
	}
	
	void hitcheck(Prostokat p) {
		boolean key1 = true, key2 = true;
		if(((xPos + width) < p.getX()) || ((xPos) > (p.getX()+p.getWidth()))) {
			key1 = false;
		}
		
		if(((yPos + height) < p.getY()) || ((yPos) > (p.getY()+p.getHeight()))) {
			key2 = false;
		}
		
		if(key1 && key2) {
			kill();
			p.kill();
		}
		
	}
	
	void kill() {
		alive = false;
	}
	
	boolean isalive() {
		return alive;
	}
	
	void follow(Prostokat p) {
		
		xV = p.getX() - xPos;
		yV = p.getY() - yPos;
		
		if(xPos > p.getX()) {
			if(p.getX() - xPos < -4) {
				xV = -4;
			}
		}
		else {
			if(p.getX() - xPos > 4) {
				xV = 4;
			}
		}
		
		if(yPos > p.getY()) {
			if(p.getY() - yPos < -4) {
				yV = -4;
			}
		}
		else {
			if(p.getY() - yPos > 4) {
				yV = 4;
			}
		}
		
		fly();
	}

}
