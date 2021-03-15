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
        int currentX = x / 2;
        int currentY = y - 1;
        maze.get(currentY).set(currentX, 'E'); // This lets you set a tile as something new
        Random randomDir = new Random();

        for (int i = 0; i < 10; i++) { // I randomly selected 10, so 10 tiles should be carved out
            int dir = randomDir.nextInt(4);
            int counter = 0;
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
             * NOTE: I need edge detection to avoid out of bound errors!
             */
            while (tryBox) {
                if (counter == 4) {
                    tryBox = false;
                }
                if (dir == 1) { // North
                    if (hasBeenReached.get(currentY - 1).get(currentX) == '#') { // If tile to the North has not been
                                                                                 // visited:
                        hasBeenReached.get(currentY - 1).set(currentX, 'V'); // Set the tile to visited
                        // Insert a method here that takes in the current tile type and returns what the
                        // targeted cell type should be.
                        currentY--; // Go up
                        tryBox = false;
                    } else {
                        dir++;
                        counter++;
                    }
                } else if (dir == 2) { // East
                    if (hasBeenReached.get(currentX + 1).get(currentX) == '#') { 
                        hasBeenReached.get(currentX + 1).set(currentX, 'V'); // Set the tile to visited
                        // Insert a method here that takes in the current tile type and returns what the
                        // targeted cell type should be.
                        currentX++; // Go right
                        tryBox = false;
                    } else {
                        dir++;
                        counter++;
                    }
                } else if (dir == 3) { // South
                    if (hasBeenReached.get(currentY + 1).get(currentX) == '#') { 
                        hasBeenReached.get(currentY + 1).set(currentX, 'V'); // Set the tile to visited
                        // Insert a method here that takes in the current tile type and returns what the
                        // targeted cell type should be.
                        currentY++; // Go down
                        tryBox = false;
                    } else {
                        dir++;
                        counter++;
                    }

                } else if (dir == 4) { // West
                    if (hasBeenReached.get(currentX - 1).get(currentX) == '#') { 
                        hasBeenReached.get(currentX - 1).set(currentX, 'V'); // Set the tile to visited
                        // Insert a method here that takes in the current tile type and returns what the
                        // targeted cell type should be.
                        currentX--; // Go left
                        tryBox = false;
                    } else {
                        dir = 1;
                        counter++;
                    }
                } else {
                    System.out.println("Something went wrong.. The direction shouldn't be more than 4!");
                }
            }

        }

        // This prints out the maze
        for (List<Character> line : maze) {
            System.out.println(line);
        }

    }

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

    public void display(List<List<Character>> maze) { // This takes the maze array and feeds the block types and
                                                      // positions to the Graphics class
        int rowNum = 0;
        int type = 0;
        for (List<Character> row : maze) {
            for (int i = 0; i < row.size(); i++) {

                switch (row.get(i)) {

                case '#':
                    type = 1; // .//res//4.png
                    break;
                case 'O':
                    type = 0; // .//res//0.png
                    break;
                case 'E':
                    type = 2; // .//res//Exit.png
                    break;

                }

                g.mazeDisplay(type, 64 * i, 64 * rowNum); // 157 for Large blocks, 64 for Small blocks
            }
            rowNum++;
        }
        g.mazeDisplay(-1, 0, 0); // An attempt to fix the random square appearing in the middle of the screen
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