package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Logic.Raiden;

public class MainMenu extends Menus
{
	
	public static Rectangle playButton,
							optionsButton,
							helpButton,
							exitButton;
	
	public MainMenu()
	{
		super();
		playButton = new Rectangle(Raiden.getBoardWidth()/2-50, getBaseY(), 100, 50);
		optionsButton = new Rectangle(Raiden.getBoardWidth()/2-50, getBaseY()+100, 100, 50);
		helpButton = new Rectangle(Raiden.getBoardWidth()/2-50, getBaseY()+200, 100, 50);
		exitButton = new Rectangle(Raiden.getBoardWidth()/2-50, getBaseY()+300, 100, 50);
	}
	
	/**
	 * Pinta o menu principal.
	 */
	public void paintMenu(Graphics g)
	{
		g.drawImage(bgMenu0.getBackground(), bgMenu0.getX(), bgMenu0.getY(),null);
		g.drawImage(bgMenu1.getBackground(), bgMenu1.getX(), bgMenu1.getY(),null);
		
		Graphics2D g2 = (Graphics2D) g;
		setContext(g2.getFontRenderContext());
		
		Font font0 = new Font("Candara", Font.BOLD, 50);
		g.setFont(font0);
		g.setColor(Color.WHITE);
		g.drawString("RAIDEN", Raiden.getBoardWidth()/2-75, getBaseY()-100);
		
		Font font1 = new Font("Candara", Font.BOLD, 30);
		Font font2 = new Font("Candara", Font.BOLD, 23);
		g.setFont(font1);
		g2.drawString("PLAY", playButton.x+17, playButton.y+35);
		g2.draw(playButton);
		g.setFont(font2);
		g2.drawString("OPTIONS", optionsButton.x+5, optionsButton.y+35);
		g2.draw(optionsButton);
		g.setFont(font1);
		g2.drawString("HELP", helpButton.x+17, helpButton.y+35);
		g2.draw(helpButton);
		g2.drawString("EXIT", exitButton.x+20, exitButton.y+35);
		g2.draw(exitButton);
	}

}
