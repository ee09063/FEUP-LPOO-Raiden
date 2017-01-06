package Logic;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class Score {
	
	private int score,
	            x,
	            y;
	
	private String file = "score.txt";
	
	private Font font;
	
	public ArrayList<String> scr = new ArrayList<String>();
	
	public Score(int x, int y){
		this.x = x;
		this.y = y;
		font = new Font("Monospaced", Font.BOLD, 15);
		scr = loadToArray(file);
	}
	
	/**
	 * Pinta a pontuacao.
	 *
	 */
	public void drawScore(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("SCORE " + score, x, y);
	}
	
	/**
	 * Actualiza a pontuacao.
	 */
	public void update(){
		score = score + 100;
	}
	
	/**
	 * Quando ocorre game over actualiza o ficheiro com as pontucoes.
	 *
	 */
	public  void updateScoreFile(String name, String filename)
	{
		scr.clear();
		scr = loadToArray(filename);
		insertScore(name);
		saveFile(filename);
	}
	
	/**
	 * Guarda o ArrayList no ficheiro.
	 */
	public  void saveFile(String filename){
		 PrintWriter writer = null;
		 try {
				writer = new PrintWriter(filename, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < scr.size(); i++)
		{
			if(i == scr.size()-1)
				writer.print(scr.get(i));
			else
				writer.println(scr.get(i));
		}
		writer.close();
	}
	
	/**
	 * Insere a pontucao no ArrayList.
	 */
	public  void insertScore(String name){
		for(int i = 0; i < scr.size(); i++)
		{
			String parts2[] = scr.get(i).split(" ");
			if(getScore() > Integer.parseInt(parts2[1]))
			{
				scr.add(i, name + " " + getScore());
				return;
			}
		}
		scr.add(name + " " + getScore());
		return;
	}
	
	/**
	 * Carrega as pontucoes do ficheiro para um ArrayList, para gestao mais simples.
	 */
	public ArrayList<String> loadToArray(String filename)
	{
		Scanner s = null;
		ArrayList<String> score = new ArrayList<String>();
		String str = "";
		try{
			try {
				s = new Scanner(new BufferedReader(new FileReader(new File(filename))));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			for(int i = 0; i < Lines(filename); i++)
			{
				str = s.nextLine();
				score.add(str);
			}
		}
		finally
		{
			if(s != null)
				s.close();
		}
		
		return score;
	}
	/**
	 * Imprime os scores.
	 */
	public  void printScores()
	{
		for(int i = 0; i < scr.size(); i++)
		{
			System.out.println(scr.get(i));
		}
	}
	
	/**
	 * Devolve o numero de linhas de um ficheiro.
	 *
	 */
	@SuppressWarnings("resource")
	public  int Lines(String filename){
		try {
			LineNumberReader lnr = new LineNumberReader(new FileReader(new File(filename)));
			try {
				lnr.skip(Long.MAX_VALUE);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return lnr.getLineNumber()+1;
		} catch (FileNotFoundException e) {	
		}
		return 0;
	}
	

	public  int getX() {
		return x;
	}

	public  void setX(int x) {
		this.x = x;
	}

	public  int getY() {
		return y;
	}

	public  void setY(int y) {
		this.y = y;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public  String getFile() {
		return this.file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	

}
