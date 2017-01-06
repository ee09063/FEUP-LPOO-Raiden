package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Logic.Raiden;

public class OptionMenu extends Menus
{
	
	public Rectangle godModeButton,
					 easyButton, 
			     	 normalButton,
			      	 hardButton,
			      	 showScoresButton,
					 backButton,
					 bugButton,
					 timerButton;
	
	public boolean godMode,
				   easy,
				   normal,
				   hard,
				   debug,
				   timerBullet;
	
	public OptionMenu()
	{
		super();
		godModeButton = new Rectangle(Raiden.getBoardWidth()/2-10, getBaseY()/2-30, 20, 20);
		timerButton = new Rectangle(Raiden.getBoardWidth()/2-10, getBaseY()/2+50, 20, 20);
		easyButton = new Rectangle(getBaseX()+150, getBaseY()+50-20, 20, 20);
		normalButton = new Rectangle(getBaseX()+150, getBaseY()+100-20, 20, 20);
		hardButton = new Rectangle(getBaseX()+150, getBaseY()+150-20, 20, 20);
		backButton = new Rectangle(Raiden.getBoardWidth()/2-75, Raiden.getBoardHeight()-100, 150, 50);
		bugButton = new Rectangle(Raiden.getBoardWidth()/2-10, Raiden.getBoardHeight()-getBaseY(), 20, 20);
		showScoresButton = new Rectangle(Raiden.getBoardWidth()/2-75, Raiden.getBoardHeight()-175, 150, 50);
		
		easy = true;
		normal = false;
		hard = false;
		debug = true;
		godMode = false;
		timerBullet = false;
	}
	
	/**
	 * Pinta o Menu de Opcoes.
	 */
	void paintOptionMenu(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		setContext(g2.getFontRenderContext());
		g.drawImage(bgMenu0.getBackground(), bgMenu0.getX(), bgMenu0.getY(),null);
		g.drawImage(bgMenu1.getBackground(), bgMenu1.getX(), bgMenu1.getY(),null);
		
		g.setColor(Color.WHITE);
		Font font3 = new Font("Candara", Font.BOLD, getFontSize());
		g.setFont(font3);
		g2.drawString("BACK", backButton.x+backButton.width/2-(int)(getWidth("BACK",font3))/2,
				              backButton.y+backButton.height/2+(int)(getHeight("BACK",font3))/4);
		g2.draw(backButton);
		
		g2.drawString("SCORES", showScoresButton.x+showScoresButton.width/2 - (int) (getWidth("SCORES", font3)/2),
								showScoresButton.y+showScoresButton.height/2 +(int)(getHeight("SCORES", font3)/4));
		g2.draw(showScoresButton);
		
		Font font0 = new Font("Candara", Font.BOLD, 25);
		g.setFont(font0);
		
		g2.drawString("TOGGLE GOD MODE", (int)(getWidth()/2-getWidth("TOGGLE GOD MODE",font0)/2), getBaseY()/2-50);
		g2.drawString("ANIMATED BULLETS", (int)(getWidth()/2-getWidth("ANIMATED BULLETS", font0)/2), getBaseY()/2+30);
		g2.drawString("DIFFICULTY", getBaseX(), getBaseY());
		g2.drawString("EASY", getBaseX(), getBaseY()+50);
		g2.drawString("NORMAL", getBaseX(), getBaseY()+100);
		g2.drawString("HARD", getBaseX(), getBaseY()+150);
		g2.drawString("DEBUG MODE", (int)(getWidth()/2-getWidth("DEBUG MODE",font0)/2), Raiden.getBoardHeight()-getBaseY()-20);
		
		if(godMode)
			g2.fill(godModeButton);
		else if(!godMode)
			g2.draw(godModeButton);
		if(timerBullet)
			g2.fill(timerButton);
		else if(!timerBullet)
			g2.draw(timerButton);
		if(easy)
			g2.fill(easyButton);
		else if(!easy)
			g2.draw(easyButton);
		if(normal)
			g2.fill(normalButton);
		else if(!normal)
			g2.draw(normalButton);
		if(hard)
			g2.fill(hardButton);
		else if(!hard)
			g2.draw(hardButton);
		if(debug)
			g2.fill(bugButton);
		else if(!debug)
			g2.draw(bugButton);
	}
}





