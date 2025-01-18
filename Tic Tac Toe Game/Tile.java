import java.util.Random;

public class Tile {
    private char letter;  

    // Default constructor that generates a random letter for the tile
    public Tile() {
        this.letter = generateLetter(); 
    }

    // Constructor with a parameter that sets the letter to the given character
    public Tile(char c) {
        this.letter = c; 
    }

    // Getter method to return the letter on the tile
    public char getLetter() {
        return this.letter; 
    }

    // Private method to generate a random letter between 'A' and 'Z'
    private char generateLetter() {
        Random random = new Random();
        // Generate a random number between 0 and 25, then add the ASCII value of 'A' (65)
        return (char) (random.nextInt(26) + 'A');
    }
}
