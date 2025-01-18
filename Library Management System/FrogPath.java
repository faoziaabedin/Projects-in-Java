public class FrogPath {
    private Pond pond;

    public FrogPath(String filename) {
        try {
            pond = new Pond(filename); // Assuming Pond can be initialized with a filename
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Hexagon findBest(Hexagon currCell) {
        ArrayUniquePriorityQueue<Hexagon> pq = new ArrayUniquePriorityQueue<>();
    
        for (int i = 0; i < 6; i++) {
            Hexagon neighbor = currCell.getNeighbour(i);
            if (isValidCell(neighbor)) { // Use isValidCell method to check if the neighbor can be added
                boolean isTwoStepsAway = isTwoCellsAway(neighbor, currCell);
                boolean isInStraightLine = isInStraightLine(neighbor, currCell);
                double priority = calcPriority(neighbor, isTwoStepsAway, isInStraightLine);
                pq.add(neighbor, priority);
            }
        }
    
        return pq.isEmpty() ? null : pq.removeMin(); // Assuming removeMin is the method to retrieve and remove the head
    }
    
    

    // Helper method to determine if Freddy can jump to a given cell.
    private boolean isValidCell(Hexagon cell) {
        if (cell == null) {
            return false;
        } else if (cell.isMarked()) {
            return false;
        } else if (cell.isMudCell() || cell.isAlligator()) {
            return false;
        } else if (isNearAlligator(cell) && !cell.isReedsCell()) {
            return false;
        }
        return true; // Cell is safe and available to jump to.
    }

    private boolean isNearAlligator(Hexagon cell) {
        // Check the immediate neighbors for an alligator
        for (int i = 0; i < 6; i++) {
            Hexagon neighbor = cell.getNeighbour(i);
            if (neighbor != null && neighbor.isAlligator()) {
                return true;
            }
        }
        return false;
    }

    private boolean isInStraightLine(Hexagon cell, Hexagon currCell) {
        // Check if two cells are in a straight line
        for (int i = 0; i < 6; i++) {
            // Get the neighbour in the direction i
            Hexagon neighbor = cell.getNeighbour(i);
            if (neighbor != null) {
                // Check if the opposite neighbour (i+3 mod 6) of the neighbour is the current cell
                // This would mean both cells are in a straight line
                Hexagon oppositeNeighbor = neighbor.getNeighbour((i + 3) % 6);
                if (oppositeNeighbor != null && oppositeNeighbor.equals(currCell)) {
                    return true;
                }
            }
        }
        return false;
    }



    // Helper method to determine the priority of a cell.
    private double calcPriority(Hexagon cell, boolean isTwoStepsAway, boolean isInStraightLine) {
        double priority = 100.0; // Default priority for inaccessible cells

        if (cell.isEnd()) return 0; // Highest priority for the end cell
        if (cell instanceof FoodHexagon) return 1 - ((FoodHexagon) cell).getNumFlies() * 0.1; // Priority based on food
        if (cell.isLilyPadCell()) return 2; // Priority for lily pad cells
        if (cell.isReedsCell()) return 3; // Priority for reeds cells
        if (cell.isWaterCell()) return 4; // Priority for water cells

        // Adjust priority for cells two steps away
        if (isTwoStepsAway) {
            priority += isInStraightLine ? 0.5 : 1.0;
        }

        return priority;
    }


    // Helper method to determine if two given cells are two cells apart.
    private boolean isTwoCellsAway(Hexagon cell, Hexagon currCell) {
        // Check if the two cells are adjacent.
        for (int i = 0; i < 6; i++) {
            if (cell.getNeighbour(i) != null && cell.getNeighbour(i).equals(currCell)) {
                return false;
            }
        }

        // Check for two cells away condition.
        for (int i = 0; i < 6; i++) {
            Hexagon neighbourCell = cell.getNeighbour(i);
            if (cell.getNeighbour(i) != null) {
                for (int j = 0; j < 6; j++) {
                    if (neighbourCell.getNeighbour(j) != null && neighbourCell.getNeighbour(j).equals(currCell)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
    public String findPath() {
        ArrayStack<Hexagon> pathStack = new ArrayStack<>();
        Hexagon startCell = pond.getStart();
        pathStack.push(startCell);
        startCell.markInStack();
        int fliesEaten = 0;
        StringBuilder path = new StringBuilder();
    
        while (!pathStack.isEmpty()) {
            Hexagon currentCell = pathStack.peek();
            path.append(currentCell.getID()).append(" ");
            if (currentCell.isEnd()) {
                break;
            }
            if (currentCell instanceof FoodHexagon) {
                FoodHexagon foodHex = (FoodHexagon) currentCell;
                fliesEaten += foodHex.getNumFlies();
                foodHex.removeFlies();
            }
    
            Hexagon nextCell = findBest(currentCell);
            if (nextCell == null) {
                currentCell.markOutStack();
                pathStack.pop();
            } else {
                nextCell.markInStack();
                pathStack.push(nextCell);
            }
        }
    
        if (!pathStack.isEmpty()) {
            path.append("ate ").append(fliesEaten).append(" flies");
            return path.toString().trim();
        } else {
            return "No solution";
        }
    }
    

}