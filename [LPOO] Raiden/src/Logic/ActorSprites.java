package Logic;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import GUI.ImageLoader;

/**
 * Data Class. Guarda os sprites para as naves.
 */
public class ActorSprites {
	
	static ArrayList<BufferedImage> ssBlue = new ArrayList<BufferedImage>(),
								    ssRed = new ArrayList<BufferedImage>(),
									ssEnemyBlue = new ArrayList<BufferedImage>(),
									ssEnemyRed = new ArrayList<BufferedImage>(),
									ssExplosion = new ArrayList<BufferedImage>();

	public ActorSprites()
	{
		setUpSprites();
	}
	
	/**
	 * Sets the up sprites.
	 */
	public void setUpSprites()
	{
		ImageLoader loader = new ImageLoader();
		ssBlue.add(loader.load("HF1B.png"));
		ssBlue.add(loader.load("HF2B.png"));
		ssBlue.add(loader.load("HF3B.png"));
		ssBlue.add(loader.load("HF4B.png"));
		ssBlue.add(loader.load("HF5B.png"));
		
		ssRed.add(loader.load("HF1R.png"));
		ssRed.add(loader.load("HF2R.png"));
		ssRed.add(loader.load("HF3R.png"));
		ssRed.add(loader.load("HF4R.png"));
		ssRed.add(loader.load("HF5R.png"));
		
		ssEnemyBlue.add(loader.load("E1B.png"));
		ssEnemyBlue.add(loader.load("E2B.png"));
		ssEnemyBlue.add(loader.load("E3B.png"));
		ssEnemyBlue.add(loader.load("E4B.png"));
		ssEnemyBlue.add(loader.load("E5B.png"));
		
		ssEnemyRed.add(loader.load("E1R.png"));
		ssEnemyRed.add(loader.load("E2R.png"));
		ssEnemyRed.add(loader.load("E3R.png"));
		ssEnemyRed.add(loader.load("E4R.png"));
		ssEnemyRed.add(loader.load("E5R.png"));
		
		ssExplosion.add(loader.load("explosionF0.png"));
		ssExplosion.add(loader.load("explosionF1.png"));
		ssExplosion.add(loader.load("explosionF2.png"));
		ssExplosion.add(loader.load("explosionF3.png"));
		ssExplosion.add(loader.load("explosionF4.png"));
		ssExplosion.add(loader.load("explosionF5.png"));
		ssExplosion.add(loader.load("explosionF6.png"));
		ssExplosion.add(loader.load("explosionF7.png"));
	}

	public static ArrayList<BufferedImage> getBlue()
	{
		return ssBlue;
	}
	
	public static ArrayList<BufferedImage> getRed()
	{
		return ssRed;
	}
	
	public static ArrayList<BufferedImage> getEnemyRed()
	{
		return ssEnemyRed;
	}

	public static ArrayList<BufferedImage> getEnemyBlue()
	{
		return ssEnemyBlue;
	}
}
