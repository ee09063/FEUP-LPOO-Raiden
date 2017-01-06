package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Logic.Raiden;

public class ShowScores extends Menus{
	
	public Rectangle backButton;

	public ShowScores()
	{
		backButton = new Rectangle(Raiden.getBoardWidth()/2-75, Raiden.getBoardHeight()-100, 150, 50);
	}
	
	/**
	 * Pinta o Menu que mostra as melhores pontuacoes.
	 */
	public void paintShowScores(Graphics g)
	{
		
		Graphics2D g2 = (Graphics2D)g;
		setContext(g2.getFontRenderContext());
		g.drawImage(bgMenu0.getBackground(), bgMenu0.getX(), bgMenu0.getY(),null);
		g.drawImage(bgMenu1.getBackground(), bgMenu1.getX(), bgMenu1.getY(),null);
		
		g.setColor(Color.WHITE);
		Font font0 = new Font("Candara", Font.BOLD, getFontSize());
		g.setFont(font0);
		
		g2.draw(backButton);
		g2.drawString("BACK", backButton.x+backButton.width/2 - (int) (getWidth("OK", font0)),
							  backButton.y+backButton.height/2 +(int)(getHeight("OK", font0)/4));
		
		for(int i = 0; i < Raiden.score.scr.size(); i++)
		{
			if(i == 10)
				break;
			g2.drawString(Raiden.score.scr.get(i),
					      (int) (getWidth()/2-getWidth(Raiden.score.scr.get(i), font0)/2),
					      getHeight()/12 + i*getHeight()/12);
		}
	}
	
}
