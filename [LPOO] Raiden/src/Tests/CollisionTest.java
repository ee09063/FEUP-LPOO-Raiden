package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Logic.ActorSprites;
import Logic.BulletSprites;
import Logic.Craft;
import Logic.CraftBullet;
import Logic.Enemy;
import Logic.EnemyBullet;
import Logic.MainTh;
import Logic.Raiden;
import Logic.Score;


public class CollisionTest {

	@BeforeClass
	public static void setUp()
	{
		Raiden.setBoardSize();
		@SuppressWarnings("unused")
		BulletSprites bs = new BulletSprites();
		@SuppressWarnings("unused")
		ActorSprites as = new ActorSprites();
		Raiden.score = new Score(0,0);
		Raiden.craft = new Craft(300, 300, 5);
	}
	
	@Before
	public void run()
	{
		Raiden.enemies.clear();
		Raiden.eB.clear();
		Raiden.craft.setLife(100);
		Raiden.craft.setShield(100);
	}
	/**
	 * Testa a colisão entre a nava do jogador e uma nave inimiga.
	 * A nave inimiga é destruída e a nave do jogador perde vida ou escudos
	 */
	@Test
	public void collisionShipsDifMode()
	{
		Craft c = Raiden.craft;
		Enemy e = new Enemy(300, 150, 5, 5, 1, 0);
		Raiden.enemies.add(e);
		MainTh.setShipDamage(20);
		c.setPlayerMoveUp(true);
		
		MainTh.checkCollisions();
		assertEquals(100, c.getLife());
		assertEquals(100, c.getShield());
		
		int k = (c.getY()-e.getY())/e.getSpeed();
		
		for(int i = 0; i < k; i++)
		{
			e.moveShip();
		}
		
		MainTh.checkCollisions();
		
		assertFalse(e.isAlive());
		assertEquals(80, c.getShield());
	}
	
	/**
	 * Collision ships same mode.
	 */
	@Test
	public void collisionShipsSameMode()
	{
		Craft c = Raiden.craft;
		Enemy e = new Enemy(300, 150, 5, 5, 0, 0);
		Raiden.enemies.add(e);
		MainTh.setShipDamage(20);
		c.setPlayerMoveUp(true);
		
		MainTh.checkCollisions();
		assertEquals(100, c.getLife());
		assertEquals(100, c.getShield());
		
		int k = (c.getY()-e.getY())/e.getSpeed();
		
		for(int i = 0; i < k; i++)
		{
			e.moveShip();
		}
		
		MainTh.checkCollisions();
		
		assertFalse(e.isAlive());
		assertEquals(80, c.getShield());
	}
	
	/**
	 * Testa a colisão entre uma bala do jogador e uma nave inimiga
	 * Após contacto a bala torna-se invisível e o inimigo explode
	 * A bala é removida pela função cleanUp().
	 */
	@Test
	public void collisionBulletEnemyDifMode()
	{
		Enemy e = new Enemy(300, 150, 5, 5, 1, 0);
		Raiden.enemies.add(e);
		Craft c = Raiden.craft;
		c.generateBullet();
		CraftBullet b = c.bullets.get(0);
		
		for(int i = 0; i < (c.getY()-e.getY())/b.getSpeed(); i++)
			b.moveBullet();
		
		MainTh.checkCollisions();
		assertFalse(b.isVisible());
		assertFalse(e.isAlive());
		
		MainTh.cleanUp(c);
		assertEquals(0, c.bullets.size());
	}
	
	/**
	 *Testa a colisão entre uma bala do jogador e uma nave inimiga com modos diferentes.
	 * Após contacto a bala torna-se invisível e o inimigo nao explode
	 * A bala é removida pela função cleanUp().
	 */
	@Test
	public void collisionBulletEnemySameMode()
	{
		Enemy e = new Enemy(300, 150, 5, 5, 0, 0);
		Raiden.enemies.add(e);
		Craft c = Raiden.craft;
		c.generateBullet();
		CraftBullet b = c.bullets.get(0);
		
		for(int i = 0; i < (c.getY()-e.getY())/b.getSpeed(); i++)
			b.moveBullet();
		
		MainTh.checkCollisions();
		assertFalse(b.isVisible());
		assertFalse(!e.isAlive());
		
		MainTh.cleanUp(c);
		assertEquals(0, c.bullets.size());
		assertEquals(1, Raiden.enemies.size());
	}
	
	/**
	 * Testa a colisão entre uma bala do inimigo e a nava do jogado
	 * Após contacto a bala torna-se invisível e o jogador nao perde vida ou escudos
	 * A bala é removida pela função cleanUp().
	 */
	@Test
	public void collisionBulletCraftSameMode()
	{
		Enemy e = new Enemy(300, 150, 5, 5, 0, 0);
		Craft c = Raiden.craft;
		MainTh.setBulletDamage(10);
		Raiden.enemies.add(e);
		e.generateBullet();
		EnemyBullet b = Raiden.eB.get(0);
		
		for(int i = 0; i < (c.getY()-e.getY())/(-b.getSpeed()); i++)
			b.moveBullet();
		
		MainTh.checkCollisions();
		
		assertFalse(b.isVisible());
		assertEquals(100, c.getShield());
		assertEquals(100, c.getLife());
		
		MainTh.cleanUp(c);
		assertEquals(0, Raiden.eB.size());
	}
	
	/**
	 * Testa a colisão entre uma bala do inimigo e a nava do jogador com modos diferentes
	 * Após contacto a bala torna-se invisível e o jogador perde vida ou escudos
	 * A bala é removida pela função cleanUp().
	 */
	@Test
	public void collisionBulletCraftDifMode()
	{
		Enemy e = new Enemy(300, 150, 5, 5, 1, 0);
		Craft c = Raiden.craft;
		MainTh.setBulletDamage(10);
		Raiden.enemies.add(e);
		e.generateBullet();
		EnemyBullet b = Raiden.eB.get(0);
		
		for(int i = 0; i < (c.getY()-e.getY())/(-b.getSpeed()); i++)
			b.moveBullet();
		
		MainTh.checkCollisions();
		
		assertFalse(b.isVisible());
		assertEquals(90, c.getShield());
		assertEquals(100, c.getLife());
		
		MainTh.cleanUp(c);
		assertEquals(0, Raiden.eB.size());
	}
	
}
