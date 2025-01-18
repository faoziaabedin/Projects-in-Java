import java.util.ArrayList;

public class MazeSolver {

    // Uses backtracking to solve the maze
    public static boolean solveMaze(char[][] maze, int row, int col, ArrayList<String> path, MazeVisualizer visualizer) {
        // Check if the current position is out of bounds, a wall, or already visited
        if (row < 0 || col < 0 || row >= maze.length || col >= maze[0].length || maze[row][col] == '#' || maze[row][col] == '+') {
            return false;
        }

        // Check if the current position is the end of the maze
        if (maze[row][col] == 'E') {
            return true;
        }

        // Mark the current position as visited
        maze[row][col] = '+';
        visualizer.repaint(); // Update the visualizer to reflect changes
        try {
            Thread.sleep(100); // delay (100ms) to see the animation
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Try moving in all four directions
        // Move right
        if (solveMaze(maze, row, col + 1, path, visualizer)) {
            path.add("right");
            return true;
        }
        // Move down
        if (solveMaze(maze, row + 1, col, path, visualizer)) {
            path.add("down");
            return true;
        }
        // Move left
        if (solveMaze(maze, row, col - 1, path, visualizer)) {
            path.add("left");
            return true;
        }
        // Move up
        if (solveMaze(maze, row - 1, col, path, visualizer)) {
            path.add("up");
            return true;
        }

        // If none of the directions work, backtrack by unmarking the current position
        maze[row][col] = ' ';
        visualizer.repaint(); // Update the visualizer to reflect changes
        try {
            Thread.sleep(100); // delay (100ms) to see the animation
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args) {
        Maze maze = new Maze("maze1.txt"); // Load the maze from a file
        maze.printMaze(); // Print the initial maze

        ArrayList<String> path = new ArrayList<>(); // To store the path taken

        // Create a frame to display the maze
        MazeVisualizer visualizer = new MazeVisualizer(maze.getMaze(), path);
        visualizer.display(); // Display the maze using the visualizer

        // Attempt to solve the maze and print the result
        if (solveMaze(maze.getMaze(), maze.getStartRow(), maze.getStartCol(), path, visualizer)) {
            System.out.println("Maze solved:");
            for (int i = path.size() - 1; i >= 0; i--) {
                System.out.println(path.get(i)); // Print the path from start to end
            }
        } else {
            System.out.println("No solution found for the maze.");
        }
    }
}
