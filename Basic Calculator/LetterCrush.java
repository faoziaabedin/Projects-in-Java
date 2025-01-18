/* 
 Assignment #1
 fabedin4
 251358251
 COMSCI 1027B 
 Feburary 6th 2024
 */

 public class LetterCrush {
    private char[][] grid;
    public static final char EMPTY = ' '; // Constant for empty tile

    // Constructor to initialize the grid with specified dimensions and initial characters
    public LetterCrush(int width, int height, String initial) {
        grid = new char[height][width]; 
        int index = 0; 

        // fill the grid with characters from the initial string or set to EMPTY
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (index < initial.length()) {
                    grid[i][j] = initial.charAt(index++);
                } else {
                    grid[i][j] = EMPTY; // Set remaining cells to EMPTY 
                }
            } 
        }
    }

    // Method to represent the grid as a string
    public String toString() {
        StringBuilder sb = new StringBuilder("LetterCrush\n");
        for (int i = 0; i < grid.length; i++) {
            sb.append("|");
            for (int j = 0; j < grid[i].length; j++) {
                sb.append(grid[i][j]);
            }
            sb.append("|").append(i).append("\n");
        }
        sb.append("+");
        for (int i = 0; i < grid[0].length; i++) {
            sb.append(i);
        }
        sb.append("+");
        return sb.toString();
    }

    //Method to check if the grid is stable
    public boolean isStable() {
        for (int col = 0; col < grid[0].length; col++) {
            for (int row = grid.length - 1; row > 0; row--) {
                if (grid[row][col] == EMPTY && grid[row - 1][col] != EMPTY) {
                    return false; // false if not stable 
                }
            }
        }
        return true; // if stable
    }    

    //Method for replacing empty titles with the values above
    public void applyGravity() {
        for (int col = 0; col < grid[0].length; col++) { 
            for (int row = grid.length - 1; row > 0; row--) { // Start from the bottom row and move up
                if (grid[row][col] == EMPTY) {
                    // Find the first non-EMPTY cell above the current EMPTY cell
                    int nonEmptyRow = row - 1;
                    while (nonEmptyRow >= 0 && grid[nonEmptyRow][col] == EMPTY) {
                        nonEmptyRow--; 
                    }
                    if (nonEmptyRow >= 0) { 
                        grid[row][col] = grid[nonEmptyRow][col]; 
                        grid[nonEmptyRow][col] = EMPTY; 
                    }
                }
            }
        }
    }

    //Method to check validity and remove from grid
    public boolean remove(Line theLine) {
        // Check if either the start or end point of the line is out of the grid bounds
        if (!isPointWithinGrid(theLine.getStart()) || !isPointWithinGrid(theLine.getEnd())) {
            return false; 
        }
    
        boolean isHorizontal = theLine.isHorizontal();
    
        if (isHorizontal) {
            for (int col = theLine.getStart()[1]; col <= theLine.getEnd()[1]; col++) {
                grid[theLine.getStart()[0]][col] = EMPTY; 
            }
        } else {
            // For a vertical line, iterate over rows from start to end within the same column
            for (int row = theLine.getStart()[0]; row <= theLine.getEnd()[0]; row++) {
                grid[row][theLine.getStart()[1]] = EMPTY; 
            }
        }
    
        return true; // The line was removed
    }
    
    // Extra helper method to check if a point is within the grid bounds
    private boolean isPointWithinGrid(int[] point) {
        int row = point[0];
        int col = point[1];
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }
    
    // Method for letters to be shown lowercase corresponding to grid
    public String toString(Line theLine) {
        StringBuilder sb = new StringBuilder("CrushLine\n");
        for (int i = 0; i < grid.length; i++) {
            sb.append("|");
            for (int j = 0; j < grid[i].length; j++) {
                char c = grid[i][j];
                if (theLine.inLine(i, j)) {
                    c = Character.toLowerCase(c); // Convert to lowercase if it's part of theLine
                }
                sb.append(c);
            }
            sb.append("|").append(i).append("\n"); 
        }
        sb.append("+");
        for (int j = 0; j < grid[0].length; j++) {
            sb.append(j); 
        }
        sb.append("+");
        return sb.toString();
    }

    // Method to indicate the longest line
    public Line longestLine() {
        Line longLine = null;
        int largest = 0;
   
        //loop to start at bottom of grid and then move up
        for (int i = grid.length - 1; i >= 0; i--) { 
            char letter = grid[i][0];
            int adjacent = 1;
   
            for (int j = 1; j < grid[i].length; j++) {
                if (grid[i][j] == letter && letter != EMPTY) {
                    adjacent++;
                    if (adjacent > largest ) {
                        largest = adjacent;
                        longLine = new Line(i, j - adjacent + 1, true, adjacent);
                    }
                } 
                else {
                        letter = grid[i][j];
                        adjacent = 1; 
                }
            }
        }
   
        // scaning the coloumns from left to right and top to bottom
        for (int j = 0; j < grid[0].length; j++) {
            char letter = grid[grid.length - 1][j];
            int adjacent = 1;
   
            for (int i = grid.length - 2; i >= 0; i--) {
                if (grid[i][j] == letter && letter != EMPTY) {
                    adjacent++;
                    if (adjacent > largest) {
                        largest = adjacent;
                        longLine = new Line(i, j, false, adjacent);
                    }
                } else {
                    letter = grid[i][j];
                    adjacent = 1;
                }
            }
        }
   
        if (largest >= 3) {
            return longLine;
        } else {
            return null;
        }
    }
 
    //Method to remove the longest line
    public void cascade() {
        Line longestLine;
        do {
            longestLine = longestLine(); // Find the longest line of matching letters
            if (longestLine != null && longestLine.length() >= 3) {
                remove(longestLine); 
                applyGravity(); 
            }
        } while (longestLine != null && longestLine.length() >= 3);
    }    
    
}
