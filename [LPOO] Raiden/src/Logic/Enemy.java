package Logic;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


// TODO: Auto-generated Javadoc
/**
 * The Class Enemy.
 */
public class Enemy{
	
	/** The movement type. */
	private int x,
				y,
				speed,
				size = 30,
				imageHeight,
				imageWidth,
				bulletSpeed = -Raiden.bulletSpeedEnemy,
				spriteCounter,
				spriteCycle = 5,
				spriteIndex,
				drawSize,
				shotCounter,
				shotLimit,
				mode,
				movementType;
	
	/** The can shoot. */
	private boolean alive,
					destroyed,
					visible, 
					patternBetaDirection,
					canShoot;
	
	/** The sprite. */
	private BufferedImage sprite;
	
	/** The my sprites. */
	public ArrayList<BufferedImage> mySprites = new ArrayList<BufferedImage>();
	
	/** The sprite timer. */
	private Timer timer,
				  dTimer,
				  spriteTimer;
	
	/** The move. */
	private boolean move = true;
	
	/**
	 * Instantiates a new enemy.
	 *
	 * @param x the x
	 * @param y the y
	 * @param speed the speed
	 * @param shotLimit the shot limit
	 * @param mode the mode
	 * @param movementType the movement type
	 */
	public Enemy(int x, int y, int speed, int shotLimit, int mode, int movementType)
	{
		setX(x);
		setY(y);
		
		setSpeed(speed);
		setMode(mode);
		setAlive(true);
		setDestroyed(false);
		setVisible(true);
		setMovementType(movementType);
		
		setshotCounter(0);
		setShotLimit(shotLimit);
		
		getX();
		if(getX() > Raiden.getBoardWidth()/2)
			setPatternBetaDirection(false);
		else setPatternBetaDirection(true);
		
		drawSize = Raiden.drawSize;
		
		setUpSprites();
		
		spriteTimer = new Timer(true);
		timer = new Timer(true);
		dTimer = new Timer(true);
		timer.schedule(new RemindTask(), 0, 1000);
		spriteTimer.schedule(new SpriteTask(), 0, 150);
	}
	/**
	 * Percorre os sprites da nave, para animacao.
	 *
	 */
	class SpriteTask extends TimerTask
	{
		
		/* (non-Javadoc)
		 * @see java.util.TimerTask#run()
		 */
		public void run()
		{
			spriteIndex++;
			if(spriteIndex == mySprites.size())
				spriteIndex = 0;
			sprite = mySprites.get(spriteIndex);	
		}
	}
	/**
	 * Indica que a nave pode disparar. 
	 *
	 */
	class RemindTask extends TimerTask
	{
		
		/* (non-Javadoc)
		 * @see java.util.TimerTask#run()
		 */
		public void run()
		{
			if(isAlive())
				canShoot = true;
		}
	}
	/**
	 * Percorre os sprites da explosao, para a destruicao da nave.
	 *
	 */
	class DestroyShip extends TimerTask
	{
		
		/* (non-Javadoc)
		 * @see java.util.TimerTask#run()
		 */
		public void run()
		{
			if(spriteIndex < ActorSprites.ssExplosion.size())
				sprite = ActorSprites.ssExplosion.get(spriteIndex);
			spriteIndex++;
			if(spriteIndex == ActorSprites.ssExplosion.size())
			{
				destroyed = true;
			}
		}
	}
	/**
	 * Comportamento da nave em cada update.
	 */
	public void update()
	{
		if(isAlive())
		{
			if(canShoot)
			{
				canShoot = false;
				this.generateBullet();
			}
			this.moveShip();
			if(this.getY() >= Raiden.getBoardHeight())
				this.setY(this.getY()-Raiden.getBoardHeight()-20);
		}
	}
	/**
	 * "Mata" a nave. Esta nao dispara ou se move, mas explode.
	 */
	public void Kill()
	{
		alive = false;
		this.spriteTimer.purge();
		this.spriteTimer.cancel();
		dTimer.schedule(new DestroyShip(), 0, 100);
	}
	/**
	 * Prepara os sprites.
	 */
	public void setUpSprites()
	{	
		if(this.mode == 0)
		{
			mySprites = ActorSprites.getEnemyBlue();
			sprite = mySprites.get(0);
		}
		else if(this.mode==1)
		{
			mySprites = ActorSprites.getEnemyRed();
			sprite = mySprites.get(0);
		}
		setImageHeight(sprite.getHeight());
		setImageWidth(sprite.getWidth());
		spriteCounter = 0;
		spriteIndex = 0;
	}
	/**
	 * Movimenta a nave, segundo um de dois padroes.
	 */
	public void moveShip()
	{
		//this.move = !this.move;
		if(this.move)
		{
			switch(movementType)
			{
				case 0:
				{
					patternAlpha(getSpeed());
					break;
				}
				case 1:
				{
					patternBeta(getSpeed());
					break;
				}
			}
		}
	}
	
	/**
	 * Movimento vertical.
	 *
	 * @param speed the speed
	 */
	public void patternAlpha(int speed)
	{
		this.y = this.y + speed;
	}
	
	/**
	 * Movimento em Zig-Zag.
	 *
	 * @param speed the speed
	 */
	public void patternBeta(int speed)
	{
		this.y +=this.speed;
		if(isPatternBetaDirection())
		{
			this.x += this.speed;
		}
		else if(!isPatternBetaDirection())
		{
			this.x-=this.speed;
		}
		if(this.x == Raiden.getBoardWidth()/2-getImageWidth()/6
		|| this.x >= Raiden.getBoardWidth()-20
		|| this.x <= 20)
			setPatternBetaDirection(!isPatternBetaDirection());
		
	}
	
	/**
	 * Dispara uma bala.
	 */
	public void generateBullet()
	{
		Raiden.eB.add(new EnemyBullet(getX() + getImageWidth()/getDrawSize()/6, getY()+getImageHeight()/3-5, getMode(), getBulletSpeed()));
	}
	
	/**
	 * Pinta a nave.
	 *
	 * @param g the g
	 */
	public void paintEnemy(Graphics g)
	{
		g.drawImage(sprite,getX(), getY(), getImageWidth()/getDrawSize(), getImageHeight()/getDrawSize(), null);
		if(Raiden.optionMenu.debug)
			g.drawRect(this.getBounds().x, this.getBounds().y, this.getBounds().width, this.getBounds().height);
	}
	
	/**
	 * Devolve os limites da nave. Usado para verificar colisao.
	 *
	 * @return the bounds
	 */
	public Rectangle getBounds()
	{
		return new Rectangle(getX()+6, getY(), getImageWidth()/getDrawSize()-12, getImageHeight()/getDrawSize());
	}
	
	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX(){
		return this.x;
	}	
	
	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(int x){
		this.x = x;
	}
	
	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY(){
		return this.y;
	}
	
	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(int y){
		this.y = y;
	}
	
	/**
	 * Gets the speed.
	 *
	 * @return the speed
	 */
	public int getSpeed(){
		return this.speed;
	}
	
	/**
	 * Sets the speed.
	 *
	 * @param speed the new speed
	 */
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize(){
		return this.size;
	}
	
	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(int size){
		this.size = size;
	}

	/**
	 * Gets the shot counter.
	 *
	 * @return the shot counter
	 */
	public int getshotCounter() {
		return shotCounter;
	}

	/**
	 * Sets the shot counter.
	 *
	 * @param shotCounter the new shot counter
	 */
	public void setshotCounter(int shotCounter) {
		this.shotCounter = shotCounter;
	}

	/**
	 * Gets the image height.
	 *
	 * @return the image height
	 */
	public int getImageHeight() {
		return imageHeight;
	}

	/**
	 * Sets the image height.
	 *
	 * @param imageHeight the new image height
	 */
	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	/**
	 * Gets the image width.
	 *
	 * @return the image width
	 */
	public int getImageWidth() {
		return imageWidth;
	}

	/**
	 * Sets the image width.
	 *
	 * @param imageWidth the new image width
	 */
	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	/**
	 * Gets the mode.
	 *
	 * @return the mode
	 */
	public int getMode() {
		return mode;
	}

	/**
	 * Sets the mode.
	 *
	 * @param mode the new mode
	 */
	public void setMode(int mode) {
		this.mode = mode;
	}
	
	/**
	 * Sets the shot limit.
	 *
	 * @param shotLimit the new shot limit
	 */
	public void setShotLimit(int shotLimit){
		this.shotLimit = shotLimit;
	}
	
	/**
	 * Gets the shot limit.
	 *
	 * @return the shot limit
	 */
	public int getShotLimit(){
		return this.shotLimit;
	}

	/**
	 * Gets the bullet speed.
	 *
	 * @return the bullet speed
	 */
	public int getBulletSpeed() {
		return bulletSpeed;
	}

	/**
	 * Sets the bullet speed.
	 *
	 * @param bulletSpeed the new bullet speed
	 */
	public void setBulletSpeed(int bulletSpeed) {
		this.bulletSpeed = bulletSpeed;
	}

	/**
	 * Gets the sprite counter.
	 *
	 * @return the sprite counter
	 */
	public int getSpriteCounter() {
		return spriteCounter;
	}

	/**
	 * Sets the sprite counter.
	 *
	 * @param spriteCounter the new sprite counter
	 */
	public void setSpriteCounter(int spriteCounter) {
		this.spriteCounter = spriteCounter;
	}
	
	/**
	 * Gets the sprite cycle.
	 *
	 * @return the sprite cycle
	 */
	public int getSpriteCycle() {
		return spriteCycle;
	}

	/**
	 * Sets the sprite cycle.
	 *
	 * @param spriteCycle the new sprite cycle
	 */
	public void setSpriteCycle(int spriteCycle) {
		this.spriteCycle = spriteCycle;
	}

	/**
	 * Gets the sprite.
	 *
	 * @return the sprite
	 */
	public BufferedImage getSprite() {
		return sprite;
	}

	/**
	 * Sets the sprite.
	 *
	 * @param sprite the new sprite
	 */
	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

	/**
	 * Gets the draw size.
	 *
	 * @return the draw size
	 */
	public int getDrawSize() {
		return drawSize;
	}

	/**
	 * Sets the draw size.
	 *
	 * @param drawSize the new draw size
	 */
	public void setDrawSize(int drawSize) {
		this.drawSize = drawSize;
	}

	/**
	 * Checks if is alive.
	 *
	 * @return true, if is alive
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * Sets the alive.
	 *
	 * @param alive the new alive
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/**
	 * Checks if is destroyed.
	 *
	 * @return true, if is destroyed
	 */
	public boolean isDestroyed() {
		return destroyed;
	}

	/**
	 * Sets the destroyed.
	 *
	 * @param destroyed the new destroyed
	 */
	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	/**
	 * Checks if is visible.
	 *
	 * @return true, if is visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Sets the visible.
	 *
	 * @param visible the new visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * Gets the movement type.
	 *
	 * @return the movement type
	 */
	public int getMovementType() {
		return movementType;
	}

	/**
	 * Sets the movement type.
	 *
	 * @param movementType the new movement type
	 */
	public void setMovementType(int movementType) {
		this.movementType = movementType;
	}

	/**
	 * Checks if is pattern beta direction.
	 *
	 * @return true, if is pattern beta direction
	 */
	public boolean isPatternBetaDirection() {
		return patternBetaDirection;
	}

	/**
	 * Sets the pattern beta direction.
	 *
	 * @param patternBetaDirection the new pattern beta direction
	 */
	public void setPatternBetaDirection(boolean patternBetaDirection) {
		this.patternBetaDirection = patternBetaDirection;
	}
}
