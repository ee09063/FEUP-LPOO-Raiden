package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import GUI.OptionMenu;
import Logic.ActorSprites;
import Logic.BulletSprites;
import Logic.Craft;
import Logic.Raiden;

public class CraftTest {

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
	 *  Testa se a nave e correctamente criada.
	 */
	@Test
	public void testCreateCraft()
	{
		Craft craft = new Craft(300, 300, 5);
		assertEquals(craft.getX(), 300);
		assertEquals(craft.getY(), 300);
		assertEquals(craft.getSpeed(), 5);
	}
	
	/**
	 * Testa se a nave se move para cima.
	 */
	@Test
	public void testMoveCraftUp()
	{
		Craft c = new Craft(300, 300, 5);
		c.moveCraft();
		assertEquals(c.getX(), 300); assertEquals(c.getY(), 300);
		c.setPlayerMoveUp(true);
		c.moveCraft();
		assertEquals(c.getX(), 300); assertEquals(c.getY(), 295);
	}
	
	/**
	 * Testa se a nave se move para baixo.
	 */
	@Test
	public void testMoveCraftDown()
	{
		Craft c = new Craft(300, 300, 5);
		c.moveCraft();
		assertEquals(c.getX(), 300); assertEquals(c.getY(), 300);
		c.setPlayerMoveDown(true);
		c.moveCraft();
		assertEquals(c.getX(), 300); assertEquals(c.getY(), 305);
	}
	
	/**
	 * Testa se a nave se move para a esquerda.
	 */
	@Test
	public void testMoveCraftLeft()
	{
		Craft c = new Craft(300, 300, 5);
		c.moveCraft();
		assertEquals(c.getX(), 300); assertEquals(c.getY(), 300);
		c.setPlayerMoveLeft(true);
		c.moveCraft();
		assertEquals(c.getX(), 295); assertEquals(c.getY(), 300);
	}
	
	/**
	 * Testa se a nave se move para a direita.
	 */
	@Test
	public void testMoveCraftRight()
	{
		Craft c = new Craft(300, 300, 5);
		c.moveCraft();
		assertEquals(c.getX(), 300); assertEquals(c.getY(), 300);
		c.setPlayerMoveRight(true);
		c.moveCraft();
		assertEquals(c.getX(), 305); assertEquals(c.getY(), 300);
	}
	
	/**
	 * Testa se a nave dispara uma bala correctamente.
	 */
	@Test
	public void testCraftShoot()
	{
		Craft c = new Craft(0,0,0);
		c.generateBullet();
		assertEquals(c.bullets.size(), 1);
	}
}
