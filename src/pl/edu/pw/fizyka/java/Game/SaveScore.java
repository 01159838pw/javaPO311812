package pl.edu.pw.fizyka.java.Game;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;



public class SaveScore {

	String name;
	String time;
	String points;
	ScoreBoardFrame frame;
	
	
	public SaveScore(String name, String time, String points) {
		this.name = name;
		this.time = time;
		this.points = points;
		frame = new ScoreBoardFrame();
		
	}
	
	void save() {
		
		try {
			
			frame.savescore(0, name, time, points);
				
			
			PrintWriter writer = new PrintWriter("Score.txt", "UTF-8");
			writer.println(frame.retText());
			writer.close();
			
		} catch (FileNotFoundException e) {
			
		} catch (UnsupportedEncodingException e) {
			
		}
		
		 
		 
		 
	}
	
}
