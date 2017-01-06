package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Logic.Raiden;

public class PauseMenu extends Menus {

	public Rectangle resumeButton,
					 optionsButton,
					 backButton,
					 exitButton;

	public PauseMenu()
	{
		super();
		resumeButton = new Rectangle(Raiden.getBoardWidth()/2-75, Raiden.getBoardHeight()/2-25, 150, 50);
		exitButton = new Rectangle(Raiden.getBoardWidth()/2-75, Raiden.getBoardHeight()/2+125, 150, 50);
		backButton = new Rectangle(Raiden.getBoardWidth()/2-75, Raiden.getBoardHeight()/2+50, 150, 50);
	}
	
	/**
	 * Pinta o Menu de Pausa.
	 */
	public void paintPauseMenu(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		setContext(g2.getFontRenderContext());
		
		Font font0 = new Font("Candara", Font.BOLD, 50);
		g.setFont(font0);
		g.setColor(Color.WHITE);
		g.drawString("PAUSE", Raiden.getBoardWidth()/2-75, Raiden.getBoardHeight()/2-50);
		
		Font font1 = new Font("Candara", Font.BOLD, 30);
		g.setFont(font1);
		
		g2.drawString("RESUME", resumeButton.x+resumeButton.width/2 - (int) (getWidth("RESUME", font1)/2),
				                resumeButton.y+resumeButton.height/2 +(int) (getHeight("RESUME", font1)/4));
		g2.draw(resumeButton);
		
		g2.drawString("MAIN", backButton.x+resumeButton.width/2 - (int) (getWidth("MAIN", font1)/2),
                			  backButton.y+resumeButton.height/2 +(int) (getHeight("MAIN", font1)/4));
		g2.draw(backButton);
		
		g2.drawString("EXIT", exitButton.x+exitButton.width/2 - (int) (getWidth("EXIT", font1)/2),
				              exitButton.y+exitButton.height/2 +(int)(getHeight("EXIT", font1)/4));
		g2.draw(exitButton);
	}
}

