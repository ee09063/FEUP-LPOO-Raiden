package Logic;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import Controller.Controller;
import Controller.Mouse;
import GUI.GameOverScreen;
import GUI.HelpMenu;
import GUI.MainMenu;
import GUI.OptionMenu;
import GUI.PauseMenu;
import GUI.ShowScores;
import GUI.myPanel;

/**
 *
 * Class responsible for the creation of the Graphic Player Interface.
 * Running this class will allow the player to play using the GUI.
 */
public class Raiden{

	private static JFrame frame;

	private static myPanel board;
	
	public static boolean gameOver = false;
	
	public static Dimension screenSize;
	
	private static int boardHeight,
					   boardWidth;
	
	public static int craftSpeed = 4,
					  bulletSpeedCraft = 3,
					  bulletSpeedEnemy = 2,
					  backSpeed = 6,
					  drawSize = 4,
					  enemyCraftSpeed = 1;
	
	protected static Raiden window;
	public static MainMenu menu;
	public static PauseMenu pauseMenu;
	public static HelpMenu helpMenu;
	public static OptionMenu optionMenu;
	public static Score score;
	public static GameOverScreen gameOverScreen;
	public static ShowScores showScores;
	public static Sound ost;
	
	public static Craft craft;
	public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public static ArrayList<EnemyBullet> eB = new ArrayList<EnemyBullet>();
	public static MainTh mt;
	
	public static Thread mainThread;
	
	public enum States
	{
		Menu,
		Game,
		Help,
		Pause,
		GameOver,
		ShowScores,
		Options;
	}

	public static States state = States.Menu;
	
	private static WindowAdapter closeWindow = new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
			interruptThreads();
			System.exit(0);
        }
    };
    
	/**
	 * Lanca a aplicacao
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Raiden();
					Raiden.getFrame().pack();
					Raiden.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Raiden() {
		initialize();
	}
	

	private void initialize() {
		
		setBoardSize();
		
		setFrame(new JFrame("RAIDEN"));
		getFrame().setBounds(0, 0, boardWidth, getBoardHeight());
		getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getFrame().getContentPane().setLayout(new BorderLayout(0, 0));
		getFrame().setBackground(Color.BLACK);
		
		//prepare actors and such
		@SuppressWarnings("unused")
		BulletSprites bs = new BulletSprites();
		@SuppressWarnings("unused")
		ActorSprites as = new ActorSprites();
		
		//prepare Menus and such
		menu = new MainMenu();
		pauseMenu = new PauseMenu();
		helpMenu = new HelpMenu();
		optionMenu = new OptionMenu();
		score = new Score(10, getBoardHeight()-5);
		gameOverScreen = new GameOverScreen();
		showScores = new ShowScores();
		ost = new Sound();
		ost.loopMenu();
				
		craft = new Craft(boardWidth/2, getBoardHeight() - 125, craftSpeed);
		craft.setX(getBoardWidth()/2-craft.getImageWidth()/craft.getDrawSize()/2);
		
		Controller ctrl = new Controller();
		Mouse mouse = new Mouse();
		
		setBoard(new myPanel());
		getBoard().setFocusable(true);
		getBoard().addKeyListener(ctrl);
		getBoard().addMouseListener(mouse);
		getBoard().requestFocusInWindow();
		getFrame().addWindowListener(closeWindow);
		getFrame().getContentPane().add(getBoard(), BorderLayout.CENTER);
		prepareThreads();
	}
	/**
	 * Verifica qual o tamanho do jogo tendo em conta o tamanho do ecra do PC.
	 */
	public static void setBoardSize()
	{
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();	
		boardHeight = (int) (screenSize.getHeight()-80);
		boardWidth = (int) (screenSize.getWidth()/3);
	}
	/**
	 * Limpa o jogo.
	 */
	public static void resetButton()
	{
		craft = new Craft(boardWidth/2, getBoardHeight() - 125, craftSpeed);
		craft.setX(getBoardWidth()/2-craft.getImageWidth()/craft.getDrawSize()/2);
		enemies.clear();
		eB.clear();
		score.setScore(0);
		gameOverScreen.setPlayerName("");
		mt.playGameOverMusic = true;
	}
	/**
	 * Prepara os threads.
	 */
	public void prepareThreads()
	{
		mt = new MainTh();
		mainThread = new Thread(mt);
		mainThread.start();
	}
	
	/**
	 * Interrompe Threads.
	 */
	public static void interruptThreads()
	{
		mainThread.interrupt();
	}

	public static int getBoardHeight() {
		return boardHeight;
	}

	public static void setBoardHeight(int boardHeight) {
		Raiden.boardHeight = boardHeight;
	}

	public static int getBoardWidth() {
		return boardWidth;
	}

	public static void setBoardWidth(int boardWidth) {
		Raiden.boardWidth = boardWidth;
	}

	public static myPanel getBoard() {
		return board;
	}

	public static void setBoard(myPanel board) {
		Raiden.board = board;
	}

	public static JFrame getFrame() {
		return frame;
	}

	public static void setFrame(JFrame frame) {
		Raiden.frame = frame;
	}
}