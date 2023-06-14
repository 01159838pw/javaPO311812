package pl.edu.pw.fizyka.java.Game;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.SwingUtilities;

public class Music implements LineListener{

	static Clip audioClip = null;
	static AudioInputStream audioStream = null;
	static boolean key = true;
	
	
	public Music() {
		super();
		
	}

	static void play() {
		
		try {
			audioStream = AudioSystem.getAudioInputStream(new File("intheelement.wav"));
			AudioFormat format = audioStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			audioClip = (Clip) AudioSystem.getLine(info);
			audioClip.open(audioStream);
			
			
			Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					audioClip.start();
					audioClip.loop(-1);
					
				}
			});
			thread.start();
			
		} catch (IOException e) {
			
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(LineEvent event) {
		
		LineEvent.Type type = event.getType();
		
		if(type == LineEvent.Type.START) {
			key = true;
			
		}
		
		if(type == LineEvent.Type.STOP) {
			key = false;
			audioClip.start();
			
		}
		
		
	}
	
	static void runMusic() {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				Music.play();
				
			}
		});
	}
	

}