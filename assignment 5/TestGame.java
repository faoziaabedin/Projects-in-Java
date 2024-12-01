/* Autograder Test Cases (Game) for CS1027B Summer 2024 Assignment # 1 (Scrabble)*/

import java.util.ArrayList;
import java.util.Arrays;

import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestGame {
	
	public static void main(String[] args) throws Exception{
		
		//All required variables to store tests' results if you want to do that
		/**************************************************/
		BufferedWriter writer = new BufferedWriter(new FileWriter("tests_results_game.txt"));
		File myObj=null;
		Scanner scanRead=null;
		/**************************************************/
		
		Scrabble game1, game2, game3, game4, game5;
		
		Tile[] gameTwoTiles = new Tile[7];
		Tile[] gameThreeTiles = new Tile[7];
		Tile[] gameFourTiles = new Tile[7];
		Tile[] gameFiveTiles = new Tile[7];
		
		gameTwoTiles[0] = new Tile('A');
		gameTwoTiles[1] = new Tile('Q');
		gameTwoTiles[2] = new Tile('C');
		gameTwoTiles[3] = new Tile('D');
		gameTwoTiles[4] = new Tile('E');
		gameTwoTiles[5] = new Tile('X');
		gameTwoTiles[6] = new Tile('G');
		
		gameThreeTiles[0] = new Tile('Z');
		gameThreeTiles[1] = new Tile('W');
		gameThreeTiles[2] = new Tile('X');
		gameThreeTiles[3] = new Tile('T');
		gameThreeTiles[4] = new Tile('Q');
		gameThreeTiles[5] = new Tile('O');
		gameThreeTiles[6] = new Tile('O');
		
		gameFourTiles[0] = new Tile('Z');
		gameFourTiles[1] = new Tile('W');
		gameFourTiles[2] = new Tile('T');
		gameFourTiles[3] = new Tile('X');
		gameFourTiles[4] = new Tile('Q');
		gameFourTiles[5] = new Tile('O');
		gameFourTiles[6] = new Tile('O');
		
		gameFiveTiles[0] = new Tile('C');
		gameFiveTiles[1] = new Tile('R');
		gameFiveTiles[2] = new Tile('R');
		gameFiveTiles[3] = new Tile('L');
		gameFiveTiles[4] = new Tile('Z');
		gameFiveTiles[5] = new Tile('O');
		gameFiveTiles[6] = new Tile('Z');
		
		
		game1 = new Scrabble();
		game2 = new Scrabble(gameTwoTiles);
		game3 = new Scrabble(gameThreeTiles);
		game4 = new Scrabble(gameFourTiles);
		game5 = new Scrabble(gameFiveTiles);
		
		ArrayList<String> getWordCheckerGameThree = new ArrayList<String>() {{
		    add("OO");
		    add("OOT");
		    add("OW");
		    add("OWT");
		    add("OX");
		    add("OXO");
		    add("TO");
		    add("TOO");
		    add("TOW");
		    add("TWO");
		    add("WO");
		    add("WOO");
		    add("WOOT");
		    add("WOOTZ");
		    add("WOT");
		    add("WOX");
		    add("ZO");
		    add("ZOO");
		    add("ZOOT");
		}};
		
		ArrayList<String> getWordCheckerGameFive = new ArrayList<String>() {{
		    add("COL");
		    add("COR");
		    add("COZ");
		    add("LO");
		    add("LOR");
		    add("OR");
		    add("ORC");
		    add("ROC");
		    add("ZO");
		    add("ZOL");
		}};
		
		// Test 1 - constructors and getLetters

		if (game2.getLetters().equals("AQCDEXG")) {
			System.out.println("Test 1 Passed");
			writer.write("test 1 passed \n");
		} else {
			System.out.println("Test 1 Failed");
			writer.write("test 1 failed \n");
		}
		
		
		// Test 2 - constructors and getLetters

		if (game3.getLetters().equals("ZWXTQOO")) {
			System.out.println("Test 2 Passed");
			writer.write("test 2 passed \n");
		} else {
			System.out.println("Test 2 Failed");
			writer.write("test 2 failed \n");
		}
		
		
		
		// Test 3 - equals
		
		if (game3.equals(game4)) {
			System.out.println("Test 3 Passed");
			writer.write("test 3 passed \n");
		} else {
			System.out.println("Test 3 Failed");
			writer.write("test 3 failed \n");
		}
		
		//Test 4 - equals
		
		if (game3.equals(game1)) {
			System.out.println("Test 4 Failed");
			writer.write("test 4 Failed \n");
		} else {
			System.out.println("Test 4 Passed");
			writer.write("test 4 Passed \n");
		}
		
		
		// Test 5 - getWords

		if (game3.getWords().equals(getWordCheckerGameThree)) {
			System.out.println("Test 5 Passed");
			writer.write("test 5 passed \n");
		} else {
			System.out.println("Test 5 Failed");
			writer.write("test 5 failed \n");
		}
		
		// Test 6 - getWords

		if (game5.getWords().equals(getWordCheckerGameFive)) {
			System.out.println("Test 6 Passed");
			writer.write("test 6 passed \n");
		} else {
			System.out.println("Test 6 Failed");
			writer.write("test 6 failed \n");
		}
				
		
		// Test 7 - getScores
		
		int[] vals = {2, 2, 3, 3, 5, 5, 6, 6, 6, 6, 6, 7, 9, 10, 11, 12, 13, 13, 17};

		if (Arrays.toString(game3.getScores()).replaceAll("\\s+","").equals(Arrays.toString(vals).replaceAll("\\s+",""))) {
			System.out.println("Test 7 Passed");
			writer.write("test 7 passed \n");
		} else {
			System.out.println("Test 7 Failed");
			writer.write("test 7 failed \n");
		}

		// Test 8 - getScores
		
		int[] vals2 = {2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 9, 9, 9, 9, 10, 11, 11, 12};

		if (Arrays.toString(game2.getScores()).replaceAll("\\s+","").equals(Arrays.toString(vals2).replaceAll("\\s+",""))) {
			System.out.println("Test 8 Passed");
			writer.write("test 8 passed \n");
		} else {
			System.out.println("Test 8 Failed");
			writer.write("test 8 failed \n");
		}

		writer.close();
	}

}
