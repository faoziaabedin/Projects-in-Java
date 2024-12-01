import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

public class MazeVisualizer extends JPanel {

    private char[][] maze;
    private ArrayList<String> path;
    private static final int CELL_SIZE = 30;

    public MazeVisualizer(char[][] maze, ArrayList<String> path) {
        this.maze = maze;
        this.path = path;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                if (maze[row][col] == '#') {
                    g.setColor(Color.BLACK);
                } else if (maze[row][col] == 'S') {
                    g.setColor(Color.GREEN);
                } else if (maze[row][col] == 'E') {
                    g.setColor(Color.RED);
                } else if (maze[row][col] == '+') {
                    g.setColor(Color.YELLOW);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.GRAY);
                g.drawRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    public void display() {
        JFrame frame = new JFrame("Maze Solver");
        frame.add(this);
        frame.setSize(maze[0].length * CELL_SIZE + 20, maze.length * CELL_SIZE + 40);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
