package Controller;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import GUI.HelpMenu;
import GUI.MainMenu;
import Logic.Craft;
import Logic.Raiden;


public class Mouse implements MouseListener {

	private boolean canShoot = true;
	
	private static boolean orderToShoot = false;
	
	public static boolean canChange = true;

	@Override
	public void mousePressed(MouseEvent m)
	{
		if(Raiden.state == Raiden.States.Game)
		{
			mousePressedGame(m);
		}
		else if(Raiden.state == Raiden.States.Menu)
		{
			mousePressedMenu(m);
		}
		else if(Raiden.state==Raiden.States.Pause)
		{
			mousePressedPause(m);
		}
		else if(Raiden.state==Raiden.States.Help)
		{
			mousePressedHelp(m);
		}
		else if(Raiden.state==Raiden.States.Options)
		{
			mousePressedOptions(m);
		}
		else if(Raiden.state==Raiden.States.GameOver)
		{
			mousePressedScore(m);
		}
		else if(Raiden.state==Raiden.States.ShowScores)
		{
			mousePressedShowScores(m);
		}
	}
	
	/**
	 * Gere os inputs do rato enquanto no ecra que mostra as melhores pontuacoes.
	 */
	public void mousePressedShowScores(MouseEvent m)
	{
		int mx = m.getX(), my = m.getY();
		Rectangle click = new Rectangle(mx, my, 1, 1);
		if(Raiden.showScores.backButton.contains(click))
		{
			Raiden.state=Raiden.States.Options;
		}
	}
	
	/**
	 * Gere os inputs do rato enquanto no menu de opcoes.
	 */
	void mousePressedOptions(MouseEvent m)
	{
		int mx = m.getX(), my = m.getY();
		Rectangle click = new Rectangle(mx, my, 1, 1);
		if(Raiden.optionMenu.godModeButton.contains(click))
		{
			Raiden.optionMenu.godMode = !Raiden.optionMenu.godMode;
			Raiden.mt.setBulletShipDamage();
		}
		else if(Raiden.optionMenu.timerButton.contains(click))
		{
			Raiden.optionMenu.timerBullet = !Raiden.optionMenu.timerBullet;
		}
		else if(Raiden.optionMenu.bugButton.contains(click))
		{
			Raiden.optionMenu.debug = !Raiden.optionMenu.debug;
		}
		else if(Raiden.optionMenu.backButton.contains(click))
		{
			Raiden.state=Raiden.States.Menu;
		}
		else if(Raiden.optionMenu.easyButton.contains(click))
		{
			Raiden.optionMenu.easy = true;
			Raiden.optionMenu.normal = Raiden.optionMenu.hard = false;
			Raiden.mt.changeDifficulty(0);
		}
		else if(Raiden.optionMenu.normalButton.contains(click))
		{
			Raiden.optionMenu.normal = true;
			Raiden.optionMenu.easy = Raiden.optionMenu.hard = false;
			Raiden.mt.changeDifficulty(1);
		}
		else if(Raiden.optionMenu.hardButton.contains(click))
		{
			Raiden.optionMenu.hard = true;
			Raiden.optionMenu.easy = Raiden.optionMenu.normal = false;
			Raiden.mt.changeDifficulty(2);
		}
		else if(Raiden.optionMenu.showScoresButton.contains(click))
		{
			Raiden.state=Raiden.States.ShowScores;
		}	
	}
	
	/**
	 * Gere os inputs do rato enquanto no menu de Game Over.
	 */
	void mousePressedScore(MouseEvent m)
	{
		int mx = m.getX(), my = m.getY();
		Rectangle click = new Rectangle(mx, my, 1, 1);
		if(Raiden.gameOverScreen.getOkButton().contains(click))
		{
			String name = Raiden.gameOverScreen.getPlayerName();
			Raiden.score.updateScoreFile(name, Raiden.score.getFile());
			Raiden.resetButton();
			Raiden.state=Raiden.States.Menu;
		}
	}
	
	/**
	 * Gere os inputs do rato enquanto no menu de ajuda.
	 */
	void mousePressedHelp(MouseEvent m)
	{
		int mx = m.getX(), my = m.getY();
		Rectangle click = new Rectangle(mx, my, 1, 1);
		if(HelpMenu.getBackButton().contains(click))
		{
			Raiden.state=Raiden.States.Menu;
		}
	}
	
	/**
	 * Gere os inputs do rato durante o jogo.
	 */
	void mousePressedGame(MouseEvent m)
	{
		Craft craft = Raiden.craft;
		if(m.getButton() == 1)
		{
			if(canShoot){
				canShoot = false;
				setOrderToShoot(true);
			}
		}
		else if(m.getButton() == 3 && canChange)
		{
			canChange = false;
			//craft.setPlayerChangeMode(true);
			craft.changeMode();
		}
	}
	
	/**
	 * Gere os inputs do rato enquanto no menu principal.
	 */
	void mousePressedMenu(MouseEvent m)
	{
		int mx = m.getX(), my = m.getY();
		Rectangle click = new Rectangle(mx, my, 1, 1);
		if(MainMenu.playButton.contains(click))
		{
			BufferedImage cursor = new BufferedImage(16,16,BufferedImage.TYPE_INT_ARGB);
			Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursor, new Point(0,0), "blank Cursor");
			Raiden.getBoard().setCursor(blankCursor);
			Raiden.ost.stopMenu();
			Raiden.ost.loopGame();
			Raiden.state=Raiden.States.Game;
		}
		else if(MainMenu.helpButton.contains(click))
		{
			Raiden.state=Raiden.States.Help;
		}
		else if(MainMenu.exitButton.contains(click))
		{
			Raiden.interruptThreads();
			System.exit(0);
		}
		else if(MainMenu.optionsButton.contains(click))
		{
			Raiden.state=Raiden.States.Options;
		}
	}
	
	/**
	 * Gere os inputs do rato enquanto no menu de pausa.
	 */
	void mousePressedPause(MouseEvent m)
	{
		int mx = m.getX(), my = m.getY();
		Rectangle click = new Rectangle(mx, my, 1, 1);
		if(Raiden.pauseMenu.resumeButton.contains(click))
		{
			BufferedImage cursor = new BufferedImage(16,16,BufferedImage.TYPE_INT_ARGB);
			Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursor, new Point(0,0), "blank Cursor");
			Raiden.getBoard().setCursor(blankCursor);
			Raiden.state=Raiden.States.Game;
		}
		else if(Raiden.pauseMenu.exitButton.contains(click))
		{
			Raiden.interruptThreads();
			System.exit(0);
		}
		else if(Raiden.pauseMenu.backButton.contains(click))
		{
			Raiden.resetButton();
			Raiden.state=Raiden.States.Menu;
			Raiden.ost.stopGame();
			Raiden.ost.loopMenu();
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent m)
	{
		if(Raiden.state == Raiden.States.Game)
		{
			if(m.getButton() == 1)
			{
				canShoot = true;
			}
		}
	}
	
	public static boolean getOrderToShoot() {
		return orderToShoot;
	}

	public static void setOrderToShoot(boolean orderToShoot) {
		Mouse.orderToShoot = orderToShoot;
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent m) {
	}

	
	@Override
	public void mouseEntered(MouseEvent m) {
	}

	
	@Override
	public void mouseExited(MouseEvent m) {
	}

}
