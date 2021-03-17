// Jacob Schwartz - 2/6/2021
// The maze generation class for my Programming 2 individual project: Maze Game

/*
    Notes:
    Look into using ArrayLists for the maze arrays
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MazeGeneration {

    int type, x, y;

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
    int currentX, currentY;

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
        maze.get(currentY).set(currentX, 'S'); // This lets you set a tile as something new, and sets the starting point
        Random randomDir = new Random();

        for (int i = 0; i < 201; i++) { // I randomly selected 10, so 10 tiles should be carved out
            // Use a while loop that checks if any tiles are still #?
            //while(!checkIfBoardDone()){
            int dir = randomDir.nextInt(4) + 1;
            int counter = 0;
            int lastSplitX = 0;
            int lastSplitY = 0;
            boolean tryBox = true;
            /*
             * I would like to possibly use methods for these: Test if the cell currentY-1
             * (for North) is visited or not if yes: try another direciton and increase a
             * counter to see if all sides are checked -> if all sides are checked then back
             * up to the saved location if no: depending on the current cell type, change
             * the current cell type and the targeted cell to the correct type. Make the
             * targeted cell the new current cell. if the counter was less than 4 then save
             * the old current cell as the saved cell
             * 
             */
            while (tryBox) {
                if (counter == 4) { // If all sides have been visited already
                    currentX = lastSplitX;
                    currentY = lastSplitY;
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
                                lastSplitX = currentX;
                                lastSplitY = currentY;
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
                                lastSplitX = currentX;
                                lastSplitY = currentY;
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
                                lastSplitX = currentX;
                                lastSplitY = currentY;
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
                                lastSplitX = currentX;
                                lastSplitY = currentY;
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

        }

        // This prints out the maze
        for (

        List<Character> line : maze) {
            System.out.println(line);
        }

    }

    // This method will return what the current cell should become depending on what
    // type it is and the direction.
    // The targeted cell will always be unvisited at first, and then become either
    // ), C, N, or U
    // It's the current cell that will change depending on the type and direction
    public char tileSwapper(char currentCell, char direction) {
        char newCurrentCell = 'S';

        switch (currentCell) {

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
            System.out.println("This shouldn't have printed. The currentCell char was " + currentCell);
        }

        return newCurrentCell;
    }

    public boolean checkIfBoardDone(){
        for (List<Character> row : hasBeenReached) {
            for (int i = 0; i < row.size();) {
                if(row.get(i) == '#'){
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    public void display(List<List<Character>> maze) { // This takes the maze array and feeds the block types and
                                                      // positions to the Graphics class
        int rowNum = 0;
        char type = 'O';
        for (List<Character> row : maze) {
            for (int i = 0; i < row.size(); i++) {

                type = row.get(i);

                g.mazeDisplay(type, 64 * i, 64 * rowNum); // 157 for Large blocks, 64 for Small blocks
            }
            rowNum++;
        }
        g.mazeDisplay('E', 0, 0); // An attempt to fix the random square appearing in the middle of the screen
    }

}

/*
 * Old way of making the maze
 * 
 * // static char[] row0 = { '#', '#', '#', '#', 'E', 'O', 'O', '#', 'O', 'O',
 * }; // static char[] row1 = { '#', '#', '#', '#', '#', '#', 'O', 'O', '#',
 * 'O', }; // static char[] row2 = { '#', 'O', 'O', 'O', 'O', 'O', '#', 'O',
 * 'O', 'O', }; // static char[] row3 = { '#', 'O', '#', 'O', '#', 'O', '#',
 * '#', '#', 'O', }; // static char[] row4 = { '#', 'O', '#', 'O', '#', 'O',
 * 'O', 'O', 'O', 'O', }; // static char[] row5 = { '#', 'O', '#', 'O', '#',
 * 'O', 'O', 'O', 'O', 'O', }; // static char[] row6 = { '#', 'O', '#', 'O',
 * '#', 'O', 'O', 'O', 'O', 'O', }; // static char[] row7 = { '#', 'O', '#',
 * 'O', '#', 'O', 'O', 'O', 'O', 'O', }; // static char[] row8 = { '#', 'O',
 * '#', 'O', '#', 'O', 'O', 'O', 'O', 'O', }; // static char[] row9 = { '#',
 * 'O', '#', 'O', '#', 'O', 'O', 'O', 'O', 'O', }; // static char[] row10 = {
 * '#', 'O', '#', 'O', '#', 'O', '#', 'O', '#', 'O', // '#', 'O', '#', 'O', '#',
 * 'O', '#', 'O', '#', 'O',};
 * 
 * //static char[][] maze = { row0, row1, row2, row3, row4, };
 * 
 */

// static List<Character> row0 = new ArrayList<Character>(Arrays.asList('#',
// '#', '#', '#', 'E', 'O', 'O', '#', 'O', 'O'));
// static List<Character> row1 = new ArrayList<Character>(Arrays.asList('#',
// '#', '#', '#', '#', '#', 'O', 'O', '#', 'O'));
// static List<Character> row2 = new ArrayList<Character>(Arrays.asList('#',
// 'O', 'O', 'O', 'O', 'O', '#', 'O', 'O', 'O'));
// static List<Character> row3 = new ArrayList<Character>(Arrays.asList('#',
// 'O', '#', 'O', '#', 'O', '#', '#', '#', 'O'));
// static List<Character> row4 = new ArrayList<Character>(Arrays.asList('#',
// 'O', '#', 'O', '#', 'O', 'O', 'O', 'O', 'O'));

// static List<List<Character>> maze = new
// ArrayList<List<Character>>(Arrays.asList(row0, row1, row2, row3, row4)); //
// Code from
// https://stackoverflow.com/questions/10768170/how-do-i-declare-a-2d-string-arraylist