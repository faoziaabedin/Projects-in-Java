import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;

public class Scrabble {
    private Tile tiles[];  // Array to hold the 7 tiles

    // Default constructor that initializes the tiles array with random Tile objects
    public Scrabble() {
        tiles = new Tile[7];
        for (int i = 0; i < 7; i++) {
            tiles[i] = new Tile();  // Initialize each Tile in the array with a random Tile object
        }
    }
    
    // Constructor with a parameter that initializes the tiles array with the given Tile objects
    public Scrabble(Tile[] tiles) {
        if (tiles.length != 7) {
            throw new IllegalArgumentException("Tile array must be of length 7."); // Exception thrown if array is not of length 7
        }
        this.tiles = tiles;
    }

    // Method to get letters as a single string
    public String getLetters() {
        String letters = "";  // Initialize an empty string to build the string of letters
        for (Tile tile : tiles) {
            letters += tile.getLetter();  // Concatenate each tile's letter to the string
        }
        return letters;  // Return the concatenated string
    }

    // Method to get all spellable words
    public ArrayList<String> getWords() throws FileNotFoundException {
        ArrayList<String> spellableWords = new ArrayList<>();  
        try (BufferedReader br = new BufferedReader(new FileReader("CollinsScrabbleWords2019.txt"))) {
            String word;  
            while ((word = br.readLine()) != null) {  // Loop through each word in the CollinsScrabbleWords2019.txt file
                String tempLetters = getLetters(); 
                boolean canSpell = true;  

                for (char c : word.toCharArray()) {  
                    int index = tempLetters.indexOf(c);  // Find the character in our available letters
                    if (index == -1) {  
                        canSpell = false;  
                        break;  
                    } else { 
                        tempLetters = tempLetters.substring(0, index) + tempLetters.substring(index + 1);  // Remove the character from available letters
                    }
                }

                if (canSpell) {  // If we can spell the word
                    spellableWords.add(word);  // Add the word to our list
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  // Print the stack trace if an exception occurs
        }
        return spellableWords;  // Return the list of spellable words
    }
    
    // Method to get scores for each spellable word
    public int[] getScores() throws FileNotFoundException {
        ArrayList<String> spellableWords = this.getWords();  // Get the list of spellable words
        int[] wordScores = new int[spellableWords.size()];  // Create an array to hold the scores

        for (int i = 0; i < spellableWords.size(); i++) {  // Loop through each spellable word
            int score = 0;
            for (char c : spellableWords.get(i).toCharArray()) {  // Loop through each character in the word
                score += getLetterValue(c);  // Add the value of the character to the score
            }
            wordScores[i] = score;  // Store the score for each word
        }

        Arrays.sort(wordScores);  // Sort the scores in ascending order
        return wordScores;  // Return the sorted scores
    }

    // Method to get the value of a letter
    private int getLetterValue(char letter) {
        if (letter == 'A' || letter == 'E' || letter == 'I' || letter == 'L' || letter == 'N' || letter == 'O' || letter == 'R' || letter == 'S' || letter == 'T' || letter == 'U') {
            return 1;
        } else if (letter == 'D' || letter == 'G') {
            return 2;
        } else if (letter == 'B' || letter == 'C' || letter == 'M' || letter == 'P') {
            return 3;
        } else if (letter == 'F' || letter == 'H' || letter == 'V' || letter == 'W' || letter == 'Y') {
            return 4;
        } else if (letter == 'K') {
            return 5;
        } else if (letter == 'J' || letter == 'X') {
            return 8;
        } else if (letter == 'Q' || letter == 'Z') {
            return 10;
        } else {
            throw new IllegalArgumentException("Invalid Scrabble letter: " + letter);
        }
    }

    // Method to compare two Scrabble objects
    public boolean equals(Scrabble s) {
        if (this == s) return true;  // Check if the objects are the same
        if (s == null || getClass() != s.getClass()) return false;  
        Scrabble scrabble = (Scrabble) s;  // Cast the object to Scrabble
        char[] thisLetters = getLetters().toCharArray();  // Convert this object's letters to a char array
        char[] otherLetters = scrabble.getLetters().toCharArray();  
        Arrays.sort(thisLetters); 
        Arrays.sort(otherLetters);  
        return Arrays.equals(thisLetters, otherLetters);  // Compare the sorted arrays
    }    
}
