package GUI;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import Logic.Raiden;

public class Background {
	
	
	private int x,
				y,
				speed;
	
	private BufferedImage background;
	
	private int width,
				height;

	public Background(int x, int y, int speed, String path)
	{
		setX(x);
		setY(y);
		setSpeed(speed);
		width = Raiden.getBoardWidth();
		height = Raiden.getBoardHeight();
		ImageLoader loader = new ImageLoader();
		BufferedImage img = loader.load(path);
		background = resize(img, width, height);
	}
	
	/**
	 * Faz resize da imagem para o tamanho do ecra.
	 *
	 */
	public static BufferedImage resize(BufferedImage img, int newW, int newH) {  
        int w = img.getWidth();  
        int h = img.getHeight();  
        BufferedImage dimg = new BufferedImage(newW, newH, img.getType());  
        Graphics2D g = dimg.createGraphics();  
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);  
        g.dispose();  
        return dimg;  
    }  
	
	/**
	 * Movimenta o background.
	 */
	public void move()
	{
		setY(getY() + getSpeed());
		if(getY() >= Raiden.getBoardHeight())
		{
			setY(-Raiden.getBoardHeight());
		}
	}
	

	public  int getX() {
		return x;
	}

	
	public  void setX(int x) {
		this.x = x;
	}

	
	public  int getY() {
		return y;
	}

	
	public  void setY(int y) {
		this.y = y;
	}

	
	public  int getSpeed() {
		return speed;
	}

	
	public  void setSpeed(int speed) {
		this.speed = speed;
	}

	
	public Image getBackground() {
		return background;
	}

	public void setBackground(BufferedImage background) {
		this.background = background;
	}
	

}
