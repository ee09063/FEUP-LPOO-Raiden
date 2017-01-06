package Logic;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Controller.Mouse;

/**
 * Nave do jogador.
 */
public class Craft {
	
	private int x,
				y,
				speed,
				imageHeight,
				imageWidth,
				drawSize,
				spriteCycle = 1,
				spriteIndex,
				mode,
				shield,
				life,
				bulletRadius;
	
	public ArrayList<CraftBullet> bullets = new ArrayList<CraftBullet>();
	
	public ArrayList<BufferedImage> mySprites = new ArrayList<BufferedImage>();

	private boolean playerMoveUp,
					playerMoveDown,
					playerMoveRight,
					playerMoveLeft,
					playerShoot,
					playerChangeMode,
					visible;
	
	private BufferedImage sprite;
	
	private Timer timer;
	
	public Craft(int x, int y, int speed)
	{
		setX(x);
		setY(y);
		setSpeed(speed);
		setMode(0);
		setUpSprites();
		life = 100;
		shield = 100;
		bulletRadius = 10;
		drawSize = Raiden.drawSize;
		timer = new Timer(true);
		timer.schedule(new RemindTask(),0,spriteCycle*150);
	}
	/**
	 * Comportamento da nave sempre que faz update.
	 */
	public void update()
	{
		this.moveCraft();
	}
	
	/**
	 * Percorre os sprites da nave, para animacao.
	 */
	class RemindTask extends TimerTask
	{
		public void run()
		{
			spriteIndex++;
			if(spriteIndex == mySprites.size())
				spriteIndex = 0;
			sprite = mySprites.get(spriteIndex);	
		}
	}
	
	/**
	 * Prepara os sprites.
	 */
	public void setUpSprites()
	{
		spriteIndex = 0;
		mySprites = ActorSprites.getBlue();
		sprite = mySprites.get(spriteIndex);
		setImageHeight(sprite.getHeight());
		setImageWidth(sprite.getWidth());
	}
	
	/**
	 * Mudanca de forma.
	 */
	public void changeMode()
	{
		if(this.getMode() == 0)
		{
			this.setMode(1);
			spriteIndex = 0;
			mySprites = ActorSprites.getRed();
		}
		else if(this.getMode() == 1)
		{
			this.setMode(0);
			spriteIndex = 0;
			mySprites = ActorSprites.getBlue();
		}
		sprite = mySprites.get(spriteIndex);
		setImageHeight(sprite.getHeight());
		setImageWidth(sprite.getWidth());
		Mouse.canChange = true;
	}
	
	/**
	 * Move craft horizontal.
	 *
	 */
	public void moveCraftHorizontal(int speed)
	{
		setX(getX() + speed);
	}
	
	/**
	 * Move craft vertical.
	 *
	 */
	public void moveCraftVertical(int speed)
	{
		setY(getY() + speed);
	}
	/**
	 * Verifica em que direccao se move.
	 */
	public void moveCraft()
	{
		if(playerMoveLeft)
			moveCraftHorizontal(-getSpeed());
		if(playerMoveRight)
			moveCraftHorizontal(getSpeed());
		if(playerMoveUp)
			moveCraftVertical(-getSpeed());
		if(playerMoveDown)
			moveCraftVertical(getSpeed());
	}
	
	/**
	 * Dispara uma bala.
	 */
	public void generateBullet(){
		bullets.add(new CraftBullet(getX()-bulletRadius/2 + getImageWidth()/getDrawSize()/2,
				                    getY(),
				                    getMode(),
				                    Raiden.bulletSpeedCraft));
	}
	
	/**
	 * Pinta a nave.
	 *
	 */
	public void paintCraft(Graphics g)
	{
		g.drawImage(sprite,getX(), getY(), getImageWidth()/getDrawSize(), getImageHeight()/getDrawSize(), null);
		if(Raiden.optionMenu.debug)
			g.drawRect(getX()+10, getY(), getImageWidth()/getDrawSize()-20, getImageHeight()/getDrawSize());
	}
	
	/**
	 * Retorna a area usada para verificacao de colisao.
	 *
	 */
	public Rectangle getBounds()
	{
		return new Rectangle(getX()+10, getY(), getImageWidth()/getDrawSize()-20, getImageHeight()/getDrawSize());
	}
	
	/**
	 * Pinta a barra de vida.
	 */
	public void paintLifeBar(Graphics g)
	{
		Graphics2D g2 = ( Graphics2D )g;
		g2.setColor(Color.WHITE);
		g2.fillRect(8, Raiden.getBoardHeight()-125-2, 14, 104);
		g2.setColor(Color.GREEN);
		g2.fillRect(10, Raiden.getBoardHeight()-125, 10, life);
	}
	
	/**
	 * Pinta a barra de escudos.
	 */
	public void paintShields(Graphics g)
	{
		Graphics2D g2 = ( Graphics2D )g;
		g2.setColor(Color.WHITE);
		g2.fillRect(8, Raiden.getBoardHeight()-230-2, 14, 104);
		g2.setColor(Color.CYAN);
		g2.fillRect(10, Raiden.getBoardHeight()-230, 10, shield);
	}
	
	/**
	 * Gets the sprite.
	 *
	 */
	public BufferedImage getSprite()
	{
		return sprite;
	}
	
	public int getX(){
		return this.x;
	}	
	
	public void setX(int x){
		this.x = x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getSpeed(){
		return this.speed;
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	public int getLife()
	{
		return this.life;
	}
	
	public void setLife(int l)
	{
		this.life = l;
	}

	public int getShield() {
		return shield;
	}

	public void setShield(int shield) {
		this.shield = shield;
	}

	public boolean getVisible() {
		return this.visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
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

	public int getDrawSize() {
		return drawSize;
	}

	public void setDrawSize(int drawSize) {
		this.drawSize = drawSize;
	}
	
	public Boolean getPlayerMoveUp(){
		return playerMoveUp;
	}
	
	public void setPlayerMoveUp(boolean playerMoveUp){
		this.playerMoveUp = playerMoveUp;
	}
	
	public Boolean getPlayerMoveDown(){
		return playerMoveDown;
	}
	
	public void setPlayerMoveDown(boolean playerMoveDown){
		this.playerMoveDown = playerMoveDown;
	}
	
	public Boolean getPlayerMoveLeft(){
		return playerMoveLeft;
	}
	
	public void setPlayerMoveLeft(boolean playerMoveLeft){
		this.playerMoveLeft = playerMoveLeft;
	}
	
	public Boolean getPlayerMoveRight(){
		return playerMoveRight;
	}
	
	public void setPlayerMoveRight(boolean playerMoveRight){
		this.playerMoveRight = playerMoveRight;
	}
	
	public boolean getPlayerShoot() {
		return playerShoot;
	}

	public void setPlayerShoot(boolean playerShoot) {
		this.playerShoot = playerShoot;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public boolean getPlayerChangeMode() {
		return playerChangeMode;
	}

	public void setPlayerChangeMode(boolean playerChangeMode) {
		this.playerChangeMode = playerChangeMode;
	}

	public int getSpriteIndex() {
		return spriteIndex;
	}

	public void setSpriteIndex(int spriteIndex) {
		this.spriteIndex = spriteIndex;
	}
}
