package Controller;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Logic.Craft;
import Logic.Raiden;

public class Controller implements KeyListener{
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			Raiden.interruptThreads();
			System.exit(0);
		}
		else if(Raiden.state == Raiden.States.Game)
		{
			keyPressedGame(e);
		}
		else if(Raiden.state==Raiden.States.GameOver)
		{
			if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE)
			{
				String str = Raiden.gameOverScreen.getPlayerName();
				str = str.substring(0, str.length()-1);
				Raiden.gameOverScreen.setPlayerName(str);
			}
			else if(Raiden.gameOverScreen.getPlayerName().length() < 5)
			{
				String c = ""+e.getKeyChar();
				c = c.toUpperCase();
				Raiden.gameOverScreen.addCharToString(c);
			}
		}
		
	}
	
	/**
	 * Gere os inputs durante enquanto ecra de jogo.
	 */
	void keyPressedGame(KeyEvent e)
	{
		Craft craft = Raiden.craft;
		if(e.getKeyCode() == KeyEvent.VK_A){//movimento para esquera
			craft.setPlayerMoveLeft(true);
		}
		else if(e.getKeyCode() == KeyEvent.VK_D){//movimento para direita
			craft.setPlayerMoveRight(true);
		}
		else if(e.getKeyCode() == KeyEvent.VK_W){//movimento para cima
			craft.setPlayerMoveUp(true);
		}
		else if(e.getKeyCode() == KeyEvent.VK_S){//movimento para baixo
			craft.setPlayerMoveDown(true);
		}
		else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){//sai da aplicacao
			Raiden.interruptThreads();
			System.exit(0);
		}
		else if(e.getKeyCode()==KeyEvent.VK_P){//pausa o jogo
			Raiden.getBoard().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			Raiden.state = Raiden.States.Pause;
		}
	}
	
	
	@Override
	public void keyReleased(KeyEvent e) {
		
		if(Raiden.state == Raiden.States.Game)
		{
			keyReleasedGame(e);
		}
	}
	
	
	void keyReleasedGame(KeyEvent e)
	{
		Craft craft = Raiden.craft;
		if(e.getKeyCode() == KeyEvent.VK_A){
			craft.setPlayerMoveLeft(false);
		}
		else if(e.getKeyCode() == KeyEvent.VK_D){
			craft.setPlayerMoveRight(false);
		}
		else if(e.getKeyCode() == KeyEvent.VK_W){
			craft.setPlayerMoveUp(false);
		}
		else if(e.getKeyCode() == KeyEvent.VK_S){
			craft.setPlayerMoveDown(false);
		}
	}
	
	
	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	

}
