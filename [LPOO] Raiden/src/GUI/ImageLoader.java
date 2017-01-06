package GUI;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageLoader {

	protected static String path = System.getProperty("user.dir");
	
	/**
	 * Class criada para carregar imagens.
	 */
	public BufferedImage load(String image){
		try {
			InputStream url = getClass().getResourceAsStream("/"+image);
			return ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
