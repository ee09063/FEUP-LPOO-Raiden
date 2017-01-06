package Logic;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound
{
	
	private AudioClip menuMusic,
					  gameMusic,
					  gameOverMusic;
	
	protected static String path = System.getProperty("user.dir");
	
	public Sound(){
		try{
			gameOverMusic=Applet.newAudioClip(Sound.class.getResource("/gameOverMusic.wav"));
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			menuMusic=Applet.newAudioClip(Sound.class.getResource("/gameOverMusic.wav"));
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			gameMusic=Applet.newAudioClip(Sound.class.getResource("/gameMusic.wav"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void stopMenu(){
		menuMusic.stop();
	}
	
	public void stopGame(){
		gameMusic.stop();
	}
	
	public void stopGameOver(){
		gameOverMusic.stop();
	}
	
	public void loopGame(){
		gameMusic.loop();
	}
	
	public void loopGameOver(){
		gameOverMusic.loop();
	}
	
	public void loopMenu()
	{
		menuMusic.loop();
	}
}