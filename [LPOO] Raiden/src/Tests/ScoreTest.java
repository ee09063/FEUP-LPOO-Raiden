package Tests;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Logic.Score;

public class ScoreTest {

	private Score score;

	private String filename;
	
	@Before
	public void run()
	{
		score = new Score(0,0);
		score.scr.clear();
		filename = "scoreTest.txt";
		resetFile(filename);
	}
	
	/**
	 * Reset file.
	 */
	public void resetFile(String filename)
	{
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(filename, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String newline = System.getProperty("line.separator");
		writer.print("AA 500"); writer.print(newline);
		writer.print("BB 400");
		writer.close();
	}
	
	/**
	 * Testa se o ficheiro e correctamente carregado para um ArrayList.
	 */
	@Test
	public void loadScore()
	{
		ArrayList<String> scoreA = new ArrayList<>();
		scoreA = score.loadToArray(filename);
		assertEquals(2, scoreA.size());
		assertEquals("AA 500", scoreA.get(0));
		assertEquals("BB 400", scoreA.get(1));
	}
	
	/**
	 * Update score.
	 */
	@Test
	public void updateScore()
	{
		score.setScore(300);
		score.updateScoreFile("CC", filename);
		
		ArrayList<String> scoreA = new ArrayList<>();
		scoreA = score.loadToArray(filename);
		
		assertEquals(3, scoreA.size());
		assertEquals("AA 500", scoreA.get(0));
		assertEquals("BB 400", scoreA.get(1));
		assertEquals("CC 300", scoreA.get(2));
	}

}
