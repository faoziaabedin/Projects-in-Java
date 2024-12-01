/* 
 Assignment #1
 fabedin4
 251358251
 COMSCI 1027B 
 Feburary 6th 2024
 */
public class Line {
    private int[] start = new int[2];
    private int[] end = new int[2];

    // Constructor
    public Line(int row, int col, boolean horizontal, int length) {
        start[0] = row;
        start[1] = col;

    // if horizontal then the end row is the same and end coloumn changess
        if (horizontal) {
            end[0] = row;
            end[1] = col + length - 1;
        } else {
            end[0] = row + length - 1;
            end[1] = col;
        }
    }

    // Method to return a copy of the start array
    public int[] getStart() {
        return new int[]{start[0], start[1]};
    }

    // Method to return a copy of the end array
    public int[] getEnd() {
        return new int[]{end[0], end[1]};
    }
    

    // Method to calculate the length
    public int length() {
        if (isHorizontal()) {
            return end[1] - start[1] + 1;
        } else {
            return end[0] - start[0] + 1;
        }
    }

    // Method to check if it is horizontal
    public boolean isHorizontal() {
        return start[0] == end[0];
    }

    // Method to check if a given position is in the line
    public boolean inLine(int row, int col) {
        if (isHorizontal()) {
            return row == start[0] && col >= start[1] && col <= end[1];
        } else {
            return col == start[1] && row >= start[0] && row <= end[0];
        }
    }

    // toString method for formating
    public String toString() {
        return "Line:[" + start[0] + "," + start[1] + "]->[" + end[0] + "," + end[1] + "]";
    }
}
