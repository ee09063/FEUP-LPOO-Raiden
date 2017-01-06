package Logic;
// TODO: Auto-generated Javadoc

/**
 * Bala da nave do jogador.
 */
public class CraftBullet extends Bullet
{
	
	/**
	 * Instantiates a new craft bullet.
	 *
	 * @param x the x
	 * @param y the y
	 * @param mode the mode
	 * @param bulletSpeed the bullet speed
	 */
	public CraftBullet(int x, int y, int mode, int bulletSpeed)
	{
		super(x, y, mode, bulletSpeed);
		setUpSprites();
	}
	
	/**
	 * Prepara os sprites tendo em conta a forma.
	 */
	public void setUpSprites()
	{
		if(mode == 0)
		{
			mySprites = BulletSprites.getBlue();
		}
		else if(mode == 1)
		{
			mySprites = BulletSprites.getRed();
		}
		sprite = mySprites.get(0);
		setImageHeight(sprite.getHeight());
		setImageWidth(sprite.getWidth());
	}
	
	/**
	 * Comportamento da bala em cada update.
	 */
	public void update()
	{
		this.moveBullet();
		if(this.getY()+this.getImageHeight()/this.getDrawSize() <= 0)
			this.setVisible(false);
	}
}
