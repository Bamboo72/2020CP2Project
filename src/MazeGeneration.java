// Jacob Schwartz - 2/6/2021
// The maze generation class for my Programming 2 individual project: Maze Game

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This is the class that handles maze generation so far with one type that uses
 * the recursive backtracker method which I learned from
 * http://weblog.jamisbuck.org/2011/2/7/maze-generation-algorithm-recap
 */
public class MazeGeneration {

    int type, x, y;

    /**
     * This is the MazeGeneration constructor, and takes in the maze type and size
     * parameters
     * 
     * @param type
     * @param x
     * @param y
     */
    public MazeGeneration(int type, int x, int y) { // Maze type and size parameters
        this.type = type;
        this.x = x;
        this.y = y;

    }

    Graphics g = MazeGame.g;

    /*
     * Steps 1. Make a 2D array of solid blocks 2. Choose a starting point in the
     * field. 3. Randomly choose a wall at that point and carve a passage through to
     * the adjacent cell, but only if the adjacent cell has not been visited yet.
     * This becomes the new current cell. 4. If all adjacent cells have been
     * visited, back up to the last cell that has uncarved walls and repeat. The
     * algorithm ends when the process has backed all the way up to the starting
     * point.
     */

    static List<List<Character>> hasBeenReached = new ArrayList<List<Character>>();
    static List<List<Character>> maze = new ArrayList<List<Character>>();
    static int currentX;
    static int currentY;
    static ArrayList<String> splits = new ArrayList<String>();
    boolean generate = true;

    /**
     * This method generates the maze
     */
    public void mazeGen() {

        // Make new blank mazes of size x and y. One for the actual maze, one to track
        // visitation.
        for (int i = 0; i < y; i++) {
            List<Character> row = new ArrayList<Character>();
            for (int j = 0; j < x; j++) {
                row.add('#');
            }
            hasBeenReached.add(row);
        }

        for (int i = 0; i < y; i++) {
            List<Character> row = new ArrayList<Character>();
            for (int j = 0; j < x; j++) {
                row.add('#');
            }
            maze.add(row);
        }

        // Choose a random direciton and carve a path if the cell is not visited yet.
        currentX = x / 2;
        currentY = y - 1;
        Random randomDir = new Random();

        while (generate) {
            int dir = randomDir.nextInt(4) + 1;
            int counter = 0;
            boolean tryBox = true;

            while (tryBox) {
                if (counter == 4) { // If all sides have been visited already
                    currentX = getLastSplit('X');
                    currentY = getLastSplit('Y');
                    splits.remove(splits.size() - 1);
                    tryBox = false;
                }
                if (dir == 1) { // North
                    if (currentY != 0) { // Check the top edge
                        if (hasBeenReached.get(currentY - 1).get(currentX) == '#') { // If tile to the North has not
                                                                                     // been
                            // visited:
                            hasBeenReached.get(currentY - 1).set(currentX, 'V'); // Set the tile to visited

                            // Swap the current cell to what it should become
                            maze.get(currentY).set(currentX, tileSwapper(maze.get(currentY).get(currentX), 'N'));
                            maze.get(currentY - 1).set(currentX, 'N'); // Change the targeted cell

                            if (counter < 4) {
                                recordLastSplit(currentX, currentY);
                            }

                            currentY--; // Go up
                            tryBox = false;
                        } else {
                            dir++;
                            counter++;
                        }
                    } else {
                        dir++;
                        counter++;
                    }

                } else if (dir == 2) { // East
                    if (currentX != x - 1) { // Check the right edge
                        if (hasBeenReached.get(currentY).get(currentX + 1) == '#') {
                            hasBeenReached.get(currentY).set(currentX + 1, 'V'); // Set the tile to visited

                            // Swap the current cell to what it should become
                            maze.get(currentY).set(currentX, tileSwapper(maze.get(currentY).get(currentX), 'E'));
                            maze.get(currentY).set(currentX + 1, ')'); // Change the targeted cell

                            if (counter < 4) {
                                recordLastSplit(currentX, currentY);
                            }

                            currentX++; // Go right
                            tryBox = false;
                        } else {
                            dir++;
                            counter++;
                        }
                    } else {
                        dir++;
                        counter++;
                    }
                } else if (dir == 3) { // South
                    if (currentY != y - 1) { // Check the bottom edge
                        if (hasBeenReached.get(currentY + 1).get(currentX) == '#') {
                            hasBeenReached.get(currentY + 1).set(currentX, 'V'); // Set the tile to visited

                            // Swap the current cell to what it should become
                            maze.get(currentY).set(currentX, tileSwapper(maze.get(currentY).get(currentX), 'S'));
                            maze.get(currentY + 1).set(currentX, 'U'); // Change the targeted cell

                            if (counter < 4) {
                                recordLastSplit(currentX, currentY);
                            }

                            currentY++; // Go down
                            tryBox = false;
                        } else {
                            dir++;
                            counter++;
                        }
                    } else {
                        dir++;
                        counter++;
                    }

                } else if (dir == 4) { // West
                    if (currentX != 0) { // Check the left edge
                        if (hasBeenReached.get(currentY).get(currentX - 1) == '#') {
                            hasBeenReached.get(currentY).set(currentX - 1, 'V'); // Set the tile to visited

                            // Swap the current cell to what it should become
                            maze.get(currentY).set(currentX, tileSwapper(maze.get(currentY).get(currentX), 'W'));
                            maze.get(currentY).set(currentX - 1, 'C'); // Change the targeted cell

                            if (counter < 4) {
                                recordLastSplit(currentX, currentY);
                            }

                            currentX--; // Go left
                            tryBox = false;
                        } else {
                            dir = 1;
                            counter++;
                        }
                    } else {
                        dir = 1;
                        counter++;
                    }
                } else {
                    System.out.println("Something went wrong.. The direction shouldn't be " + dir + "!");
                }
            }
            checkIfBoardDone();
        }
        maze.get(0).set(0, 'E');

    }

    /**
     * This method adds the last split cell to the ArrayList of splits to be
     * backtracked to later
     * 
     * @param lastSplitX
     * @param lastSplitY
     */
    public void recordLastSplit(int lastSplitX, int lastSplitY) {
        splits.add("" + lastSplitX + "," + lastSplitY);

    }

    /**
     * This method returns the most recent split coordinate: x or y depending on the
     * type
     * 
     * @param type
     * @return int x or y, depending on the type that is passed in
     */
    public int getLastSplit(char type) {
        String coord = splits.get(splits.size() - 1);
        int x = -1, y = -1;

        for (int i = 0; i < coord.length(); i++) {
            if (coord.charAt(i) == ',') {
                x = Integer.parseInt(coord.substring(0, i));
                y = Integer.parseInt(coord.substring(i + 1, coord.length()));
            }
        }

        if (type == 'X')
            return x;
        else
            return y;

    }

    // The targeted cell will always be unvisited at first, and then become either
    // ), C, N, or U
    // It's the current cell that will change depending on the type and direction
    /**
     * This method will return what the current cell should become depending on what
     * type it is and the direction
     * 
     * @param currentCell
     * @param direction
     * @return char the newCurrentCell which the old currentCell will become
     */
    public char tileSwapper(char currentCell, char direction) {
        char newCurrentCell = '4';

        switch (currentCell) {

        // Maybe make a case for S and E?

        case 'N':
            if (direction == 'N') {
                newCurrentCell = 'P';
            } else if (direction == 'E') {
                newCurrentCell = 'R';
            } else if (direction == 'W') {
                newCurrentCell = '7';
            }
            break;
        case ')':
            if (direction == 'N') {
                newCurrentCell = 'J';
            } else if (direction == 'E') {
                newCurrentCell = '=';
            } else if (direction == 'S') {
                newCurrentCell = '7';
            }
            break;
        case 'U':
            if (direction == 'E') {
                newCurrentCell = 'L';
            } else if (direction == 'S') {
                newCurrentCell = 'P';
            } else if (direction == 'W') {
                newCurrentCell = 'J';
            }
            break;
        case 'C':
            if (direction == 'N') {
                newCurrentCell = 'L';
            } else if (direction == 'S') {
                newCurrentCell = 'R';
            } else if (direction == 'W') {
                newCurrentCell = '=';
            }
            break;
        case '=':
            if (direction == 'N') {
                newCurrentCell = 'B';
            } else if (direction == 'S') {
                newCurrentCell = 'T';
            }
            break;
        case 'P':
            if (direction == 'E') {
                newCurrentCell = '1';
            } else if (direction == 'W') {
                newCurrentCell = 'I';
            }
            break;
        case '7':
            if (direction == 'N') {
                newCurrentCell = 'I';
            } else if (direction == 'E') {
                newCurrentCell = 'T';
            }
            break;
        case 'J':
            if (direction == 'E') {
                newCurrentCell = 'B';
            } else if (direction == 'S') {
                newCurrentCell = 'I';
            }
            break;
        case 'L':
            if (direction == 'S') {
                newCurrentCell = '1';
            } else if (direction == 'W') {
                newCurrentCell = 'B';
            }
            break;
        case 'R':
            if (direction == 'N') {
                newCurrentCell = '1';
            } else if (direction == 'W') {
                newCurrentCell = 'T';
            }
            break;
        case 'T':
            if (direction == 'N') {
                newCurrentCell = '0';
            }
            break;
        case 'I':
            if (direction == 'E') {
                newCurrentCell = '0';
            }
            break;
        case 'B':
            if (direction == 'S') {
                newCurrentCell = '0';
            }
            break;
        case '1':
            if (direction == 'W') {
                newCurrentCell = '0';
            }
            break;
        default:
            System.out.println("The default for the tileSwapper printed. The currentCell char was " + currentCell);
        }

        return newCurrentCell;
    }

    /**
     * This method checks if every tile in the maze has been reached or not. It will
     * stop generation if every tile has been reached.
     */
    public void checkIfBoardDone() {
        for (List<Character> row : hasBeenReached) {
            for (int i = 0; i < row.size(); i++) {
                if (row.get(i) == '#') {
                    generate = true;
                    return;
                }
            }
        }
        generate = false;

    }

    /**
     * This method resets the 2D ArrayLists that make up the maze
     */
    public void mazeReset() {
        hasBeenReached.clear();
        maze.clear();

    }

    /**
     * This method takes the maze array and feeds the block types and positions to
     * the Graphics class
     * 
     * @param maze
     */
    public void display(List<List<Character>> maze) {
        int rowNum = 0;
        char type = 'O';
        MazeGame.g.characterDisplay(MazeGame.cc);
        for (List<Character> row : maze) {
            for (int i = 0; i < row.size(); i++) {

                type = row.get(i);

                g.mazeDisplay(type, 64 * i, 64 * rowNum); // 157 for Large blocks, 64 for Small blocks
            }
            rowNum++;
        }
    }

    /**
     * This method returns the tile type at the passed in x and y location
     * 
     * @param x
     * @param y
     * @return char they type of tile at the x and y location
     */
    public static char getTileType(int x, int y) {
        return maze.get(y).get(x);
    }

}
