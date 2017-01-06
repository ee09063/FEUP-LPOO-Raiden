package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import GUI.OptionMenu;
import Logic.ActorSprites;
import Logic.BulletSprites;
import Logic.Enemy;
import Logic.Raiden;

public class EnemyTest {
	
	@BeforeClass
	public static void setUp()
	{
		@SuppressWarnings("unused")
		BulletSprites bs = new BulletSprites();
		@SuppressWarnings("unused")
		ActorSprites as = new ActorSprites();
		Raiden.setBoardSize();
		Raiden.optionMenu = new OptionMenu();
	}
	
	
	/**
	 *  Testa se a um inimigo é correctamente criado.
	 */
	@Test
	public void testCreateEnemy()
	{
		Enemy e = new Enemy(300, 300, 5, 5, 0, 0);
		assertEquals(e.getX(), 300);
		assertEquals(e.getY(), 300);
		assertEquals(e.getSpeed(), 5);
		assertEquals(e.getShotLimit(), 5);
		assertEquals(e.getMode(), 0);
		assertEquals(e.getMovementType(), 0);
	}
	
	/**
	 * Testa se a um inimigo se move segundo o padrão A - movimento vertical em direcção ao jogador.
	 */
	@Test
	public void testMoveEnemyPatternA()
	{
		Enemy e = new Enemy(300, 300, 5, 5, 0, 0);
		e.moveShip();
		assertEquals(e.getX(), 300);
		assertEquals(e.getY(), 305); 
	}
	
	/**
	 * Testa se a um inimigo se move segundo o padrão B - movimento em zig-zag em direcção ao jogador.
	 */
	@Test
	public void testMoveEnemyPatternB()
	{
		Enemy e = new Enemy(300,300,5,5,0,1);
		e.setPatternBetaDirection(true);// add to X
		e.moveShip();
		assertEquals(e.getX(), 305); assertEquals(e.getY(), 305);
		e.setPatternBetaDirection(false);//subtract to X
		e.moveShip();
		assertEquals(e.getX(), 300); assertEquals(e.getY(), 310);
	}
	
	/**
	 * Testa se um inimigo dispara correctamente.
	 */
	@Test
	public void testCraftShoot()
	{
		Enemy e = new Enemy(300,300,5,5,0,1);
		e.generateBullet();
		assertEquals(Raiden.eB.size(), 1);
	}
}

