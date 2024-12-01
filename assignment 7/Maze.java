import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maze {

    private char[][] maze;
    private int startRow, startCol;

    public Maze(String filename) {
        loadMaze(filename);
    }

    private void loadMaze(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int rows = 0;
            int cols = 0;

            while ((line = br.readLine()) != null) {
                rows++;
                cols = line.length();
            }

            maze = new char[rows][cols];

            br.close();
            try (BufferedReader br2 = new BufferedReader(new FileReader(filename))) {
                int row = 0;
                while ((line = br2.readLine()) != null) {
                    for (int col = 0; col < line.length(); col++) {
                        maze[row][col] = line.charAt(col);
                        if (maze[row][col] == 'S') {
                            startRow = row;
                            startCol = col;
                        }
                    }
                    row++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printMaze() {
        for (char[] row : maze) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }

    public char[][] getMaze() {
        return maze;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartCol() {
        return startCol;
    }
}
