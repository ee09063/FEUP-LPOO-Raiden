package Logic;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import GUI.ImageLoader;

/**
 * Data Class. Guarda os sprites para as balas
 *
 */
public class BulletSprites {

	public static ArrayList<BufferedImage> ssBlue = new ArrayList<BufferedImage>(),
										   ssRed = new ArrayList<BufferedImage>(),
										   ssEnemyBlue = new ArrayList<BufferedImage>(),
										   ssEnemyRed = new ArrayList<BufferedImage>();
	
	public BulletSprites()
	{
		setUpSprites();
	}
	
	/**
	 * Prepara os sprites
	 */
	public void setUpSprites()
	{
		ImageLoader loader = new ImageLoader();
		ssBlue.add(loader.load("bulletF0.png"));
		ssBlue.add(loader.load("bulletF1.png"));
		ssBlue.add(loader.load("bulletF2.png"));
		ssBlue.add(loader.load("bulletF1.png"));
		
		ssRed.add(loader.load("bulletF5.png"));
		ssRed.add(loader.load("bulletF6.png"));
		ssRed.add(loader.load("bulletF7.png"));
		ssRed.add(loader.load("bulletF6.png"));
		
		ssEnemyBlue.add(loader.load("bulletF15.png"));
		ssEnemyBlue.add(loader.load("bulletF16.png"));
		ssEnemyBlue.add(loader.load("bulletF17.png"));
		ssEnemyBlue.add(loader.load("bulletF16.png"));
		
		ssEnemyRed.add(loader.load("bulletF11.png"));
		ssEnemyRed.add(loader.load("bulletF12.png"));
		ssEnemyRed.add(loader.load("bulletF13.png"));
		ssEnemyRed.add(loader.load("bulletF14.png"));
		ssEnemyRed.add(loader.load("bulletF13.png"));
	}
	

	public static ArrayList<BufferedImage> getRed()
	{
		return ssRed;
	}
	
	public static ArrayList<BufferedImage> getBlue()
	{
		return ssBlue;
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
