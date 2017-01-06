package Logic;
import java.awt.Cursor;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Controller.Mouse;
import GUI.Menus;

// TODO: Auto-generated Javadoc
/**
 * The Class MainTh.
 */
public class MainTh implements Runnable
{
	
	/** The ship damage. */
	private static int shipDamage;

	/** The bullet damage. */
	private static int bulletDamage;

	/** The spawn limit. */
	private int spawnLimit;

	/** The difficulty level. */
	private int difficultyLevel;

	/** The enemy limit. */
	private int enemyLimit;

	/** The frames per second. */
	private int FRAMES_PER_SECOND = 150;

	/** The target time. */
	private int targetTime = 1000 / FRAMES_PER_SECOND;
	
	/** The back timer. */
	private Timer spawnTimer,
	             // mainTimer,
	              backTimer;
	
	/** The to spawn. */
	private boolean toSpawn;

	/** The play game over music. */
	boolean playGameOverMusic;
	
	/**
	 * Instantiates a new main th.
	 */
	public MainTh()
	{
		int d = 0;
		setDifficultyLevel(d);
		setSpawnLimitD(d);
		setEnemyLimitD(d);
		shipDamage = 10;
		bulletDamage = 10;
		
		playGameOverMusic=true;
		
		spawnTimer = new Timer(true);
		spawnTimer.schedule(new SpawnTask(), 0 , spawnLimit*500);
		/*
		mainTimer = new Timer(true);
		mainTimer.scheduleAtFixedRate(new MainTask(), 0, targetTime);*/
		
		backTimer = new Timer(true);
		backTimer.schedule(new BackTask(), 0, 5);
	}
	/**
	 * Indica que um inimigo tem de ser criado.
	 */
	class SpawnTask extends TimerTask
	{
		
		/* (non-Javadoc)
		 * @see java.util.TimerTask#run()
		 */
		public void run()
		{
			if(Raiden.enemies.size() < getEnemyLimit())
				toSpawn = true;
		}
	}
	/*
	class MainTask extends TimerTask
	{
		public void run()
		{
			gameUpdate();
			Raiden.getBoard().repaint();
		}
	}
	*/
	/**
	 * Responsavel pelo movimento do background.
	 *
	 */
	class BackTask extends TimerTask
	{
		
		/* (non-Javadoc)
		 * @see java.util.TimerTask#run()
		 */
		public void run()
		{
			Menus.bgMenu0.move();
			Menus.bgMenu1.move();
		}
	}
	/**
	 * Update do jogo.
	 */
	public void gameUpdate()
	{
		if(Raiden.state == Raiden.States.Game)
		{
			mainThreadGame();
		}
		if(Raiden.craft.getLife() <= 0)
		{
			Raiden.gameOver = true;
			playGameOverMusic();
			Raiden.state=Raiden.States.GameOver;
		}
	}
	/**
	 * Quando em game over, da ordem para tocar a music, uma vez.
	 */
	public void playGameOverMusic()
	{
		if(playGameOverMusic)
		{
			playGameOverMusic = false;
			Raiden.ost.stopGame();
			Raiden.ost.loopGameOver();
		}
	}
	/**
	 * Thread Principal.
	 */
	public void run()
	{
	   Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
	
	   while( true )
	   {   
		   long beginning = System.currentTimeMillis();
		   gameUpdate();
		   
		   Raiden.getBoard().repaint();
		   
		   long end = System.currentTimeMillis();
		   long delta = end - beginning;
		   long sleepTime = targetTime - delta;
	        if( sleepTime >= 0 ){
	        	try{
	    			Thread.sleep(sleepTime);
	        	  } catch (InterruptedException e) {
	    			//e.printStackTrace();
	        	  }
	        }
	    }	
	}
	/**
	 * Accao a tomar durante o menu de pausa.
	 */
	void mainThreadPauseMenu()
	{
		Raiden.getBoard().repaint();
	}
	/**
	 * Accoes a tomar enquanto no jogo.
	 */
	void mainThreadGame()
	{
		ArrayList<Enemy> e = Raiden.enemies;
		ArrayList<EnemyBullet> b = Raiden.eB;
		Craft craft = Raiden.craft;
		
		if(toSpawn)
		{
			toSpawn = false;
			spawnEnemy();
		}
		
		craft.update();
		
		//checks if craft shoots
		if(Mouse.getOrderToShoot())
		{
			Mouse.setOrderToShoot(false);
			Raiden.craft.generateBullet();
		}
		//moves enemies
		for(int i = 0; i < e.size(); i++)
		{
			e.get(i).update();
		}
		//moves my bullets
		for(int i = 0; i < craft.bullets.size(); i++)
		{
			craft.bullets.get(i).update();
		}
		//moves enemy bullets
		for(int i = 0; i < b.size(); i++)
		{
			b.get(i).update();
		}
		
		checkCollisions();
		cleanUp(craft);
	}
	
	/**
	 * Remove de jogo balas invisiveis e inimigos completamente destruidos.
	 *
	 * @param craft the craft
	 */
	public static void cleanUp(Craft craft)
	{
		for(int i = 0; i < Raiden.eB.size(); i++)
		{
			if(!Raiden.eB.get(i).isVisible())
			{
				if(Raiden.eB.get(i).getTimer()!=null)
				{
					Raiden.eB.get(i).getTimer().cancel();
					Raiden.eB.get(i).getTimer().purge();
				}
				Raiden.eB.remove(i);
			}
		}
		for(int i = 0; i < craft.bullets.size(); i++)
		{
			if(!craft.bullets.get(i).isVisible())
			{
				if(craft.bullets.get(i).getTimer()!=null)
				{
					craft.bullets.get(i).getTimer().cancel();
					craft.bullets.get(i).getTimer().purge();
				}
				craft.bullets.remove(i);
			}
		}
		for(int i = 0; i < Raiden.enemies.size(); i++)
		{
			if(Raiden.enemies.get(i).isDestroyed())
				Raiden.enemies.remove(i);
		}
	}
	/**
	 * Colisoes.
	 */
	public static void checkCollisions()
	{
		craftVsEnemy();
		bulletVsEnemy();
		bulletVsCraft();
	}
	/**
	 * Verifica colisoes entre naves.
	 */
	public static void craftVsEnemy()
	{
		Craft craft = Raiden.craft;
		ArrayList<Enemy> e = Raiden.enemies;
		Rectangle craftR = craft.getBounds();
		
		//collision between ships
		for(int i = 0; i < e.size(); i++)
		{
			Enemy e1 = e.get(i);
			Rectangle eR = e1.getBounds();
			if(e1.isAlive())
			{
				if(craftR.intersects(eR))
				{
					if(craft.getShield() >= 0)
						craft.setShield(craft.getShield()-shipDamage);
					else
						craft.setLife(craft.getLife()-shipDamage);
					e1.Kill();
					Raiden.score.update();
				}
			}
		}
	}
	/**
	 * Verifica colisoes entre a nave do jogador e as naves inimigas.
	 */
	public static void bulletVsEnemy()
	{
		Craft craft = Raiden.craft;
		ArrayList<Enemy> e = Raiden.enemies;
		
		//collision between ship bullets and enemies
		for(int i = 0; i < craft.bullets.size(); i++)
		{
			CraftBullet bl = craft.bullets.get(i);
			Rectangle bR = bl.getBounds();
			
			for(int j = 0; j < e.size(); j++)
			{
				Enemy em = e.get(j);
				Rectangle emR = em.getBounds();
				if(em.isAlive() && bR.intersects(emR))
				{
					if(em.getMode()!=bl.getMode())
					{
						em.Kill();
						Raiden.score.update();
					}
					craft.bullets.get(i).setVisible(false);	
				}
			}
		}
	}
	/**
	 * Verifica colisoes entre a nave e balas inimigas.
	 */
	public static void bulletVsCraft()
	{
		Craft craft = Raiden.craft;
		Rectangle craftR = craft.getBounds();
		ArrayList<EnemyBullet> eB = Raiden.eB;
		//collision between enemy bullets and craft
		for(int i = 0; i < eB.size(); i++)
		{
			EnemyBullet bl = eB.get(i);
			Rectangle bR = bl.getBounds();
			if(craftR.intersects(bR))
			{
				bl.setVisible(false);
				if(craft.getMode()!=bl.getMode())
				{
					if(craft.getShield() > 0)
					{
						craft.setShield(craft.getShield() - bulletDamage);
					}
					else
						craft.setLife(craft.getLife() - bulletDamage);
				}
			}
			if(craft.getLife() <= 0)
			{
				Raiden.gameOver = true;
				Raiden.getBoard().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}	
	}
	/**
	 * Modifica o dano das balas, segundo a dificuldade.
	 */
	public void setBulletShipDamage()
	{
		if(Raiden.optionMenu.godMode)
		{
			shipDamage = 0;
			bulletDamage = 0;
		}
		else if(!Raiden.optionMenu.godMode)
		{
			if(difficultyLevel == 0)
			{
				shipDamage = bulletDamage = 10;
			}
			else if(difficultyLevel == 1)
			{
				shipDamage = bulletDamage = 15;
			}
			else if(difficultyLevel == 2)
			{
				shipDamage = bulletDamage = 20;
			}
		}
	}
	/**
	 * Cria um novo inimigo.
	 */
	public void spawnEnemy()
	{
		Random random = new Random();
		
		int xPos = random.nextInt(Raiden.getBoardWidth()-40)+20;
		int mode = random.nextInt(2);
		int shotLimit = 100;
		int movement = random.nextInt(2);
		
		Enemy e = new Enemy(xPos,-50, Raiden.enemyCraftSpeed, shotLimit, mode, movement); 
		Raiden.enemies.add(e);		
	}
	
	/**
	 * Coloca um limite ao numero de inimigos que existem, segundo a dificuldade.
	 *
	 * @param d the new enemy limit d
	 */
	private void setEnemyLimitD(int d) {
		switch(difficultyLevel)
		{
			case 0:
			{
				setEnemyLimit(5);
				break;
			}
			case 1:
			{
				setEnemyLimit(8);
				break;
			}
			case 2:
			{
				setEnemyLimit(12);
				break;
			}
		}
	}
	
	/**
	 * Muda quao rapidamente um inimigo e criado, segundo a dificuldade.
	 *
	 * @param difficultyLevel the new spawn limit d
	 */
	public void setSpawnLimitD(int difficultyLevel) {
		switch(difficultyLevel)
		{
			case 0: //easy
			{
				setSpawnLimit(5);
				break;
			}
			case 1: //normal
			{
				setSpawnLimit(4);
				break;
			}
			case 2:
			{
				setSpawnLimit(3);
				break;
			}
		}
	}
	
	/**
	 * Muda a dificuldade.
	 *
	 * @param d the d
	 */
	public void changeDifficulty(int d)
	{
		setDifficultyLevel(d);
		setSpawnLimitD(d);
		setEnemyLimitD(d);
		spawnTimer.schedule(new SpawnTask(), 0, spawnLimit*500);
	}
	
	/**
	 * Sets the bullet damage.
	 *
	 * @param bulletDamage the new bullet damage
	 */
	public static void setBulletDamage(int bulletDamage)
	{
		MainTh.bulletDamage = bulletDamage;
	}
	
	/**
	 * Gets the bullet damage.
	 *
	 * @return the bullet damage
	 */
	public int getBulletDamage()
	{
		return MainTh.bulletDamage;
	}
	
	/**
	 * Sets the ship damage.
	 *
	 * @param shipDamage the new ship damage
	 */
	public static void setShipDamage(int shipDamage)
	{
		MainTh.shipDamage = shipDamage;
	}
	
	/**
	 * Gets the ship damage.
	 *
	 * @return the ship damage
	 */
	public int getShipDamage()
	{
		return MainTh.shipDamage;
	}
	
	/**
	 * Gets the difficulty level.
	 *
	 * @return the difficulty level
	 */
	public int getDifficultyLevel() {
		return difficultyLevel;
	}

	/**
	 * Sets the difficulty level.
	 *
	 * @param difficultyLevel the new difficulty level
	 */
	public void setDifficultyLevel(int difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	/**
	 * Gets the spawn limit.
	 *
	 * @return the spawn limit
	 */
	public int getSpawnLimit() {
		return spawnLimit;
	}

	/**
	 * Sets the spawn limit.
	 *
	 * @param spawnLimit the new spawn limit
	 */
	public void setSpawnLimit(int spawnLimit) {
		this.spawnLimit = spawnLimit;
	}

	/**
	 * Gets the enemy limit.
	 *
	 * @return the enemy limit
	 */
	public int getEnemyLimit() {
		return enemyLimit;
	}

	/**
	 * Sets the enemy limit.
	 *
	 * @param enemyLimit the new enemy limit
	 */
	public void setEnemyLimit(int enemyLimit) {
		this.enemyLimit = enemyLimit;
	}
}