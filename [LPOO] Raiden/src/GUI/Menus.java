package GUI;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import Logic.Raiden;

public class Menus {
	
	public static Background bgMenu0,
	   				          bgMenu1;
	
	private static int baseY = Raiden.getBoardHeight()/3,
					   baseX = Raiden.getBoardWidth()/3;

	private String image = "space2.jpg";
	
	private int height,
			    width,
			    div,
			    fontSize,
			    commandOffset;
	
	private FontRenderContext context;

	public Menus()
	{
		bgMenu0 = new Background(0,0,1,image);
		bgMenu1 = new Background(0,-Raiden.getBoardHeight(),1,image);
		setHeight(Raiden.getBoardHeight());
		setWidth(Raiden.getBoardWidth());
		setDiv(getHeight()/48);
		setFontSize(getDiv()*2);
		setCommandOffset(30);
	}
	
	public Rectangle2D getBounds(String message, Font font)
	{
	    return font.getStringBounds(message, getContext());
	}
	
	/**
	 * Devolve a largura de uma string com uma determinada fonte.
	 */
	public double getWidth(String message, Font font)
	{
	    Rectangle2D bounds = getBounds(message, font);
	    return bounds.getWidth();
	}
	
	/**
	 * Devolve o comprimento de uma string com uma determinada fonte.
	 *
	 */
	public double getHeight(String message, Font font)
	{
		Rectangle2D bounds = getBounds(message, font);
		return bounds.getHeight();
	}

	
	public static int getBaseY() {
		return baseY;
	}

	public static void setBaseY(int baseY) {
		Menus.baseY = baseY;
	}
	
	
	public static int getBaseX() {
		return baseX;
	}
	
	
	public static void setBaseX(int baseX) {
		Menus.baseX = baseX;
	}
	
	
	public int getHeight() {
		return height;
	}

	
	public void setHeight(int height) {
		this.height = height;
	}

	
	public int getWidth() {
		return width;
	}

	
	public void setWidth(int width) {
		this.width = width;
	}

	
	public int getDiv() {
		return div;
	}


	public void setDiv(int div) {
		this.div = div;
	}
	

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public int getCommandOffset() {
		return commandOffset;
	}

	public void setCommandOffset(int commandOffset) {
		this.commandOffset = commandOffset;
	}


	public FontRenderContext getContext() {
		return context;
	}

	public void setContext(FontRenderContext context) {
		this.context = context;
	}
	
}
