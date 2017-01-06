package Logic;

// TODO: Auto-generated Javadoc
/**
 * The Class EnemyBullet.
 */
public class EnemyBullet extends Bullet
{
	
	/**
	 * Instantiates a new enemy bullet.
	 *
	 * @param x the x
	 * @param y the y
	 * @param mode the mode
	 * @param bulletSpeed the bullet speed
	 */
	public EnemyBullet(int x, int y, int mode, int bulletSpeed) {
		super(x, y, mode, bulletSpeed);
		setUpSprites();
	}
	/**
	 * Prepara os sprites.
	 */
	public void setUpSprites()
	{
		if(this.getMode()==0)
		{
			mySprites = BulletSprites.getEnemyBlue();
		}
		if(this.getMode()==1)
		{
			mySprites = BulletSprites.getEnemyRed();
		}
		sprite = mySprites.get(0);
		setImageWidth(sprite.getWidth());
		setImageHeight(sprite.getHeight());
	}
	/**
	 * Comportamento em cada update.
	 */
	public void update()
	{
		this.moveBullet();
		if(this.getY() >= Raiden.getBoardHeight())
			this.setVisible(false);
	}
}
