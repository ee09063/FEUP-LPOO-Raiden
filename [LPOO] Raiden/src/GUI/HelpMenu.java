package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Logic.Craft;
import Logic.Enemy;
import Logic.Raiden;

public class HelpMenu extends Menus
{
	
	private BufferedImage up,
						  down,
						  left,
						  right,
						  life,
						  shield;
	
	private static Rectangle backButton;
	
	private Enemy enemyRed,
				  enemyBlue;
	
	private Craft craft;
	
	public HelpMenu()
	{
		super();
		setBackButton(new Rectangle(getWidth()/2-(getWidth()/8), getHeight()-100, getWidth()/4, 50));
		ImageLoader loader = new ImageLoader();
		up = loader.load("upArrow.png");
		down = loader.load("downArrow.png");
		left = loader.load("leftArrow.png");
		right = loader.load("rightArrow.png");
		life = loader.load("life.png");
		shield = loader.load("shield.png");
		
		enemyRed = new Enemy(getWidth()/2, getHeight()/2+150, 0, 0, 1, 0);
		enemyBlue = new Enemy(getWidth()/2, getHeight()/2+150,0, 0, 0, 0);
		craft = new Craft(getWidth()/2, getHeight()/2+10, 0);
		craft.changeMode();
	}
	
	/**
	 * Pinta o menu de Ajuda.
	 *
	 */
	void paintHelpMenu(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		Craft c = Raiden.craft;
		
		setContext(g2.getFontRenderContext());
		
		g.drawImage(bgMenu0.getBackground(), bgMenu0.getX(), bgMenu0.getY(),null);
		g.drawImage(bgMenu1.getBackground(), bgMenu1.getX(), bgMenu1.getY(),null);
		
		Font font1 = new Font("Candara", Font.BOLD, getFontSize()/2);
		g.setFont(font1);
		g.setColor(Color.WHITE);
		g2.drawString("MOVE YOUR SHIP USING WASD", getWidth()/2-(int)(getWidth("MOVE YOUR SHIP USING WASD", font1)/2),
				                                   Raiden.getBoardHeight()/10);
		g.drawImage(c.getSprite(), getWidth()/2 + getCommandOffset(),
				                   getHeight()/7,
				                   c.getImageWidth()/c.getDrawSize(),
				                   c.getImageHeight()/c.getDrawSize(), null);
		
		
		g2.drawString("W", getWidth()/5 + getCommandOffset(),
						   getHeight()/7);
		g.drawImage(up, getWidth()/5+10 + getCommandOffset(),
				        getHeight()/7,
				        up.getWidth()/getDiv(),
				        up.getHeight()/getDiv(), null);
		
		g2.drawString("S", getWidth()/5 + getCommandOffset(),
				           getHeight()/4);
		g.drawImage(down, getWidth()/5+10 + getCommandOffset(),
				          getHeight()/4,
				          down.getWidth()/getDiv(),
				          down.getHeight()/getDiv(), null);
		
		g2.drawString("A", getWidth()/10 + getCommandOffset(),
				           getHeight()/5);
		g.drawImage(left, getWidth()/10+10 + getCommandOffset(),
				          getHeight()/5,
				          left.getWidth()/getDiv(),
				          left.getHeight()/getDiv(), null);
		
		g2.drawString("D", getWidth()/3 + getCommandOffset(),
				           getHeight()/5);
		g.drawImage(right, getWidth()/3+10 + getCommandOffset(),
				           getHeight()/5,
				           right.getWidth()/getDiv(),
				           right.getHeight()/getDiv(), null);
		
		Font font3 = new Font("Candara", Font.BOLD, getFontSize());
		g.setFont(font3);
		g2.drawString("BACK", backButton.x+backButton.width/2 - (int) (getWidth("BACK", font3)/2),
							  backButton.y+backButton.height/2 +(int)(getHeight("BACK", font3)/4));
		g2.draw(backButton);
		
		g.setFont(font1);
		g2.drawString("FIRE - LEFT MOUSE BUTTON", getWidth()/2-(int)(getWidth("FIRE - LEFT MOUSE BUTTON", font1)/2),
				                                  getHeight()/3+50);
		g2.drawString("CHANGE FORM - RIGHT MOUSE BUTTON", getWidth()/2-(int)(getWidth("CHANGE FORM - RIGHT MOUSE BUTTON", font1)/2),
                								          getHeight()/3+75);
		
		g2.drawImage(life, getWidth()/5, getHeight()/2, life.getWidth(), life.getHeight(), null);
		g2.drawString("LIFE", getWidth()/5+20, (int) (getHeight()/2 + getHeight("LIFE", font1)/2));
		
		g2.drawImage(shield, getWidth()/5+20, getHeight()/2+20, shield.getWidth(), shield.getHeight(), null);
		g2.drawString("SHIELDS", (int) (getWidth()/5 - getWidth("SHIELDS", font1)/2-10),
				                 (int) (getHeight()/2 + 20 + shield.getHeight()));
		
		g2.drawString("A BLUE SHIP", (int) (getWidth()/2 - getWidth("A BLUE SHIP", font1)/2),
									   (int) (getHeight()/2));
		g2.drawImage(c.getSprite(), craft.getX()-c.getImageWidth()/c.getDrawSize()/2,
				                    craft.getY(),
				                    craft.getImageWidth()/craft.getDrawSize(),
				                    craft.getImageHeight()/craft.getDrawSize(), null);
		
		g2.drawString("A RED SHIP", ((int) (getWidth()/2 - getWidth("A RED SHIP", font1)/2))+100,
				                    (int) (getHeight()/2));
		g2.drawImage(craft.getSprite(), (craft.getX()-c.getImageWidth()/c.getDrawSize()/2)+100,
									    craft.getY(),
									    craft.getImageWidth()/craft.getDrawSize(),
									    craft.getImageHeight()/craft.getDrawSize(), null);
		
		g2.drawString("DESTROYS", (int) (getWidth()/2 - getWidth("DESTROYS", font1)/2),
								  (int) (getHeight()/2+125));
		g2.drawImage(enemyRed.getSprite(), enemyRed.getX()-enemyBlue.getImageWidth()/enemyBlue.getDrawSize()/2,
				               			   enemyRed.getY(),
				               			   enemyRed.getImageWidth()/enemyRed.getDrawSize(),
				               			   enemyRed.getImageHeight()/enemyRed.getDrawSize(), null);
		
		g2.drawString("DESTROYS", (int) (getWidth()/2 - getWidth("DESTROYS", font1)/2)+100,
								  (int) (getHeight()/2+125));
		g2.drawImage(enemyBlue.getSprite(), enemyRed.getX()+100-enemyRed.getImageWidth()/enemyRed.getDrawSize()/2,
    			     enemyBlue.getY(),
    			     enemyBlue.getImageWidth()/enemyRed.getDrawSize(),
    			     enemyBlue.getImageHeight()/enemyRed.getDrawSize(), null);


	}


	public static Rectangle getBackButton() {
		return backButton;
	}

	public static void setBackButton(Rectangle backButton) {
		HelpMenu.backButton = backButton;
	}

}
