package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Logic.Craft;
import Logic.Raiden;


public class myPanel extends JPanel
{
	
	private static final long serialVersionUID = 1L;
	
	public myPanel()
	{
		this.setBackground(Color.BLACK);
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(Raiden.getBoardWidth(),Raiden.getBoardHeight());
	}
	
	/**
	 * Pinta o Heads Up Display.
	 */
	public void paintHUD(Graphics g)
	{
		Raiden.score.drawScore(g);
		Raiden.craft.paintLifeBar(g);
		Raiden.craft.paintShields(g);
	}
	
	/**
	 * Pinta o ecra de jogo.
	 *
	 */
	void paintGame(Graphics g)
	{
		g.drawImage(Menus.bgMenu0.getBackground(), Menus.bgMenu0.getX(), Menus.bgMenu0.getY(),null);
		g.drawImage(Menus.bgMenu1.getBackground(), Menus.bgMenu1.getX(), Menus.bgMenu1.getY(),null);
		
		Craft craft = Raiden.craft;
		//paints my craft
		Raiden.craft.paintCraft(g);
		//paint my bullets
		for(int i = 0; i < craft.bullets.size(); i++)
		{
			if(craft.bullets.get(i).isVisible())
			{
				craft.bullets.get(i).paintBullet(g);
			}
		}
		//paint my enemies
		for(int i = 0; i < Raiden.enemies.size(); i++){
			Raiden.enemies.get(i).paintEnemy(g);
		}
		//paints my enemie's bullets
		for(int i = 0; i < Raiden.eB.size(); i++)
		{
			if(Raiden.eB.get(i).isVisible())
			{
				Raiden.eB.get(i).paintBullet(g);
			}
		}
		paintHUD(g);
	}
	
	/**
	 * Pinta o Menu de Pausa.
	 *
 */
	void paintPauseMenu(Graphics g)
	{
		paintGame(g);
		Raiden.pauseMenu.paintPauseMenu(g);
	}
	
	/**
	 * Pinta o Menu de Ajuda.
	 */
	void paintHelpMenu(Graphics g)
	{
		Raiden.helpMenu.paintHelpMenu(g);
	}
	
	/**
	 * Pinta o Menu de Opcoes.
	 */
	void paintOptionsMenu(Graphics g)
	{
		Raiden.optionMenu.paintOptionMenu(g);
	}
	
	/**
	 * Pinta varias informacoes sobre o estado do jogo.
	 */
	void paintDebug(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		
		Font font = new Font("Monospaced", Font.BOLD, 15);
		g.setFont(font);
		g.setColor(Color.RED);
		g2.drawString("DIFFICULTY", 100, Raiden.getBoardHeight()-5);
		if(Raiden.mt.getDifficultyLevel() == 0)
			g2.drawString("EASY", 200, Raiden.getBoardHeight()-5);
		else if(Raiden.mt.getDifficultyLevel() == 1)
			g2.drawString("NORMAL", 200, Raiden.getBoardHeight()-5);
		else if(Raiden.mt.getDifficultyLevel() == 2)
			g2.drawString("HARD", 200, Raiden.getBoardHeight()-5);
		
		g2.drawString("GODMODE", 275, Raiden.getBoardHeight()-5);
		if(Raiden.optionMenu.godMode)
			g2.drawString("ON", 350, Raiden.getBoardHeight()-5);
		else if(!Raiden.optionMenu.godMode)
			g2.drawString("OFF", 350, Raiden.getBoardHeight()-5);
		
		g2.drawString("ENEMIES" + " " + Raiden.enemies.size() + "/" + Raiden.mt.getEnemyLimit(),
									275, Raiden.getBoardHeight()-25);
		
	}


	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(Raiden.state == Raiden.States.Menu)
		{
			Raiden.menu.paintMenu(g);
		}
		else if(Raiden.state == Raiden.States.Game)
		{
			paintGame(g);
		}
		else if(Raiden.state==Raiden.States.Pause)
		{
			paintPauseMenu(g);
		}
		else if(Raiden.state==Raiden.States.Help)
		{
			paintHelpMenu(g);
		}
		else if(Raiden.state==Raiden.States.Options)
		{
			paintOptionsMenu(g);
		}
		else if(Raiden.state==Raiden.States.GameOver)
		{
			Raiden.gameOverScreen.paintGameOverScreen(g);
		}
		else if(Raiden.state==Raiden.States.ShowScores)
		{
			Raiden.showScores.paintShowScores(g);
		}
		if(Raiden.optionMenu.debug)
			paintDebug(g);
		
		g.dispose();
	}
}

	