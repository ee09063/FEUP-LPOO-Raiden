package Logic;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Bullet {
	
	protected int x,
				  y,
				  spriteCounter,
				  speed,
				  spriteCycle = 1,
				  spriteIndex,
				  drawSize,
				  imageHeight,
				  imageWidth,
				  mode;
	
	protected BufferedImage sprite;

	protected ArrayList<BufferedImage> mySprites;
	
	private boolean visible;
	
	private final Timer timer;
	
	Bullet(int x, int y, int mode, int speed)
	{
		setX(x);
		setY(y);
		setMode(mode);
		setSpeed(speed);
		setVisible(true);
		spriteIndex = 0;
		spriteCounter = 0;
		drawSize = Raiden.drawSize;
		mySprites = new ArrayList<BufferedImage>();
		if(Raiden.optionMenu.timerBullet)
		{
			timer = new Timer(true);
			timer.schedule(new RemindTask(), 0, spriteCycle*150);	
		}
		else timer = null;
	}
	
	/**
	 * 	Percorre o vector com os sprites para animar as balas.
	 */
	class RemindTask extends TimerTask
	{
		public void run()
		{
			spriteIndex++;
			if(spriteIndex == mySprites.size())
				spriteIndex = 0;
			if(spriteIndex<mySprites.size())
				sprite = mySprites.get(spriteIndex);
		}
	}
	
	/**
	 * Movimenta a bala.
	 */
	public void moveBullet(){
		setY(getY() - speed);
	}
	
	/**
	 * Pinta a bala. Em modo de degug, pinta tambem o contorno.
	 *
	 */
	public void paintBullet(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(sprite, this.getBounds().x, this.getBounds().y, this.getBounds().width, this.getBounds().height,null);
		if(Raiden.optionMenu.debug)
			this.paintCountour(g);
	}
	
	/**
	 * Devolve os limites da bala. Usado para verificar colisoes.
	 
	 */
	public Rectangle getBounds()
	{
		return new Rectangle(getX(), getY(), getImageWidth()/(getDrawSize()-1), getImageHeight()/(getDrawSize()-1));
	}
	
	/**
	 * Pinta a area de impacto da bala.
	 */
	public void paintCountour(Graphics g)
	{ 
		g.drawRect(this.getBounds().x, this.getBounds().y, this.getBounds().width, this.getBounds().height);
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}

	public int getSpriteCounter() {
		return spriteCounter;
	}

	public void setSpriteCounter(int spriteCounter) {
		this.spriteCounter = spriteCounter;
	}

	public int getSpriteCycle() {
		return spriteCycle;
	}

	public void setSpriteCycle(int spriteCycle) {
		this.spriteCycle = spriteCycle;
	}

	public int getDrawSize() {
		return drawSize;
	}

	public void setDrawSize(int drawSize) {
		this.drawSize = drawSize;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}
	
	public int getSC() {
		return spriteCounter;
	}
	
	public void setSC(int spriteCounter) {
		this.spriteCounter = spriteCounter;
	}
	
	public int getImageHeight() {
		return imageHeight;
	}
	
	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}
	
	public int getMode() {
		return mode;
	}

	public void setMode(int i) {
		this.mode = i;
	}
	
	public int getSpeed(){
		return this.speed;
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getSpriteIndex() {
		return spriteIndex;
	}

	public void setSpriteIndex(int spriteIndex) {
		this.spriteIndex = spriteIndex;
	}

	public Timer getTimer() {
		return timer;
	}
}
