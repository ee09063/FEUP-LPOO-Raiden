package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Logic.Raiden;


public class GameOverScreen extends Menus
{
	
	private Rectangle nameEntry,
					  okButton;
	
	private String playerName;
	
	public GameOverScreen()
	{
		super();
		playerName = "";
		nameEntry = new Rectangle(getWidth()/2-(getWidth()/8), getHeight()/2, getWidth()/4, 50);
		setOkButton(new Rectangle(getWidth()/2-(getWidth()/8), nameEntry.y+100, getWidth()/4, 50));
	}
	
	/**
	 * Os caracteres sao adicionados a medida que o jogador os introduz.
	 *
	 */
	public void addCharToString(String character)
	{
		playerName += character;
	}
	
	/**
	 * Pinta o ecra de game over.
	 *
	 */
    void paintGameOverScreen(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		setContext(g2.getFontRenderContext());
		Font font1 = new Font("Candara", Font.BOLD, getFontSize());
		g2.setColor(Color.WHITE);
		g2.setFont(font1);
		
		g2.drawImage(bgMenu0.getBackground(), bgMenu0.getX(), bgMenu0.getY(),null);
		g2.drawImage(bgMenu1.getBackground(), bgMenu1.getX(), bgMenu1.getY(),null);
		
		g2.drawString("GAME OVER", (int) (getWidth()/2-getWidth("GAME OVER", font1)/2), getHeight()/6);
		
		g2.drawString("SCORE", (int) (getWidth()/2-getWidth("SCORE", font1)/2), getHeight()/5+50);
		String score = ""+Raiden.score.getScore();
		g2.drawString(score,  (int) (getWidth()/2-getWidth(score, font1)/2), getHeight()/5+100);
		
		g2.drawString("ENTER NAME",  (int) (getWidth()/2-getWidth("ENTER NAME", font1)/2), getHeight()/5+200);
		g2.draw(nameEntry);
		g2.drawString(playerName, nameEntry.x+nameEntry.width/2 - (int) (getWidth(playerName, font1)/2),
								  nameEntry.y+nameEntry.height/2 +(int)(getHeight(playerName, font1)/4));
		
		g2.draw(getOkButton());
		g2.drawString("OK", getOkButton().x+okButton.width/2 - (int) (getWidth("OK", font1)/2),
							getOkButton().y+okButton.height/2 +(int)(getHeight("OK", font1)/4));
	}
	
	
	public String getPlayerName() {
		return this.playerName;
	}

	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	
	public Rectangle getOkButton() {
		return this.okButton;
	}

	
	public void setOkButton(Rectangle okButton) {
		this.okButton = okButton;
	}
	
}
